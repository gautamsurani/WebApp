package tech.fraction.webapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.OutwardDetailListAdapter;
import tech.fraction.webapp.model.Account;
import tech.fraction.webapp.model.OutwardDetails;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.model.Transporter;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiRequestModel.SaveOutwardRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.DetailOutwardResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.SaveOutwardResponseModel;
import tech.fraction.webapp.rest.CommonApiCall.AccountApiCall;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.AppConstant;
import tech.fraction.webapp.util.Utils;
import tech.fraction.webapp.util.ValidationUtil;

public class AddEditOutwardActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rec_view;
    OutwardDetailListAdapter outwardDetailListAdapter;
    ImageView ivBack;
    Activity context;
    ProgressBar pbParty;
    EditText edt_vehicleNo, edt_transporter, edt_driverName, edt_driverNo, edt_remark;
    TextView txtSave, txtAddItem, tvTitle, tvOutwardNo, tvDate, tvParty;
    String vehicleNo, transporterName, driverName, driverNo, remark;
    RelativeLayout scrollView;
    SaveOutwardRequestModel saveOutwardRequestModel;
    int outwardId;
    String mode = "";
    Transporter transporter;
    DetailOutwardResponseModel detailOutwardResponseModel = new DetailOutwardResponseModel();
    public static ArrayList<OutwardDetails> outwardItemsList = new ArrayList<>();

    ArrayList<Account> lstAccount = new ArrayList<>();
    private static Account selectedAccount = new Account();
    private static String outwardNumber = "", outwardDate = "";

    RelativeLayout rlProgress, rlMain;

    @Override
    protected void onResume() {
        super.onResume();
        outwardDetailListAdapter.setList(outwardItemsList);
        outwardDetailListAdapter.notifyDataSetChanged();
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_outward_detail);

        initComp();

        initItemRecyclerView();

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        getData();

        initCache();

        context = this;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mode = bundle.getString("mode", "");
            outwardId = bundle.getInt("outwardId", -1);
        }

        if (mode.equals("add")) {
            tvTitle.setText("Add Outward");
            if (lstAccount.size() == 0) {
                pbParty.setVisibility(View.VISIBLE);
                AccountApiCall sampleClass = new AccountApiCall();
                sampleClass.setOnDataListener(new AccountApiCall.DataInterface() {
                    @Override
                    public void responseData(AccountResponseModel accountResponseModel) {
                        lstAccount = accountResponseModel.getAccount();
                        pbParty.setVisibility(View.INVISIBLE);
                    }
                });
                sampleClass.CallAccountApi();
            }
        } else {
            tvTitle.setText("Edit Outward");
            CallGetOutwardItemDetailApi(outwardId, Utils.getPersonalInfo(context).getAccountId());
        }

        tvParty.setOnClickListener(this);

        ivBack.setOnClickListener(this);

        txtSave.setOnClickListener(this);

        txtAddItem.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tvParty:
                ArrayList<SearchTextViewModel> itemsList = new ArrayList<>();
                for (int i = 0; i < lstAccount.size(); i++) {
                    itemsList.add(new SearchTextViewModel(lstAccount.get(i).getId(), lstAccount.get(i).getName()));
                }
                Intent i = new Intent(AddEditOutwardActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", itemsList);
                i.putExtra("type", "party");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.txt_save:
                getData();
                if (selectedAccount.getName() == null) {
                    Utils.ShowSnakBar("Please select party ", rlMain, AddEditOutwardActivity.this);
                } else if (outwardItemsList.size() == 0) {
                    Utils.ShowSnakBar("Please Enter Item ", rlMain, AddEditOutwardActivity.this);
                } else {
                    AddDataInRequestModel();
                    Utils.hideKeyboard(AddEditOutwardActivity.this);
                    CallSaveOutwardApi();
                }
                break;
            case R.id.txt_additem: {
                if (selectedAccount.getName() == null) {
                    Utils.ShowSnakBar("Select Party", rlMain, context);
                } else {
                    Intent intent = new Intent(AddEditOutwardActivity.this, SelectedOutwardActivity.class);
                    intent.putExtra("mode", mode);
                    intent.putExtra("accountId", selectedAccount.getId());
                    intent.putExtra("outwardItemsList", outwardItemsList);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
                break;
            default:
                //do ur code;
        }
    }
    private void CallGetOutwardItemDetailApi(int outwardId, int accountId) {
        rlProgress.setVisibility(View.VISIBLE);
        Call<DetailOutwardResponseModel> call = RetrofitInstance.getApiInterface().getOutwardItemDetail(outwardId, accountId);
        call.enqueue(new Callback<DetailOutwardResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DetailOutwardResponseModel> call, @NonNull Response<DetailOutwardResponseModel> response) {
                rlProgress.setVisibility(View.GONE);
                detailOutwardResponseModel = response.body();
                assert detailOutwardResponseModel != null;
                saveOutwardRequestModel = detailOutwardResponseModel.getData().getInwardDetails();
                setData();
            }

            @Override
            public void onFailure(@NonNull Call<DetailOutwardResponseModel> call, @NonNull Throwable t) {
                rlProgress.setVisibility(View.GONE);
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        if (mode.equals("add")) {
            tvTitle.setText("Add Outward");
        } else {
            tvTitle.setText("Edit Outward");
        }
        if (mode.equals("edit")) {

            tvDate.setEnabled(false);
            tvParty.setEnabled(false);

            selectedAccount.setId(saveOutwardRequestModel.getAccountId());
            selectedAccount.setName(saveOutwardRequestModel.getBroker());
            selectedAccount.setPersonId(Utils.getPersonalInfo(context).getPersonId());
            outwardNumber = saveOutwardRequestModel.getOutwardNo();
            outwardDate = saveOutwardRequestModel.getOutwardOn();
            outwardItemsList = saveOutwardRequestModel.getOutwardsInwardItems();
            outwardDetailListAdapter.setList(outwardItemsList);
            outwardDetailListAdapter.notifyDataSetChanged();
            transporter = saveOutwardRequestModel.getTransporter();
            if (transporter != null) {
                edt_vehicleNo.setText(Utils.ifIsStringNull(transporter.getVehicleNo()));
                edt_driverName.setText(Utils.ifIsStringNull(transporter.getDriverName()));
                edt_driverNo.setText(Utils.ifIsStringNull(transporter.getDriverContactNumber()));
                edt_transporter.setText(Utils.ifIsStringNull(transporter.getTransporterDetail()));
                edt_remark.setText(Utils.ifIsStringNull(transporter.getRemarks()));
            } else {
                transporter = new Transporter();
            }
        }

        initCache();
    }


    private void CallSaveOutwardApi() {
        rlProgress.setVisibility(View.VISIBLE);
        Call<SaveOutwardResponseModel> call = RetrofitInstance.getApiInterface().saveOutwardItems(saveOutwardRequestModel);
        call.enqueue(new Callback<SaveOutwardResponseModel>() {
            @Override
            public void onResponse(Call<SaveOutwardResponseModel> call, Response<SaveOutwardResponseModel> response) {
                rlProgress.setVisibility(View.GONE);
                SaveOutwardResponseModel saveOutwardResponseModel = new SaveOutwardResponseModel();
                saveOutwardResponseModel = response.body();
                if (saveOutwardResponseModel.isValid()) {
                    Utils.ShowSnakBar(saveOutwardResponseModel.getMessage(), rlMain, context);
                }
            }

            @Override
            public void onFailure(Call<SaveOutwardResponseModel> call, Throwable t) {
                rlProgress.setVisibility(View.GONE);
            }
        });


    }

    private void AddDataInRequestModel() {
        Transporter transporter = new Transporter();
        transporter.setDriverContactNumber(edt_driverNo.getText().toString());
        transporter.setTransporterDetail(edt_transporter.getText().toString());
        transporter.setDriverName(edt_driverName.getText().toString());
        transporter.setRemarks(edt_remark.getText().toString());
        transporter.setVehicleNo(edt_vehicleNo.getText().toString());
        saveOutwardRequestModel = new SaveOutwardRequestModel(outwardItemsList, selectedAccount.getId(), selectedAccount.getName(),
                null, false, 0, tvOutwardNo.getText().toString(), 0.0, transporter,
                "02-15-2019", 0.0);
    }

    private void initItemRecyclerView() {
        rec_view.setLayoutManager(new LinearLayoutManager(this));
        rec_view.setHasFixedSize(true);
        outwardDetailListAdapter = new OutwardDetailListAdapter(AddEditOutwardActivity.this, true);
        rec_view.setAdapter(outwardDetailListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConstant.SEARCH_ACTIVITY_REQUEST_CODE)
            if (resultCode == Activity.RESULT_OK) {
                String type = data.getStringExtra("type");
                if (type.equals("party")) {
                    SearchTextViewModel searchTextViewModel = (SearchTextViewModel) data.getSerializableExtra("search");
                    setParty(searchTextViewModel);
                }
            }
    }

    private void setParty(SearchTextViewModel searchTextViewModel) {
        for (int i = 0; i < lstAccount.size(); i++) {
            if (searchTextViewModel.getId() == lstAccount.get(i).getId()) {
                selectedAccount = lstAccount.get(i);
                tvParty.setText(selectedAccount.getName());
                return;
            }
        }
    }

    private void initCache() {
        GenerateInwardNumber();
        if (outwardDate.isEmpty()) {
            outwardDate = Utils.getTodayDate();
        }
        tvDate.setText(outwardDate);
        if (selectedAccount.getName() != null) {
            tvParty.setText(selectedAccount.getName());
        }
    }

    public void GenerateInwardNumber() {
        if (outwardNumber.isEmpty()) {
            Random r = new Random();
            outwardNumber = String.format("%06d", (Object) Integer.valueOf(r.nextInt(100000)));
        }
        tvOutwardNo.setText(outwardNumber);
    }

    private void getData() {
        vehicleNo = edt_vehicleNo.getText().toString();
        transporterName = edt_transporter.getText().toString();
        driverName = edt_driverName.getText().toString();
        driverNo = edt_driverNo.getText().toString();
        remark = edt_remark.getText().toString();
    }

    private boolean validateField(String vehicleNo, String transporterName, String driverName, String driverNo, String remark) {

        if (!ValidationUtil.isValidVehicleNumber(vehicleNo)) {

            Utils.ShowSnakBar("Please Enter Valid Vehicle No", scrollView, AddEditOutwardActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidName(transporterName)) {

            Utils.ShowSnakBar("Please Enter Valid Name", scrollView, AddEditOutwardActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidName(driverName)) {
            Utils.ShowSnakBar("Please Enter Valid Name", scrollView, AddEditOutwardActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidPhoneNumber(driverNo)) {
            Utils.ShowSnakBar("Please Enter Valid Mobile No", scrollView, AddEditOutwardActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidRemark(remark)) {
            Utils.ShowSnakBar("Lenghth must be more then 10 ", scrollView, AddEditOutwardActivity.this);
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mode.equals("edit")) {
            selectedAccount = new Account();
            outwardNumber = "";
            outwardDate = "";
            outwardItemsList.clear();
        }
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void initComp() {
        rec_view = findViewById(R.id.rec_view);
        ivBack = findViewById(R.id.ivBack);
        edt_vehicleNo = findViewById(R.id.edt_vehicleNo);
        edt_transporter = findViewById(R.id.edt_transporter);
        edt_driverName = findViewById(R.id.edt_driverName);
        edt_driverNo = findViewById(R.id.edt_driverNo);
        edt_remark = findViewById(R.id.edt_remark);
        txtSave = findViewById(R.id.txt_save);
        txtAddItem = findViewById(R.id.txt_additem);
        rlMain = findViewById(R.id.rlMain);
        tvTitle = findViewById(R.id.tvTitle);
        pbParty = findViewById(R.id.pbParty);
        tvOutwardNo = findViewById(R.id.tvOutwardNo);
        tvDate = findViewById(R.id.tvDate);
        tvParty = findViewById(R.id.tvParty);
        rlProgress = findViewById(R.id.rlProgress);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}