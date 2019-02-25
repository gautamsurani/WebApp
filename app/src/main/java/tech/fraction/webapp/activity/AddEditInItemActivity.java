package tech.fraction.webapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.SqliteDatabase.DbConstants;
import tech.fraction.webapp.SqliteDatabase.Sqlitehelper.SqLiteHelperFunctions;
import tech.fraction.webapp.SqliteDatabase.model.ItemRent;
import tech.fraction.webapp.SqliteDatabase.model.Items;
import tech.fraction.webapp.SqliteDatabase.model.Racks;
import tech.fraction.webapp.adapter.RacksAdapter;
import tech.fraction.webapp.base.BaseActivity;
import tech.fraction.webapp.model.InwardItemLocationPoco;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.ItemResoponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.RackResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.RentResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.AppConstant;
import tech.fraction.webapp.util.Utils;

public class AddEditInItemActivity extends BaseActivity implements View.OnClickListener {

    RecyclerView rec_view;

    RacksAdapter racksAdapter;

    TextView tvHeading, spnItem, spnUnit, spnLocation, tvUpdate, tvCancel;
    ImageView ivBack;

    RelativeLayout rlProgress, rlMain;

    Activity context;

    SqLiteHelperFunctions sqLiteHelperFunctions;

    EditText etRent, etQuantity, etMarko, etUnloadingCharge;

    Items selectedItems = new Items();

    List<Items> items;

    ItemRent selectedItemRent = new ItemRent();

    List<ItemRent> itemRents;

    InwardItemLocationPoco selectedRacks = new InwardItemLocationPoco();

    List<InwardItemLocationPoco> selectedRacksList = new ArrayList<>();

    List<InwardItemLocationPoco> itemRacks = new ArrayList<>();

    InwardItems inwardItem;

    String mode;

    String quantity = "", marko = "", unloadingCharge = "", rentPerUnit = "";

    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        context = this;

        sqLiteHelperFunctions = new SqLiteHelperFunctions(context);

        initComp();

        getData();

        initRecyclerView();

        mode = getIntent().getStringExtra("mode");
        if (mode.equals("add")) {
            tvHeading.setText("Add Item");
        } else if (mode.equals("edit")) {

            tvHeading.setText("Edit Item");

            InwardItems item = (InwardItems) getIntent().getSerializableExtra("item");
            selectedPosition = Integer.parseInt(getIntent().getStringExtra("position"));

            etQuantity.setText(item.getQuantity() + "");
            // etMarko.setText(item.getMarkoName());
            etUnloadingCharge.setText(item.getUnloadingCharges() + "");
            selectedRacksList = item.getInwardLocationModel();
            if (selectedRacksList == null) {
                selectedRacksList = new ArrayList<>();
            }
            racksAdapter.setList(selectedRacksList);
            racksAdapter.notifyDataSetChanged();

            // selectedItems.setId(item.getItemId());
            selectedItems.setName(item.getItemName());
            spnItem.setText(item.getItemName());

            spnUnit.setText(item.getUnitName());
            etRent.setText(item.getRentPerUnit() + "");
            selectedItemRent.setUnit(item.getUnitName());
            // selectedItemRent.setUnitId(item.getUnitId());
            selectedItemRent.setRent(item.getRentPerUnit());
        }

        ivBack.setOnClickListener(this);

        spnItem.setOnClickListener(this);

        spnUnit.setOnClickListener(this);

        spnLocation.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvUpdate.setOnClickListener(this);

        new ItemAsync().execute();
    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.spn_item: {
                ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                items = sqLiteHelperFunctions.getAllItems();
                for (int i = 0; i < items.size(); i++) {
                    searchTextViewModels.add(new SearchTextViewModel(items.get(i).getId(), items.get(i).getName()));
                }
                Intent i = new Intent(AddEditInItemActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", searchTextViewModels);
                i.putExtra("type", "item");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
            break;

            case R.id.spn_unit: {
                if (selectedItems.getName() == null) {
                    Utils.ShowSnakBar("Please Select Item", rlMain, context);
                } else {
                    ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                    itemRents = sqLiteHelperFunctions.getItemRentByItemId(selectedItems.getId());
                    for (int i = 0; i < itemRents.size(); i++) {
                        searchTextViewModels.add(new SearchTextViewModel(itemRents.get(i).getId(), itemRents.get(i).getUnit()));
                    }
                    Intent i = new Intent(AddEditInItemActivity.this, SearchTextViewActivity.class);
                    i.putExtra("itemsList", searchTextViewModels);
                    i.putExtra("type", "unit");
                    startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
                }
            }
            break;

            case R.id.spn_location:
                ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                List<Racks> racks = sqLiteHelperFunctions.getAllRacks();
                itemRacks.clear();
                for (int i = 0; i < racks.size(); i++) {
                    InwardItemLocationPoco inwardItemLocationPoco = new InwardItemLocationPoco();
                    inwardItemLocationPoco.setRackId(racks.get(i).getId());
                    inwardItemLocationPoco.setRackName(racks.get(i).getName());
                    itemRacks.add(inwardItemLocationPoco);
                }

                for (int i = 0; i < itemRacks.size(); i++) {
                    searchTextViewModels.add(new SearchTextViewModel(itemRacks.get(i).getRackId(), itemRacks.get(i).getRackName()));
                }

                Intent i = new Intent(AddEditInItemActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", searchTextViewModels);
                i.putExtra("type", "location");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
                break;

            case R.id.tvCancel:
                onBackPressed();
                break;

            case R.id.tvUpdate:
                quantity = etQuantity.getText().toString();
                marko = etMarko.getText().toString();
                unloadingCharge = etUnloadingCharge.getText().toString();
                rentPerUnit = etRent.getText().toString();

                inwardItem = new InwardItems();

                if (selectedItems.getName() == null) {
                    Utils.ShowSnakBar("Please Select Item", rlMain, context);
                    return;
                }
                if (selectedItemRent.getUnit() == null) {
                    Utils.ShowSnakBar("Please Select Unit", rlMain, context);
                    return;
                }
                if (rentPerUnit.isEmpty()) {
                    Utils.ShowSnakBar("Please Enter Rent", rlMain, context);
                    return;
                }
                if (quantity.isEmpty()) {
                    Utils.ShowSnakBar("Please Enter Quantity", rlMain, context);
                    return;
                }
                if (unloadingCharge.isEmpty()) {
                    Utils.ShowSnakBar("Please Enter Unloading Charges", rlMain, context);
                    return;
                }
                if (selectedRacksList.size() == 0) {
                    Utils.ShowSnakBar("Please Select Location", rlMain, context);
                    return;
                }
                if (mode.equals("add")) {
                    inwardItem.setItemId(selectedItems.getId());
                    if (AddEditInwardActivity.inwardItems.size() == 0) {
                        inwardItem.setRawId(1);
                    } else {
                        int rawId = AddEditInwardActivity.inwardItems.get(AddEditInwardActivity.inwardItems.size() - 1).getRawId();
                        rawId = rawId + 1;
                        inwardItem.setRawId(rawId);
                    }

                    inwardItem.setItemName(selectedItems.getName());
                    inwardItem.setUnitName(selectedItemRent.getUnit());
                    inwardItem.setRentPerUnit(Double.parseDouble(rentPerUnit));
                    inwardItem.setQuantity(Integer.parseInt(quantity));
                    inwardItem.setUnloadingCharges(Integer.parseInt(unloadingCharge));
                    inwardItem.setInwardLocationModel(selectedRacksList);
                    inwardItem.setUnitId(selectedItemRent.getUnitId());
                    inwardItem.setLabel(selectedItems.getName());


                    inwardItem.setInwardDetailId(0);


                    inwardItem.setStock(0);

                    inwardItem.setLabel("aashu");

                    AddEditInwardActivity.inwardItems.add(inwardItem);
                } else {

                    AddEditInwardActivity.inwardItems.get(selectedPosition).setItemName(selectedItems.getName());

                    AddEditInwardActivity.inwardItems.get(selectedPosition).setUnitName(selectedItemRent.getUnit());
                    AddEditInwardActivity.inwardItems.get(selectedPosition).setRentPerUnit(Double.parseDouble(rentPerUnit));
                    AddEditInwardActivity.inwardItems.get(selectedPosition).setQuantity(Integer.parseInt(quantity));


                    AddEditInwardActivity.inwardItems.get(selectedPosition).setUnloadingCharges(Integer.parseInt(unloadingCharge));
                    AddEditInwardActivity.inwardItems.get(selectedPosition).setInwardLocationModel(selectedRacksList);
                }
                onBackPressed();
                break;

            default:
                break;
        }

    }

    private void getData() {


    }

    private void initRecyclerView() {
        racksAdapter = new RacksAdapter(AddEditInItemActivity.this);
        rec_view.setLayoutManager(new GridLayoutManager(this, 3));
        rec_view.setHasFixedSize(true);
        racksAdapter.setList(selectedRacksList);
        rec_view.setAdapter(racksAdapter);
        racksAdapter.setOnItemClickListener(new RacksAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                if (witch == 0) {
                    selectedRacksList.remove(position);
                    racksAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConstant.SEARCH_ACTIVITY_REQUEST_CODE)
            if (resultCode == Activity.RESULT_OK) {
                String type = data.getStringExtra("type");
                if (type.equals("item")) {
                    SearchTextViewModel searchTextViewModel = (SearchTextViewModel) data.getSerializableExtra("search");
                    setItem(searchTextViewModel);
                } else if (type.equals("unit")) {
                    SearchTextViewModel searchTextViewModel = (SearchTextViewModel) data.getSerializableExtra("search");
                    setUnitAndRent(searchTextViewModel);
                } else if (type.equals("location")) {
                    SearchTextViewModel searchTextViewModel = (SearchTextViewModel) data.getSerializableExtra("search");
                    setLocation(searchTextViewModel);
                }
            }
    }

    private void setItem(SearchTextViewModel searchTextViewModel) {
        for (int i = 0; i < items.size(); i++) {
            if (searchTextViewModel.getId() == items.get(i).getId()) {
                selectedItems = items.get(i);
                spnItem.setText(selectedItems.getName());
                selectedItemRent = new ItemRent();
                spnUnit.setText("");
                etRent.setText("");
                return;
            }
        }
    }

    private void setUnitAndRent(SearchTextViewModel searchTextViewModel) {
        for (int i = 0; i < itemRents.size(); i++) {
            if (searchTextViewModel.getId() == itemRents.get(i).getId()) {
                selectedItemRent = itemRents.get(i);
                spnUnit.setText(selectedItemRent.getUnit());
                etRent.setText(selectedItemRent.getRent() + "");
                return;
            }
        }
    }

    private void setLocation(SearchTextViewModel searchTextViewModel) {

        for (int i = 0; i < itemRacks.size(); i++) {
            if (searchTextViewModel.getId() == itemRacks.get(i).getRackId()) {
                selectedRacks = itemRacks.get(i);
            }
        }

        boolean isIn = false;
        if (selectedRacksList.size() == 0) {
            selectedRacks.setRawId(1);
            selectedRacksList.add(selectedRacks);
            racksAdapter.notifyDataSetChanged();
        } else {
            for (int i = 0; i < selectedRacksList.size(); i++) {
                if (selectedRacksList.get(i).getRackName().equals(selectedRacks.getRackName())) {
                    isIn = true;
                }
            }
            if (!isIn) {
                int selectedRawId = selectedRacksList.get(selectedRacksList.size() - 1).getRawId();
                selectedRawId = selectedRawId + 1;
                selectedRacks.setRawId(selectedRawId);
                selectedRacksList.add(selectedRacks);
                racksAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initComp() {
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        spnItem = findViewById(R.id.spn_item);
        spnLocation = findViewById(R.id.spn_location);
        spnUnit = findViewById(R.id.spn_unit);
        rec_view = findViewById(R.id.rec_view);
        tvHeading = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        rlProgress = findViewById(R.id.rlProgress);
        rlMain = findViewById(R.id.rlMain);
        etRent = findViewById(R.id.etRent);
        tvUpdate = findViewById(R.id.tvUpdate);
        etQuantity = findViewById(R.id.etQuantity);
        etMarko = findViewById(R.id.etMarko);
        etUnloadingCharge = findViewById(R.id.etUnloadingCharge);
        tvCancel = findViewById(R.id.tvCancel);
        ImageView ivBack = findViewById(R.id.ivBack);
    }


    @SuppressLint("StaticFieldLeak")
    private class ItemAsync extends AsyncTask<String, String, String> {
        boolean isItemFinished = true;
        boolean isUnitsFinished = true;
        boolean isRantFinished = true;
        boolean isRacksFinished = true;

        private void isAllFinish() {
            if (isItemFinished && isUnitsFinished && isRantFinished && isRacksFinished) {
                rlProgress.setVisibility(View.GONE);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rlProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            if (!sqLiteHelperFunctions.getTableRecordCount(DbConstants.TABLE_ITEMS_NAME)) {
                isItemFinished = false;


                Call<ItemResoponseModel> call = RetrofitInstance.getApiInterface().getAllItems();
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


            if (!sqLiteHelperFunctions.getTableRecordCount(DbConstants.TABLE_RACKS_NAME)) {
                isRacksFinished = false;
                Call<RackResponseModel> call = RetrofitInstance.getApiInterface().getAllRacks();
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
                Call<RentResponseModel> call = RetrofitInstance.getApiInterface().getAllItemRents();
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
    }
}