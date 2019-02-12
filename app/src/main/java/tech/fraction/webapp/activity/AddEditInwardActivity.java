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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
import tech.fraction.webapp.model.Account;
import tech.fraction.webapp.model.AddEditInwardModel;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.InwardVehicleDetail;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.SaveInwardResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.AppConstant;
import tech.fraction.webapp.util.Utils;

public class AddEditInwardActivity extends AppCompatActivity {

    RecyclerView rec_view;
    RelativeLayout rl_editInward;
    InwardItemAdapter inwardItemAdapter;
    ImageView ivBack;
    TextView tvAddItem, tvInwardNo, tvDate;
    Activity context;
    ProgressBar pbParty;
    TextView tvParty, tvTitle, tvSave;
    ApiInterface apiInterface;
    Retrofit retrofit;
    EditText etVehicleNo, etTransporter, etDriverName, etDriverNo, etRemark;
    AddEditInwardModel addEditInwardModel;

    public static List<InwardItems> inwardItems = new ArrayList<>();

    ArrayList<Account> accounts = new ArrayList<>();
    private static Account selectedAccount;
    private static String inwardNumber = "", inwardDate = "";

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


        retrofit = RetrofitInstance.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        initComp();

        initCache();

        initItemRecyclerView();

        String mode = getIntent().getStringExtra("mode");
        if (mode.equals("add")) {
            tvTitle.setText("Add Inward");
        } else {
            tvTitle.setText("Edit Inward");
        }

        tvAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddEditInItemActivity.class);
                intent.putExtra("mode", "add");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

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

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(inwardItems.size()==0)
                {
                    Utils.ShowSnakBar("Add atleast one item",rl_editInward,context);
                    return;
                }
                InwardVehicleDetail inwardVehicleDetail = new InwardVehicleDetail("", etVehicleNo.getText().toString(),
                        etTransporter.getText().toString(), etRemark.getText().toString(), etDriverNo.getText().toString(),
                        etDriverName.getText().toString(), 0, 0);

                addEditInwardModel = new AddEditInwardModel(null, false, inwardItems,
                        selectedAccount.getId(), "", tvDate.getText().toString(), null, inwardVehicleDetail, null,
                        false, null, null, null, null, null,
                        0, null, Utils.getPersonalInfo(context).getPersonId());*/

                CallAddInwardApi();
            }
        });

        tvParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SearchTextViewModel> itemsList = new ArrayList<>();
                for (int i = 0; i < accounts.size(); i++) {
                    itemsList.add(new SearchTextViewModel(accounts.get(i).getId(), accounts.get(i).getName()));
                }
                Intent i = new Intent(AddEditInwardActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", itemsList);
                i.putExtra("type", "party");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
        });

        if (accounts.size() == 0) {
            new AccountAsync().execute();
        }

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(tvDate);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void CallAddInwardApi() {

        String s = "{\"Invoices\":null,\"InwardVehicleDetail\":{\"InwardDetail\":null,\"Id\":2223,\"InwardDetailId\":3334,\"VehicleNo\":\"34535\",\"TransporterDetail\":\"Arbuda\",\"DriverName\":\"Mahes \",\n" +
                "\"DriverContactNumber\":\"6589415256\",\"Remarks\":\"test\"},\"Id\":3334,\"Number\":\"704275\",\"AccountId\":14,\"InwardedOn\":\"\\/Date(1549630862000)\\/\",\"AddedBy\":1,\"LastInvoiceGeneratedOn\":null,\n" +
                "\"LastInvoiceFromDate\":null,\"LastInvoiceToDate\":null,\"PaidStatus\":null,\"LastPaidAmount\":null,\"TotalPaidAmount\":null,\"LastInvoicePaidOn\":null,\"Broker\":\"Pritambhai Vasantmal\",\n" +
                "\"CanGenerateInvoice\":false,\"IsModified\":false,\"InwardItemDetailPoco\":[{\"ItemName\":\"Val\",\"UnitId\":2,\"RentPerUnit\":10.0000,\"UnloadingCharges\":50.0000,\"Label\":\"krishna\",\n" +
                "\"OutwardDetailId\":0,\"OutwardId\":0,\"LoadingCharges\":0,\"OtherCharges\":0,\"RawId\":1,\"AccountId\":0,\"InwardDetailId\":3334,\"InwardItemDetailId\":5218,\"InwardNo\":null,\"ItemId\":5,\n" +
                "\"UnitName\":\"50kg bag\",\"Quantity\":50,\"Stock\":0,\"Weight\":50,\"OutwardWeight\":0,\"OutwardQuantity\":0,\"TotalOutwardQuantity\":0,\"Location\":null,\"IsModified\":false,\n" +
                "\"InwardedOn\":\"\\/Date(-62135596800000)\\/\",\"InwardDetail\":null,\"OutwardDetails\":null,\"InwardItemLocationPoco\":[{\"RawId\":1,\"Id\":13879,\"InwardItemDetailId\":5218,\"RackId\":6,\n" +
                "\"FloorId\":1,\"ChamberId\":1,\"RackName\":\"A103\",\"InwardItemDetail\":null,\"Rack\":null},{\"RawId\":1,\"Id\":13880,\"InwardItemDetailId\":5218,\"RackId\":39,\"FloorId\":2,\"ChamberId\":1,\n" +
                "\"RackName\":\"A202\",\"InwardItemDetail\":null,\"Rack\":null},{\"RawId\":1,\"Id\":13881,\"InwardItemDetailId\":5218,\"RackId\":452,\"FloorId\":1,\"ChamberId\":1,\"RackName\":\"A101\",\n" +
                "\"InwardItemDetail\":null,\"Rack\":null}]}]}";
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(s).getAsJsonObject();

        Call<SaveInwardResponseModel> call = apiInterface.saveInwardJson(jsonObject);

        call.enqueue(new Callback<SaveInwardResponseModel>() {
            @Override
            public void onResponse(Call<SaveInwardResponseModel> call, Response<SaveInwardResponseModel> response) {
                SaveInwardResponseModel saveInwardResponseModel = response.body();
                Log.d("", "===>" + response.body().toString());

            }

            @Override
            public void onFailure(Call<SaveInwardResponseModel> call, Throwable t) {
                Log.d("", "===>failure");


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
        for (int i = 0; i < accounts.size(); i++) {
            if (searchTextViewModel.getId() == accounts.get(i).getId()) {
                selectedAccount = accounts.get(i);
                tvParty.setText(selectedAccount.getName());
                return;
            }
        }
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

            Call<AccountResponseModel> call = apiInterface.getAllAccount();
            call.enqueue(new Callback<AccountResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<AccountResponseModel> call, @NonNull Response<AccountResponseModel> response) {
                    AccountResponseModel accountResponseModel = new AccountResponseModel();
                    accountResponseModel = response.body();
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
                    Log.d("fsd", "fail");
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
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
