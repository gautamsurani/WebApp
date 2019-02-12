package tech.fraction.webapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.InwardItemAdapter;
import tech.fraction.webapp.adapter.OutwardDetailListAdapter;
import tech.fraction.webapp.model.Account;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.OutwardDetailModel;
import tech.fraction.webapp.model.OutwardItems;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.CommonApiCall.AccountApiCall;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;
import tech.fraction.webapp.util.ValidationUtil;

public class AddEditOutwardActivity extends AppCompatActivity {

    RecyclerView rec_view;
    OutwardDetailListAdapter outwardDetailListAdapter;
    List<OutwardDetailModel> outwardDetailList;
    ImageView ivBack;
    Context context;
    Retrofit retrofit;
    ProgressBar  pbParty;
    ApiInterface apiInterface;
    EditText edt_vehicleNo, edt_transporter, edt_driverName, edt_driverNo, edt_remark;
    TextView txtSave, txtAddItem,tvTitle;
    String vehicleNo, transporterName, driverName, driverNo, remark;
    RelativeLayout scrollView;
    public static List<OutwardItems> outwardItemsList = new ArrayList<>();

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

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        getData();

        context=this;

        retrofit = RetrofitInstance.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        String mode = getIntent().getStringExtra("mode");
        if (mode.equals("add")) {
            tvTitle.setText("Add Outward");
        } else {
            tvTitle.setText("Edit Outward");
        }

        if(lstAccount.size()==0)
        {
            pbParty.setVisibility(View.VISIBLE);

            AccountApiCall sampleClass = new AccountApiCall();
            sampleClass.setOnDataListener(new AccountApiCall.DataInterface() {
                @Override
                public void responseData(AccountResponseModel accountResponseModel) {
                    lstAccount=accountResponseModel.getAccount();
                    pbParty.setVisibility(View.INVISIBLE);
                }
            });
            sampleClass.CallAccountApi();
        }

//
//        outwardDetailList = new ArrayList<OutwardDetailModel>();
//        outwardDetailList.add(new OutwardDetailModel("DhaniAkhi[Locale Bardhan]", "A304,A304,A304", "1500", "100/200"));
//        outwardDetailList.add(new OutwardDetailModel("DhaniAkhi[Locale Bardhan]", "A304,A304,A304", "1500", "100/200"));
//        outwardDetailListAdapter = new OutwardDetailListAdapter(AddEditOutwardActivity.this, true);
//        rec_view.setLayoutManager(new LinearLayoutManager(this));
//        rec_view.setHasFixedSize(true);
//        outwardDetailListAdapter.setList(outwardDetailList);
//        rec_view.setAdapter(outwardDetailListAdapter);

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
                validateField(vehicleNo, transporterName, driverName, driverNo, remark);
                Utils.hideKeyboard(AddEditOutwardActivity.this);

            }
        });
        txtAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEditOutwardActivity.this, AddEditOutItemActivity.class);
                intent.putExtra("mode", "add");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        outwardDetailListAdapter.setOnItemClickListener(new OutwardDetailListAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                if (witch == 1) {
                    Intent intent = new Intent(context, AddEditOutItemActivity.class);
                    intent.putExtra("mode", "edit");
                    intent.putExtra("item", outwardItemsList.get(position));
                    intent.putExtra("position", String.valueOf(position));
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else if (witch == 2) {
                    outwardItemsList.remove(position);
                    outwardDetailListAdapter.setList(outwardItemsList);
                    outwardDetailListAdapter.notifyDataSetChanged();
                }
            }
        });


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
        scrollView = findViewById(R.id.edt_out_det_act);
        txtAddItem = findViewById(R.id.txt_additem);
        tvTitle = findViewById(R.id.tvTitle);
        pbParty = findViewById(R.id.pbParty);

    }
}