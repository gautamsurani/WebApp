package tech.fraction.webapp.rest.CommonApiCall;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.model.Account;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;

public class AccountApiCall {
    Retrofit retrofit = RetrofitInstance.getClient();
    ApiInterface apiInterface = retrofit.create(ApiInterface.class);
    ArrayList<Account> lstAccount = new ArrayList<Account>();
    private DataInterface mListener;

    public AccountApiCall() {
        super();
    }

    private class AccountAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... strings) {

            Call<AccountResponseModel> call = apiInterface.getAllAccount();
            call.enqueue(new Callback<AccountResponseModel>() {
                @Override
                public void onResponse(Call<AccountResponseModel> call, Response<AccountResponseModel> response) {


                    if (response != null && response.body() != null && mListener != null) {
                        mListener.responseData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<AccountResponseModel> call, Throwable t) {
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

    public void CallAccountApi() {

        new AccountAsync().execute();


    }

    public void setOnDataListener(DataInterface listener) {
        mListener = listener;
    }

    public interface DataInterface {
        void responseData(AccountResponseModel accountResponseModel);
    }


}

