package tech.fraction.webapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
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
import tech.fraction.webapp.rest.ApiResponseModel.SaveOutwardResponseModel;
import tech.fraction.webapp.rest.CommonApiCall.AccountApiCall;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.AppConstant;
import tech.fraction.webapp.util.Utils;
import tech.fraction.webapp.util.ValidationUtil;

public class AddEditOutwardActivity extends AppCompatActivity {

    RecyclerView rec_view;
    OutwardDetailListAdapter outwardDetailListAdapter;
    ImageView ivBack;
    Activity context;
    Retrofit retrofit;
    ProgressBar pbParty;
    ApiInterface apiInterface;
    EditText edt_vehicleNo, edt_transporter, edt_driverName, edt_driverNo, edt_remark;
    TextView txtSave, txtAddItem, tvTitle, tvOutwardNo, tvDate, tvParty;
    String vehicleNo, transporterName, driverName, driverNo, remark;
    RelativeLayout scrollView, rlAddEditOutward;
    SaveOutwardRequestModel saveOutwardRequestModel;
    public static ArrayList<OutwardDetails> outwardItemsList = new ArrayList<>();

    ArrayList<Account> lstAccount = new ArrayList<>();
    private static Account selectedAccount;
    private static String outwardNumber = "", outwardDate = "";

    @Override
    protected void onResume() {
        super.onResume();
        outwardDetailListAdapter.setList(outwardItemsList);
        outwardDetailListAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_outward_detail);


        initComp();

        initCache();

        initItemRecyclerView();


        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        getData();

        context = this;

        retrofit = RetrofitInstance.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        String mode = getIntent().getStringExtra("mode");
        if (mode.equals("add")) {
            tvTitle.setText("Add Outward");
        } else {
            tvTitle.setText("Edit Outward");
        }

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
        tvParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SearchTextViewModel> itemsList = new ArrayList<>();
                for (int i = 0; i < lstAccount.size(); i++) {
                    itemsList.add(new SearchTextViewModel(lstAccount.get(i).getId(), lstAccount.get(i).getName()));
                }
                Intent i = new Intent(AddEditOutwardActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", itemsList);
                i.putExtra("type", "party");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                AddDataInRequestModel();
               // validateField(vehicleNo, transporterName, driverName, driverNo, remark);
                CallSaveOutwardApi();
                Utils.hideKeyboard(AddEditOutwardActivity.this);

            }
        });
        txtAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAccount == null) {
                    Utils.ShowSnakBar("Select Party", rlAddEditOutward, context);
                } else {
                    Intent intent = new Intent(AddEditOutwardActivity.this, SelectedOutwardActivity.class);
                    intent.putExtra("mode", "add");
                    intent.putExtra("accountId", selectedAccount.getId());
                    intent.putExtra("outwardItemsList",outwardItemsList);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
//        outwardDetailListAdapter.setOnItemClickListener(new OutwardDetailListAdapter.OnClickListener() {
//            @Override
//            public void onClick(int position, int witch) {
//                if (witch == 1) {
//                    Intent intent = new Intent(context, AddEditOutItemActivity.class);
//                    intent.putExtra("mode", "edit");
//                    intent.putExtra("item", outwardItemsList.get(position));
//                    intent.putExtra("position", String.valueOf(position));
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                } else if (witch == 2) {
//                    outwardItemsList.remove(position);
//                    outwardDetailListAdapter.setList(outwardItemsList);
//                    outwardDetailListAdapter.notifyDataSetChanged();
//                }
//            }
//        });


    }

    private void CallSaveOutwardApi() {

        Call<SaveOutwardResponseModel> call=apiInterface.saveOutwardItems(saveOutwardRequestModel);
        call.enqueue(new Callback<SaveOutwardResponseModel>() {
            @Override
            public void onResponse(Call<SaveOutwardResponseModel> call, Response<SaveOutwardResponseModel> response) {
                SaveOutwardResponseModel saveOutwardResponseModel=new SaveOutwardResponseModel();
                saveOutwardResponseModel=response.body();
            }

            @Override
            public void onFailure(Call<SaveOutwardResponseModel> call, Throwable t) {
                Log.d("Failure","======>");

            }
        });


    }

    private void AddDataInRequestModel() {
        Transporter transporter=new Transporter();
        transporter.setDriverContactNumber(edt_driverNo.getText().toString());
        transporter.setTransporterDetail(edt_transporter.getText().toString());
        transporter.setDriverName(edt_driverName.getText().toString());
        transporter.setRemarks(edt_remark.getText().toString());
        transporter.setVehicleNo(edt_vehicleNo.getText().toString());
        saveOutwardRequestModel=new SaveOutwardRequestModel(outwardItemsList,selectedAccount.getId(),selectedAccount.getName(),
                null,false,0,tvOutwardNo.getText().toString(),0.0,transporter,
                tvDate.getText().toString(),0.0);


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
        if (selectedAccount != null) {
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
        rlAddEditOutward = findViewById(R.id.rlAddEditOutward);
        tvTitle = findViewById(R.id.tvTitle);
        pbParty = findViewById(R.id.pbParty);
        tvOutwardNo = findViewById(R.id.tvOutwardNo);
        tvDate = findViewById(R.id.tvDate);
        tvParty = findViewById(R.id.tvParty);


    }
}