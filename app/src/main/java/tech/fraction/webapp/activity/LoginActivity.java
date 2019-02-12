package tech.fraction.webapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.base.BaseActivity;
import tech.fraction.webapp.base.NoNetworkActivity;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.LoginResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.rest.RetrofitInstance0ne;
import tech.fraction.webapp.util.Utils;
import tech.fraction.webapp.util.ValidationUtil;

import static tech.fraction.webapp.util.AppConstant.NO_NETWORK_REQUEST_CODE;

public class LoginActivity extends BaseActivity {

    TextView txtForgotPassword;
    TextView btnLogin;
    Activity context;
    EditText edtEmail, edtPassword;
    RelativeLayout rlLogin;
    Retrofit retrofit;
    String emailId, password;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        initComp();

        if (Utils.getPersonalInfo(context) != null) {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }

        retrofit = RetrofitInstance.getClient();

        apiInterface = retrofit.create(ApiInterface.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getData();
                if (!validateField(emailId, password)) {
                    return;
                }
                Utils.hideKeyboard(context);
                if (!global.isNetworkAvailable()) {
                    retryInternet("doLogin");
                    return;
                }
                callDoLoginAPI();
            }
        });
    }

    private void callDoLoginAPI() {
        startProgressDialog();
        Call<LoginResponseModel> call = apiInterface.doLogin(emailId, password);

        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                dismissProgressDialog();
                LoginResponseModel loginResponseModel = response.body();
                assert loginResponseModel != null;
                if (loginResponseModel.isValid()) {

                    Utils.setUserRights(context, loginResponseModel.getData().getUserRights());
                    Utils.setPersonalInfo(context, loginResponseModel.getData().getPersonInformation());
                    Utils.setMenu(context, loginResponseModel.getData().getMenu());

                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    Utils.ShowSnakBar(loginResponseModel.getMessage(), rlLogin, context);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                dismissProgressDialog();
                Utils.ShowSnakBar("Failure", rlLogin, context);
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
                if (extraValue.equalsIgnoreCase("doLogin")) {
                    callDoLoginAPI();
                }
            }
        }
    }

    private boolean validateField(String emailId, String password) {
        if (!ValidationUtil.isValidEmail(emailId)) {
            Utils.ShowSnakBar("Please Enter Valid Email", rlLogin, context);
            return false;
        }
        if (!ValidationUtil.isValidPassword(password)) {
            Utils.ShowSnakBar("Please Enter Valid Password", rlLogin, context);
            return false;
        }
        return true;
    }

    private void initComp() {
        txtForgotPassword = findViewById(R.id.txt_forgot_pwd);
        txtForgotPassword.setText(Html.fromHtml("<u>Forgot Password</u>"));
        btnLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_pwd);
        rlLogin = findViewById(R.id.rl_login);
    }

    private void getData() {
        emailId = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
    }
}