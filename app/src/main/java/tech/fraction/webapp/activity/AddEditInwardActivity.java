package tech.fraction.webapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.InwardItemAdapter;
import tech.fraction.webapp.base.BaseActivity;
import tech.fraction.webapp.base.NoNetworkActivity;
import tech.fraction.webapp.model.Account;
import tech.fraction.webapp.model.InwardDetailsModel;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.InwardVehicleDetail;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiRequestModel.SaveInwardRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.DetailInwardResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.SaveInwardResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.AppConstant;
import tech.fraction.webapp.util.Utils;

import static tech.fraction.webapp.util.AppConstant.NO_NETWORK_REQUEST_CODE;

public class AddEditInwardActivity extends BaseActivity implements View.OnClickListener {

    RecyclerView rec_view;

    RelativeLayout rl_editInward, rlProgress;

    InwardItemAdapter inwardItemAdapter;

    ImageView ivBack, tvAddItem;

    TextView tvInwardNo, tvDate, tvParty, tvTitle, tvSave;

    Activity context;

    ProgressBar pbParty;

    EditText etVehicleNo, etTransporter, etDriverName, etDriverNo, etRemark;

    public static List<InwardItems> inwardItems = new ArrayList<>();

    SaveInwardRequestModel saveInwardRequestModel;

    InwardDetailsModel inwardDetailsModel;

    ArrayList<Account> accounts = new ArrayList<>();

    private static Account selectedAccount = new Account();

    public static String inwardNumber = "", inwardDate = "";

    String mode = "";

    InwardVehicleDetail inwardVehicleDetail;

    int inwardDetailId;

    DetailInwardResponseModel detailInwardResponseModel = new DetailInwardResponseModel();

    int inwardVehicalId = 0, inwardDetailId1 = 0;

    @Override
    protected void onResume() {
        super.onResume();
        inwardItemAdapter.setList(inwardItems);
        inwardItemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inward_detail);

        context = this;

        initComp();

        initItemRecyclerView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mode = bundle.getString("mode", "");
            inwardDetailId = bundle.getInt("inwardItemDetailId", -1);
        }

        if (mode.equals("add")) {
            tvTitle.setText("Add Inward");
            initCache();
            if (accounts.size() == 0) {
                new AccountAsync().execute();
            }
        } else {
            tvTitle.setText("Edit Inward");
            CallGetInwardItemDetailApi(inwardDetailId, Utils.getPersonalInfo(context).getAccountId());
        }

        tvAddItem.setOnClickListener(this);

        inwardItemAdapter.setOnItemClickListener(new InwardItemAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                if (witch == 1) {
                    Intent intent = new Intent(context, AddEditInItemActivity.class);
                    intent.putExtra("mode", "edit");
                    intent.putExtra("item", inwardItems.get(position));
                    intent.putExtra("position", String.valueOf(position));
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else if (witch == 2) {
                    inwardItems.remove(position);
                    inwardItemAdapter.setList(inwardItems);
                    inwardItemAdapter.notifyDataSetChanged();
                }
            }
        });

        tvSave.setOnClickListener(this);

        tvParty.setOnClickListener(this);


        tvDate.setOnClickListener(this);

        ivBack.setOnClickListener(this);
    }


    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.tvAddItem:
                Intent intent = new Intent(context, AddEditInItemActivity.class);
                intent.putExtra("mode", "add");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.tvSave:
                if (selectedAccount.getName() == null) {
                    Utils.ShowSnakBar("Please select party name", rl_editInward, context);
                    return;
                }

                if (inwardItems.size() == 0) {
                    Utils.ShowSnakBar("Add atleast one item", rl_editInward, context);
                    return;
                }

                if (mode.equals("add")) {
                    InwardVehicleDetail inwardVehicleDetail = new InwardVehicleDetail("", etVehicleNo.getText().toString(),
                            etTransporter.getText().toString(), etRemark.getText().toString(), etDriverNo.getText().toString(),
                            etDriverName.getText().toString(), 0, 0);

                    saveInwardRequestModel = new SaveInwardRequestModel(inwardNumber, selectedAccount.getId()
                            , inwardVehicleDetail
                            , tvDate.getText().toString()
                            , Utils.getPersonalInfo(context).getPersonId()
                            , inwardItems
                            , 0);
                } else {

                    InwardVehicleDetail inwardVehicleDetail = new InwardVehicleDetail("", etVehicleNo.getText().toString(), etTransporter.getText().toString()
                            , etRemark.getText().toString(), etDriverNo.getText().toString(), etDriverName.getText().toString(), inwardVehicalId,
                            inwardDetailId1);

                    saveInwardRequestModel = new SaveInwardRequestModel(inwardNumber,
                            selectedAccount.getId(),
                            inwardVehicleDetail,
                            tvDate.getText().toString(),
                            Utils.getPersonalInfo(context).getPersonId(),
                            inwardItems,
                            inwardDetailId1);


                }
                if (!global.isNetworkAvailable()) {
                    retryInternet("CallAddInwardApi");
                    return;
                }

                CallAddInwardApi();
                break;
            case R.id.tvParty:
                ArrayList<SearchTextViewModel> itemsList = new ArrayList<>();
                for (int i = 0; i < accounts.size(); i++) {
                    itemsList.add(new SearchTextViewModel(accounts.get(i).getId(), accounts.get(i).getName()));
                }
                Intent i = new Intent(AddEditInwardActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", itemsList);
                i.putExtra("type", "party");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.tvDate:
                setDate(tvDate);
                break;
            case R.id.ivBack:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    public void retryInternet(String extraValue) {
        Intent i = new Intent(context, NoNetworkActivity.class);
        i.putExtra("extraValue", extraValue);
        startActivityForResult(i, NO_NETWORK_REQUEST_CODE);
    }

    private void CallGetInwardItemDetailApi(int inwardDetailId, int accountId) {
        rlProgress.setVisibility(View.VISIBLE);
        Call<DetailInwardResponseModel> call = RetrofitInstance.getApiInterface().getInwardItemDetail(inwardDetailId, accountId);
        call.enqueue(new Callback<DetailInwardResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<DetailInwardResponseModel> call, @NonNull Response<DetailInwardResponseModel> response) {
                rlProgress.setVisibility(View.GONE);
                detailInwardResponseModel = response.body();
                if (detailInwardResponseModel != null) {
                    inwardDetailsModel = new InwardDetailsModel();
                    inwardDetailsModel = detailInwardResponseModel.getData().getInwardDetails();
                    setData();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DetailInwardResponseModel> call, @NonNull Throwable t) {
                rlProgress.setVisibility(View.GONE);
                Log.d("fsd", "fdfdf");
            }
        });
    }

    private void setData() {

        tvDate.setEnabled(false);
        tvParty.setEnabled(false);

        selectedAccount.setId(inwardDetailsModel.getAccountId());
        selectedAccount.setName(inwardDetailsModel.getAccountName());
        selectedAccount.setPersonId(Utils.getPersonalInfo(context).getPersonId());
        inwardNumber = inwardDetailsModel.getNumber();
        inwardDate = inwardDetailsModel.getInwardDateinDDMMYYYY();
        inwardItems = inwardDetailsModel.getInwardItemDetailPoco();
        inwardItemAdapter.setList(inwardItems);
        inwardItemAdapter.notifyDataSetChanged();
        inwardVehicleDetail = inwardDetailsModel.getInwardVehicleDetail();
        if (inwardVehicleDetail != null) {
            etVehicleNo.setText(Utils.ifIsStringNull(inwardVehicleDetail.getVehicleNo()));
            etDriverName.setText(Utils.ifIsStringNull(inwardVehicleDetail.getDriverName()));
            etDriverNo.setText(Utils.ifIsStringNull(inwardVehicleDetail.getDriverContactNumber()));
            etTransporter.setText(Utils.ifIsStringNull(inwardVehicleDetail.getTransporterName()));
            etRemark.setText(Utils.ifIsStringNull(inwardVehicleDetail.getRemarks()));
        } else {
            inwardVehicleDetail = new InwardVehicleDetail();
        }

        inwardVehicalId = inwardVehicleDetail.getId();
        inwardDetailId1 = inwardVehicleDetail.getInwardDetailId();

        initCache();
    }


    private void CallAddInwardApi() {
        rlProgress.setVisibility(View.VISIBLE);

        Call<SaveInwardResponseModel> call = RetrofitInstance.getApiInterface().saveInward(saveInwardRequestModel);

        call.enqueue(new Callback<SaveInwardResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<SaveInwardResponseModel> call, @NonNull Response<SaveInwardResponseModel> response) {
                rlProgress.setVisibility(View.GONE);
                AppConstant.canResume = true;
                SaveInwardResponseModel saveInwardResponseModel = response.body();
                assert response.body() != null;
                assert saveInwardResponseModel != null;
                if (saveInwardResponseModel.getIsValid()) {
                    Utils.ShowSnakBar(saveInwardResponseModel.getMessage(), rl_editInward, context);
                }
                if (mode.equals("add")) {
                    clearCache();
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SaveInwardResponseModel> call, @NonNull Throwable t) {
                rlProgress.setVisibility(View.GONE);
            }
        });
    }

    public void setDate(final TextView textView) {
        Utils.hideKeyboard(context);
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd-MM-yyyy"; // In which you need put
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                textView.setText(sdf.format(myCalendar.getTime()));
                inwardDate = textView.getText().toString();
            }
        };

        DatePickerDialog d = new DatePickerDialog(context,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        d.setCancelable(false);
        d.show();
    }

    private void initCache() {
        GenerateInwardNumber();
        if (inwardDate.isEmpty()) {
            inwardDate = Utils.getTodayDate();
        }
        tvDate.setText(inwardDate);
        if (selectedAccount != null) {
            tvParty.setText(selectedAccount.getName());
        }
    }

    public void GenerateInwardNumber() {
        if (inwardNumber.isEmpty()) {
            Random r = new Random();
            inwardNumber = String.format("%06d", (Object) Integer.valueOf(r.nextInt(100000)));
        }
        tvInwardNo.setText(inwardNumber);
    }

    private void initItemRecyclerView() {
        inwardItemAdapter = new InwardItemAdapter(AddEditInwardActivity.this, true);
        rec_view.setLayoutManager(new LinearLayoutManager(this));
        rec_view.setHasFixedSize(true);
        rec_view.setAdapter(inwardItemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConstant.SEARCH_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String type = data.getStringExtra("type");
                if (type.equals("party")) {
                    SearchTextViewModel searchTextViewModel = (SearchTextViewModel) data.getSerializableExtra("search");
                    setParty(searchTextViewModel);
                }
            }
        } else if (requestCode == NO_NETWORK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String extraValue = data.getStringExtra("extraValue");
                if (extraValue.equalsIgnoreCase("CallAddInwardApi")) {
                    CallAddInwardApi();
                }
            }
        }
    }

    private void setParty(SearchTextViewModel searchTextViewModel) {
        for (int i = 0; i < accounts.size(); i++) {
            if (searchTextViewModel.getId() == accounts.get(i).getId()) {
                selectedAccount = accounts.get(i);
                tvParty.setText(selectedAccount.getName());
                return;
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class AccountAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbParty.setVisibility(View.VISIBLE);
            tvParty.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {

            Call<AccountResponseModel> call = RetrofitInstance.getApiInterface().getAllAccount();
            call.enqueue(new Callback<AccountResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<AccountResponseModel> call, @NonNull Response<AccountResponseModel> response) {
                    AccountResponseModel accountResponseModel = response.body();
                    assert accountResponseModel != null;
                    accounts = accountResponseModel.getAccount();
                    pbParty.setVisibility(View.INVISIBLE);
                    tvParty.setEnabled(true);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFailure(@NonNull Call<AccountResponseModel> call, @NonNull Throwable t) {
                    pbParty.setVisibility(View.INVISIBLE);
                    tvParty.setText("Fail to load party name");
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private void initComp() {
        rec_view = findViewById(R.id.rec_view);
        ivBack = findViewById(R.id.ivBack);
        tvAddItem = findViewById(R.id.tvAddItem);
        tvParty = findViewById(R.id.tvParty);
        pbParty = findViewById(R.id.pbParty);
        tvTitle = findViewById(R.id.tvTitle);
        tvInwardNo = findViewById(R.id.tvInwardNo);
        tvDate = findViewById(R.id.tvDate);
        tvSave = findViewById(R.id.tvSave);
        etVehicleNo = findViewById(R.id.etVehicleNo);
        etTransporter = findViewById(R.id.etTransporter);
        etDriverName = findViewById(R.id.etDriverName);
        etDriverNo = findViewById(R.id.etDriverNo);
        etRemark = findViewById(R.id.etRemark);
        rl_editInward = findViewById(R.id.rl_editInward);
        rlProgress = findViewById(R.id.rlProgress);
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ImageView ivBack = findViewById(R.id.ivBack);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mode.equals("edit")) {
            clearCache();
        }
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void clearCache() {
        selectedAccount = new Account();
        inwardNumber = "";
        inwardDate = "";
        inwardItems.clear();
    }
}
