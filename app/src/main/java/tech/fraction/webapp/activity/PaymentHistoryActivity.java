package tech.fraction.webapp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.PaymentHistoryAdapter;
import tech.fraction.webapp.base.BaseActivity;
import tech.fraction.webapp.base.NoNetworkActivity;
import tech.fraction.webapp.model.PastPaymentDetail;
import tech.fraction.webapp.rest.ApiResponseModel.MakeInvoiceAsPaidResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.PaymentHistoryResponsModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;

import static tech.fraction.webapp.util.AppConstant.NO_NETWORK_REQUEST_CODE;

public class PaymentHistoryActivity extends BaseActivity implements View.OnClickListener {


    TextView tvPartyName, tvInvNo, tvAmount, tvRemainingAmount, tvTotalAmount, tvPayNow, tvTitle, tvNoRecord;
    RecyclerView rvPaymentHistory;
    ImageView ivBack;
    Activity context;
    ArrayList<PastPaymentDetail> lstPaymentHistory = new ArrayList<>();
    int inwardId;
    PaymentHistoryResponsModel paymentHistoryResponsModel;
    LinearLayout ll_bottom;
    RelativeLayout rlMain;

    PaymentHistoryAdapter paymentHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        context = this;
        initComp();

        initRecyclerView();
        tvTitle.setText("Payment History");
        inwardId = getIntent().getIntExtra("inwardId", -1);
        ivBack.setOnClickListener(this);


        tvPayNow.setOnClickListener(this);

        paymentHistoryAdapter.setOnItemClickListener(new PaymentHistoryAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                callMarkAsUnpaidApi(position);


            }
        });
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivBack:

                onBackPressed();
                break;
            case R.id.tvPayNow:
                Intent intent = new Intent(context, PayNowActivity.class);
                intent.putExtra("inwardId", inwardId);
                intent.putExtra("remainingAmount", paymentHistoryResponsModel.getData().getPaidAmountDetail().getRemainingAmount());
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    private void callMarkAsUnpaidApi(final int position) {

        Call<MakeInvoiceAsPaidResponseModel> call = RetrofitInstance.getApiInterface().getInvoiceAsUnpaid(inwardId, 2,
                paymentHistoryResponsModel.getData().getPastPaymentDetail().get(position).getTransactionSettlementId()
        );
        call.enqueue(new Callback<MakeInvoiceAsPaidResponseModel>() {
            @Override
            public void onResponse(Call<MakeInvoiceAsPaidResponseModel> call, Response<MakeInvoiceAsPaidResponseModel> response) {
                MakeInvoiceAsPaidResponseModel makeInvoiceAsPaidResponseModel = response.body();
                if (makeInvoiceAsPaidResponseModel.isValid()) {

                    Utils.ShowSnakBar(makeInvoiceAsPaidResponseModel.getMessage(), rlMain, context);
                    lstPaymentHistory.remove(position);
                    if (lstPaymentHistory.size() == 0) {
                        tvNoRecord.setVisibility(View.VISIBLE);
                    }
                    paymentHistoryAdapter.setList(lstPaymentHistory);
                    paymentHistoryAdapter.notifyDataSetChanged();

                    tvAmount.setText(makeInvoiceAsPaidResponseModel.getData().getPaidAmountDetail().getPaidAmount() + " " + context.getResources().getString(R.string.rs));
                    tvTotalAmount.setText(makeInvoiceAsPaidResponseModel.getData().getPaidAmountDetail().getAmount() + " " + context.getResources().getString(R.string.rs));
                    tvRemainingAmount.setText("Remaining Amt: " + makeInvoiceAsPaidResponseModel.getData().getPaidAmountDetail().getRemainingAmount() + " " + context.getResources().getString(R.string.rs));


                }

            }


            @Override
            public void onFailure(Call<MakeInvoiceAsPaidResponseModel> call, Throwable t) {

                Log.d("dfff", "dff");
            }
        });
    }

    public void retryInternet(String extraValue) {
        Intent i = new Intent(context, NoNetworkActivity.class);
        i.putExtra("extraValue", extraValue);
        startActivityForResult(i, NO_NETWORK_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NO_NETWORK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String extraValue = data.getStringExtra("extraValue");
                if (extraValue.equalsIgnoreCase("paymentHistory")) {
                    CallPaymentHistoryApi();
                }
            }
        }
    }

    private void CallPaymentHistoryApi() {

        Call<PaymentHistoryResponsModel> call = RetrofitInstance.getApiInterface().getPaymentHistory(inwardId, 2);
        call.enqueue(new Callback<PaymentHistoryResponsModel>() {
            @Override
            public void onResponse(Call<PaymentHistoryResponsModel> call, Response<PaymentHistoryResponsModel> response) {
                paymentHistoryResponsModel = response.body();

                if (paymentHistoryResponsModel.isValid()) {
                    setData();
                    lstPaymentHistory = paymentHistoryResponsModel.getData().getPastPaymentDetail();
                    if (lstPaymentHistory.size() == 0) {
                        tvNoRecord.setVisibility(View.VISIBLE);
                    } else {
                        paymentHistoryAdapter.setList(lstPaymentHistory);
                        paymentHistoryAdapter.notifyDataSetChanged();
                        tvNoRecord.setVisibility(View.GONE);
                    }
                    if (paymentHistoryResponsModel.getData().getPaidAmountDetail().getRemainingAmount() == 0) {
                        ll_bottom.setVisibility(LinearLayout.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentHistoryResponsModel> call, Throwable t) {

                Log.d("ggdg", "fsf");
            }
        });
    }

    private void setData() {

        tvPartyName.setText(paymentHistoryResponsModel.getData().getPaidAmountDetail().getAccountName());
        tvInvNo.setText("INV" + paymentHistoryResponsModel.getData().getPaidAmountDetail().getReferenceNumber());
        tvAmount.setText(paymentHistoryResponsModel.getData().getPaidAmountDetail().getPaidAmount() + " " + context.getResources().getString(R.string.rs));
        tvTotalAmount.setText(paymentHistoryResponsModel.getData().getPaidAmountDetail().getAmount() + " " + context.getResources().getString(R.string.rs));
        tvRemainingAmount.setText("Remaining Amt: " + paymentHistoryResponsModel.getData().getPaidAmountDetail().getRemainingAmount() + " " + context.getResources().getString(R.string.rs));


    }

    private void initRecyclerView() {
        paymentHistoryAdapter = new PaymentHistoryAdapter(context);
        rvPaymentHistory.setLayoutManager(new LinearLayoutManager(context));
        rvPaymentHistory.setHasFixedSize(true);
        paymentHistoryAdapter.setList(lstPaymentHistory);

        rvPaymentHistory.setAdapter(paymentHistoryAdapter);
    }

    private void initComp() {

        tvPartyName = findViewById(R.id.tvPartyName);
        tvInvNo = findViewById(R.id.tvInvNo);
        tvAmount = findViewById(R.id.tvAmount);
        tvRemainingAmount = findViewById(R.id.tvRemainingAmount);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        tvPayNow = findViewById(R.id.tvPayNow);
        rvPaymentHistory = findViewById(R.id.rvPaymentHistory);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvNoRecord = findViewById(R.id.tvNoRecord);
        ll_bottom = findViewById(R.id.ll_bottom);
        rlMain = findViewById(R.id.rlMain);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!global.isNetworkAvailable()) {
            retryInternet("paymentHistory");
            return;
        }
        CallPaymentHistoryApi();


    }


}
