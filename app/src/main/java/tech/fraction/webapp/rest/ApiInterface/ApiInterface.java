package tech.fraction.webapp.rest.ApiInterface;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import tech.fraction.webapp.model.Codebeautify;
import tech.fraction.webapp.rest.ApiRequestModel.InvoiceRequestModel;
import tech.fraction.webapp.rest.ApiRequestModel.InwardRequestModel;
import tech.fraction.webapp.rest.ApiRequestModel.OutwardRequestModel;
import tech.fraction.webapp.rest.ApiRequestModel.SaveInwardRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.CityResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.CountryResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.InvoiceResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.InwardResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.ItemResoponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.LoginResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.OutwardResoinseModel;
import tech.fraction.webapp.rest.ApiResponseModel.RackResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.RentResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.SaveInwardResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.StateResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.UnitResponseModel;

public interface ApiInterface {

    @POST("master/login")
    Call<LoginResponseModel> doLogin(@Query("Email") String email, @Query("Password") String password);

    @POST("master/InwradsWithPaging")
    Call<InwardResponseModel> getInward(@Body InwardRequestModel inwardRequestModel);

    @POST("inwradswithpaging")
    Call<String> getTestApi(@Body Codebeautify codebeautify);

    @GET("master/GetCountry")
    Call<CountryResponseModel> getAllCountry();

    @GET("master/GetStates")
    Call<StateResponseModel> getAllState();

    @GET("master/GetCities")
    Call<CityResponseModel> getAllCity();

    @GET("master/GetItems")
    Call<ItemResoponseModel> getAllItems();

    @GET("master/GetUnits")
    Call<UnitResponseModel> getAllUnits();

    @GET("master/GetItemRents")
    Call<RentResponseModel> getAllItemRents();

    @GET("master/GetAccounts")
    Call<AccountResponseModel> getAllAccount();

    @GET("master/GetRacks")
    Call<RackResponseModel> getAllRacks();

    @POST("master/OutwardsWithPaging")
    Call<OutwardResoinseModel> getAllOurward(@Body OutwardRequestModel outwardRequestModel);

    @POST("master/SaveInwardDetail")
    Call<SaveInwardResponseModel> saveInward(@Body SaveInwardRequestModel saveInwardRequestModel);



    @POST("master/invoiceswithpaging")
    Call<InvoiceResponseModel> getAllInvoice(@Body InvoiceRequestModel invoiceRequestModel);

}