package tech.fraction.webapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.SqliteDatabase.Sqlitehelper.SqLiteHelperFunctions;
import tech.fraction.webapp.SqliteDatabase.model.ItemRent;
import tech.fraction.webapp.SqliteDatabase.model.Items;
import tech.fraction.webapp.SqliteDatabase.model.Racks;
import tech.fraction.webapp.adapter.RacksAdapter;
import tech.fraction.webapp.adapter.RacksOutwardAdapter;
import tech.fraction.webapp.model.InwardItemLocationPoco;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.OutwardItemLocations;
import tech.fraction.webapp.model.OutwardItems;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.CommonApiCall.ItemAsyncTask;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.AppConstant;
import tech.fraction.webapp.util.Utils;

public class AddEditOutItemActivity extends AppCompatActivity {


    RecyclerView rec_view;
    TextView tvHeading, spnItem, spnUnit, spnLocation, tvUpdate;
    ImageView ivBack;
    ApiInterface apiInterface;
    Retrofit retrofit;
    RelativeLayout rlProgress, rlMain;
    RacksOutwardAdapter racksOutwardAdapter;
    Activity context;
    boolean isfinished;
    SqLiteHelperFunctions sqLiteHelperFunctions;
    EditText etRent, etQuantity, etMarko, etUnloadingCharge;
    Items selectedItems = new Items();
    List<Items> items;

    ItemRent selectedItemRent = new ItemRent();
    List<ItemRent> itemRents;

    OutwardItemLocations selectedRacks = new OutwardItemLocations();
    ArrayList<OutwardItemLocations> selectedRacksList = new ArrayList<>();
    List<OutwardItemLocations> itemRacks = new ArrayList<>();

    OutwardItems outwardItems;

    String quantity = "", marko = "", unloadingCharge = "";
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_out_item);
        context = this;

        sqLiteHelperFunctions = new SqLiteHelperFunctions(context);

        initComp();

        getData();

        retrofit = RetrofitInstance.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        initRecyclerView();
        spnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                items = sqLiteHelperFunctions.getAllItems();
                for (int i = 0; i < items.size(); i++) {
                    searchTextViewModels.add(new SearchTextViewModel(items.get(i).getId(), items.get(i).getName()));
                }
                Intent i = new Intent(AddEditOutItemActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", searchTextViewModels);
                i.putExtra("type", "item");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
        });

        spnUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItems == null) {
                    Utils.ShowSnakBar("Please Select Item", rlMain, context);
                } else {
                    ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                    itemRents = sqLiteHelperFunctions.getItemRentByItemId(selectedItems.getId());
                    for (int i = 0; i < itemRents.size(); i++) {
                        searchTextViewModels.add(new SearchTextViewModel(itemRents.get(i).getId(), itemRents.get(i).getUnit()));
                    }
                    Intent i = new Intent(AddEditOutItemActivity.this, SearchTextViewActivity.class);
                    i.putExtra("itemsList", searchTextViewModels);
                    i.putExtra("type", "unit");
                    startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
                }
            }
        });

        Utils.showToast(context,"dffdfd");

        spnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                List<Racks> racks = sqLiteHelperFunctions.getAllRacks();
                itemRacks.clear();
                for (int i = 0; i < racks.size(); i++) {
                    OutwardItemLocations outwardItemLocations = new OutwardItemLocations();
                    outwardItemLocations.setId(racks.get(i).getId());
                    outwardItemLocations.setRackName(racks.get(i).getName());
                    itemRacks.add(outwardItemLocations);
                }

                for (int i = 0; i < itemRacks.size(); i++) {
                    searchTextViewModels.add(new SearchTextViewModel(itemRacks.get(i).getId(), itemRacks.get(i).getRackName()));
                }

                Intent i = new Intent(AddEditOutItemActivity.this, SearchTextViewActivity.class);
                i.putExtra("itemsList", searchTextViewModels);
                i.putExtra("type", "location");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = etQuantity.getText().toString();
                marko = etMarko.getText().toString();
                unloadingCharge = etUnloadingCharge.getText().toString();
                outwardItems = new OutwardItems();
                outwardItems.setItemId(selectedItems.getId());
                if (AddEditOutwardActivity.outwardItemsList.size() == 0) {
                    outwardItems.setRawId(1);
                } else {
                    int rawId = AddEditOutwardActivity.outwardItemsList.get(AddEditOutwardActivity.outwardItemsList.size() - 1).getRawId();
                    rawId = rawId + 1;
                    outwardItems.setRawId(rawId);
                }
                outwardItems.setItemName(selectedItems.getName());
                outwardItems.setUnitId(selectedItemRent.getUnitId());
                outwardItems.setUnitName(selectedItemRent.getUnit());
                outwardItems.setRentPerUnit(selectedItemRent.getRent());
                outwardItems.setQuantity(Integer.parseInt(quantity));
                outwardItems.setMarkoName(marko);
                outwardItems.setLoadingCharges(Integer.parseInt(unloadingCharge));
                outwardItems.setOutwardItemLocations(selectedRacksList);

                if (selectedPosition != -1) {
                    AddEditOutwardActivity.outwardItemsList.remove(selectedPosition);
                    AddEditOutwardActivity.outwardItemsList.add(selectedPosition, outwardItems);
                } else {
                    AddEditOutwardActivity.outwardItemsList.add(outwardItems);
                }
                onBackPressed();
            }
      });

        rlProgress.setVisibility(View.VISIBLE);
        ItemAsyncTask itemAsyncTask = new ItemAsyncTask(context);
        itemAsyncTask.setOnDataListener(new ItemAsyncTask.DataInterface() {
            @Override
            public void finishAll() {
                rlProgress.setVisibility(View.INVISIBLE);
            }
        });
        itemAsyncTask.execute();

        String mode = getIntent().getStringExtra("mode");
        if (mode.equals("add")) {
            tvHeading.setText("Add Item");
        } else if (mode.equals("edit")) {

            tvHeading.setText("Edit Item");

            OutwardItems item = (OutwardItems) getIntent().getSerializableExtra("item");
            selectedPosition = Integer.parseInt(getIntent().getStringExtra("position"));

            etQuantity.setText(item.getQuantity() + "");
            etMarko.setText(item.getMarkoName());
            etUnloadingCharge.setText(item.getLoadingCharges() + "");
            selectedRacksList = item.getOutwardItemLocations();
            racksOutwardAdapter.setList(selectedRacksList);
            racksOutwardAdapter.notifyDataSetChanged();

            selectedItems.setId(item.getItemId());
            selectedItems.setName(item.getItemName());
            spnItem.setText(item.getItemName());

            spnUnit.setText(item.getUnitName());
            etRent.setText(item.getRentPerUnit() + "");
            selectedItemRent.setUnit(item.getUnitName());
            selectedItemRent.setUnitId(item.getUnitId());
            selectedItemRent.setRent(item.getRentPerUnit());
        }

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
                selectedItemRent = null;
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
            if (searchTextViewModel.getId() == itemRacks.get(i).getId()) {
                selectedRacks = itemRacks.get(i);
            }
        }

        boolean isIn = false;
        if (selectedRacksList.size() == 0) {
            selectedRacks.setRawId(1);
            selectedRacksList.add(selectedRacks);
            racksOutwardAdapter.notifyDataSetChanged();
        } else {
            for (int i = 0; i < selectedRacksList.size(); i++) {
                if (selectedRacksList.get(i).getRackName().equals(selectedRacks.getRackName())) {
                    isIn = true;
                }
            }
            if (!isIn) {
                int selectedRawId=selectedRacksList.get(selectedRacksList.size()-1).getRawId();
                selectedRawId=selectedRawId+1;
                selectedRacks.setRawId(selectedRawId);
                selectedRacksList.add(selectedRacks);
                racksOutwardAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initRecyclerView() {
        racksOutwardAdapter = new RacksOutwardAdapter(AddEditOutItemActivity.this);
        rec_view.setLayoutManager(new GridLayoutManager(this, 3));
        rec_view.setHasFixedSize(true);
        racksOutwardAdapter.setList(selectedRacksList);
        rec_view.setAdapter(racksOutwardAdapter);
        racksOutwardAdapter.setOnItemClickListener(new RacksOutwardAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                if (witch == 0) {
                    selectedRacksList.remove(position);
                    racksOutwardAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getData() {
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
    }
}
