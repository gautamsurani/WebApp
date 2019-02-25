package tech.fraction.webapp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.fraction.webapp.R;
import tech.fraction.webapp.rest.ApiResponseModel.MakeInvoiceAsPaidResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;

public class PayNowActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvTitle, tvSave, tvClose, tvRemainingAmount, tvFromDate;
    ImageView ivBack;
    int inwardId;
    EditText etAmount;
    RadioGroup rgPayment;
    RadioButton rbFullyPaid, rbPartiallyPaid;
    Activity context;
    Double Amount;
    boolean paidStatus;
    String date;
    Double remainingAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);
        initComp();
        tvTitle.setText("Payment History");
        ivBack.setOnClickListener(this);
        inwardId = getIntent().getIntExtra("inwardId", -1);
        remainingAmount = getIntent().getDoubleExtra("remainingAmount", 0.0);
        context = this;
        tvFromDate.setText(Utils.getTodayDate());
        tvRemainingAmount.setText(String.valueOf(remainingAmount));
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
                CallMarkInvoiceAsPaid();


            }
        });
        etAmount.setText(String.valueOf(remainingAmount));
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.setDate(tvFromDate, context);
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

                break;
            default:
                break;

        }
    }


    private void CallMarkInvoiceAsPaid() {

        Call<MakeInvoiceAsPaidResponseModel> call = RetrofitInstance.getApiInterface().getInvoiceAsPaid(inwardId, 2,
                Amount, paidStatus, date);
        call.enqueue(new Callback<MakeInvoiceAsPaidResponseModel>() {
            @Override
            public void onResponse(Call<MakeInvoiceAsPaidResponseModel> call, Response<MakeInvoiceAsPaidResponseModel> response) {
                MakeInvoiceAsPaidResponseModel makeInvoiceAsPaidResponseModel = response.body();
                if (makeInvoiceAsPaidResponseModel.isValid()) {
                    final Dialog openDialog = new Dialog(context);
                    openDialog.setContentView(R.layout.customdialog_layout);
                    Window window = openDialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    openDialog.setTitle("Custom Dialog Box");
                    TextView tvOkay = (TextView) openDialog.findViewById(R.id.tvOkay);
                    TextView tvMessage = (TextView) openDialog.findViewById(R.id.tvMessage);
                    tvMessage.setText(makeInvoiceAsPaidResponseModel.getMessage());
                    tvOkay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openDialog.dismiss();
                            finish();


                        }
                    });
                    openDialog.show();
                }
            }

            @Override
            public void onFailure(Call<MakeInvoiceAsPaidResponseModel> call, Throwable t) {

                Log.d("dfff", "dff");
            }
        });
    }

    private void getData() {

        remainingAmount = Double.valueOf(tvRemainingAmount.getText().toString());
        Amount = Double.valueOf(etAmount.getText().toString());
        int radioButtonID = rgPayment.getCheckedRadioButtonId();
        date = tvFromDate.getText().toString();
        RadioButton radioButton = (RadioButton) rgPayment.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        if (selectedtext.equals("Partially Paid")) {
            paidStatus = true;
        } else {
            paidStatus = false;
        }


    }

    private void initComp() {

        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvSave = findViewById(R.id.tvSave);
        tvClose = findViewById(R.id.tvClose);
        tvRemainingAmount = findViewById(R.id.tvRemainingAmount);
        tvFromDate = findViewById(R.id.tvFromDate);
        etAmount = findViewById(R.id.etAmount);
        rgPayment = findViewById(R.id.rgPayment);
        rbFullyPaid = findViewById(R.id.rbFullyPaid);
        rbPartiallyPaid = findViewById(R.id.rbPartiallyPaid);
    }


}
