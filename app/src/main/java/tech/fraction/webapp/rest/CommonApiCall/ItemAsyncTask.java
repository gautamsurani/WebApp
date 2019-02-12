package tech.fraction.webapp.rest.CommonApiCall;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.fraction.webapp.SqliteDatabase.DbConstants;
import tech.fraction.webapp.SqliteDatabase.Sqlitehelper.SqLiteHelperFunctions;
import tech.fraction.webapp.SqliteDatabase.model.ItemRent;
import tech.fraction.webapp.SqliteDatabase.model.Items;
import tech.fraction.webapp.SqliteDatabase.model.Racks;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.ItemResoponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.RackResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.RentResponseModel;

public class ItemAsyncTask extends AsyncTask<String, String, String> {
        ApiInterface apiInterface;
        SqLiteHelperFunctions sqLiteHelperFunctions;
        private DataInterface mListener;
        boolean isItemFinished = true;
        boolean isUnitsFinished = true;
        boolean isRantFinished = true;
        boolean isRacksFinished = true;

        public void isAllFinish() {
            if (isItemFinished && isUnitsFinished && isRantFinished && isRacksFinished) {

                mListener.finishAll();
            }


        }


    public ItemAsyncTask(Context context){
            context = context;
            sqLiteHelperFunctions=new SqLiteHelperFunctions(context);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            if (!sqLiteHelperFunctions.getTableRecordCount(DbConstants.TABLE_ITEMS_NAME)) {
                isItemFinished = false;
                Call<ItemResoponseModel> call = apiInterface.getAllItems();
                call.enqueue(new Callback<ItemResoponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<ItemResoponseModel> call, @NonNull Response<ItemResoponseModel> response) {

                        isItemFinished = true;
                        isAllFinish();

                        ItemResoponseModel itemResoponseModel = new ItemResoponseModel();
                        itemResoponseModel = response.body();
                        Items items = new Items();

                        assert itemResoponseModel != null;
                        for (int i = 0; i < itemResoponseModel.getLstItem().size(); i++) {
                            items.setId(itemResoponseModel.getLstItem().get(i).getId());
                            items.setName(itemResoponseModel.getLstItem().get(i).getName());
                            items.setBillingType(itemResoponseModel.getLstItem().get(i).getBillingType());
                            items.setStatus(itemResoponseModel.getLstItem().get(i).getStatus());
                            boolean insert = sqLiteHelperFunctions.insertItems(items);
                            Log.d("fsd", "insert Item ====>" + insert + items.getId());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ItemResoponseModel> call, @NonNull Throwable t) {
                        isItemFinished = true;
                        isAllFinish();
                    }
                });
            }

            /*if (!sqLiteHelperFunctions.getTableRecordCount(DbConstants.TABLE_UNITS_NAME)) {
                isUnitsFinished = false;
                Call<UnitResponseModel> call = apiInterface.getAllUnits();
                call.enqueue(new Callback<UnitResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UnitResponseModel> call, @NonNull Response<UnitResponseModel> response) {
                        isUnitsFinished = true;
                        isAllFinish();
                        UnitResponseModel unitResponseModel = new UnitResponseModel();
                        unitResponseModel = response.body();
                        Units units = new Units();
                        assert unitResponseModel != null;
                        for (int i = 0; i < unitResponseModel.getLstUnit().size(); i++) {
                            units.setId(unitResponseModel.getLstUnit().get(i).getId());
                            units.setName(unitResponseModel.getLstUnit().get(i).getName());
                            units.setWeight(unitResponseModel.getLstUnit().get(i).getWeight());
                            units.setWeightUnit(unitResponseModel.getLstUnit().get(i).getWeightUnit());
                            boolean insert = sqLiteHelperFunctions.insertUnits(units);
                            Log.d("fsd", "insert units ====>" + insert + units.getId());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UnitResponseModel> call, @NonNull Throwable t) {
                        isUnitsFinished = true;
                        isAllFinish();
                        Log.d("fsd", "fail");

                    }
                });
            }*/

            if (!sqLiteHelperFunctions.getTableRecordCount(DbConstants.TABLE_RACKS_NAME)) {
                isRacksFinished = false;
                Call<RackResponseModel> call = apiInterface.getAllRacks();
                call.enqueue(new Callback<RackResponseModel>() {
                    @Override
                    public void onResponse(Call<RackResponseModel> call, Response<RackResponseModel> response) {
                        isRacksFinished = true;
                        isAllFinish();
                        RackResponseModel rackResponseModel = new RackResponseModel();
                        rackResponseModel = response.body();
                        Racks racks = new Racks();

                        for (int i = 0; i < rackResponseModel.getRack().size(); i++) {
                            racks.setId(rackResponseModel.getRack().get(i).getId());
                            racks.setName(rackResponseModel.getRack().get(i).getName());
                            racks.setChamberId(rackResponseModel.getRack().get(i).getChamberId());
                            racks.setFloorId(rackResponseModel.getRack().get(i).getFloorId());
                            racks.setStatus(rackResponseModel.getRack().get(i).getStatus());
                            boolean insert = sqLiteHelperFunctions.insertRacks(racks);
                            Log.d("fsd", "insert rack ====>" + insert + racks.getId());
                        }
                    }

                    @Override
                    public void onFailure(Call<RackResponseModel> call, Throwable t) {
                        isRacksFinished = true;
                        isAllFinish();
                        Log.d("fsd", "fail");
                    }
                });

            }

            if (!sqLiteHelperFunctions.getTableRecordCount(DbConstants.TABLE_RENT_NAME)) {
                isRantFinished = false;
                Call<RentResponseModel> call = apiInterface.getAllItemRents();
                call.enqueue(new Callback<RentResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<RentResponseModel> call, @NonNull Response<RentResponseModel> response) {
                        isRantFinished = true;
                        isAllFinish();
                        RentResponseModel rentResponseModel = new RentResponseModel();
                        rentResponseModel = response.body();
                        ItemRent itemRent = new ItemRent();

                        assert rentResponseModel != null;
                        for (int i = 0; i < rentResponseModel.getItemRent().size(); i++) {
                            itemRent.setId(rentResponseModel.getItemRent().get(i).getId());
                            itemRent.setUnitId(rentResponseModel.getItemRent().get(i).getUnitId());
                            itemRent.setItemId(rentResponseModel.getItemRent().get(i).getItemId());
                            itemRent.setItem(rentResponseModel.getItemRent().get(i).getItem());
                            itemRent.setRent(rentResponseModel.getItemRent().get(i).getRent());
                            itemRent.setUnit(rentResponseModel.getItemRent().get(i).getUnit());
                            boolean insert = sqLiteHelperFunctions.insertItemRent(itemRent);
                            Log.d("fsd", "insert itemrent ====>" + insert + itemRent.getId());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RentResponseModel> call, @NonNull Throwable t) {
                        isRantFinished = true;
                        isAllFinish();
                        Log.d("fsd", "fail");
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            isAllFinish();
        }


    public void setOnDataListener(DataInterface listener) {
        mListener = listener;
    }

    public interface DataInterface {
        void finishAll();
    }

}