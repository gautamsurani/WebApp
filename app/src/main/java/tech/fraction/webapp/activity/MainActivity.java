package tech.fraction.webapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.SqliteDatabase.DbConstants;
import tech.fraction.webapp.SqliteDatabase.Sqlitehelper.SqLiteHelperFunctions;
import tech.fraction.webapp.SqliteDatabase.model.Items;
import tech.fraction.webapp.adapter.MenuAdapter;
import tech.fraction.webapp.fragment.CustomerListFragment;
import tech.fraction.webapp.fragment.InvoiceFragment;
import tech.fraction.webapp.fragment.InwardsListFragment;
import tech.fraction.webapp.R;
import tech.fraction.webapp.fragment.OutwardListFragment;
import tech.fraction.webapp.model.Account;
import tech.fraction.webapp.model.Codebeautify;
import tech.fraction.webapp.model.Menu;
import tech.fraction.webapp.model.Paging;
import tech.fraction.webapp.model.SearchField;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiResponseModel.AccountResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.ItemResoponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.AppConstant;
import tech.fraction.webapp.util.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    private DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mDrawerToggle;

    RelativeLayout mainView;

    Activity context;

    RelativeLayout layoutBottomSheet;

    LinearLayout layoutBottomSheetCustomer;

    RelativeLayout layoutBottomSheetOutward, layoutBottomSheetInvoice;

    BottomSheetBehavior sheetBehavior;

    BottomSheetBehavior sheetBehaviorCustomer;

    BottomSheetBehavior sheetBehaviorOutward;

    BottomSheetBehavior sheetBehaviorInvoice;

    ImageView ivFilter, ivClose, ivCloseCustomer, ivCloseOutward, ivCloseInvoice;

    Spinner spInwardOn, spInvoiceGenerationDue, spInvoiceGeneratedPeriod, spPaidStatus, spPaidOn;

    Spinner spnOutwardedOn, spInvoiceStatus, spPaidStatusOut, spPaidOnOut;

    TextView tvInwardFilterReset, tvResetCustomer, tvTitle, tvFromDate, tvToDate, tvParty, tvItem, tvInwardFilterApply;

    TextView tvBroker, tvItemOut, tvOutwardFilterReset, tvOutwardFilterApply;

    EditText etOutwardNo, etInwardNoOut, etUnitOut, etLocationOut;

    MenuAdapter menuAdapter;

    ArrayList<Account> accounts = new ArrayList<>();

    ProgressBar pbParty, pbBroker, pbItemOut;

    private static Account selectedAccount;

    SqLiteHelperFunctions sqLiteHelperFunctions;

    Items selectedItems = new Items();
    List<Items> items;

    EditText etInwardNo, etUnit, etMarko, etLocation;

    RadioGroup rgShortBy, rgSortByExpression;

    RadioButton rbDefaultShortBy, rbDefaultSortByExpression;

    TextView tvBrokerInvoice, tvInvoiceFilterReset, tvInvoiceFilterApply;

    Spinner spnMon, spYear, spInvReceiptType, spInvoiceGeneratedPeriodInv, spPaidStatusInv, spPaidOnInv;

    ProgressBar pbBrokerInvoice;

    EditText etInvoiceNo, etInwardNoInv, etOutwardNoInv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        AppConstant.canResume = true;

        sqLiteHelperFunctions = new SqLiteHelperFunctions(context);

        initComp();

        setNavBar();

        ivFilter.setOnClickListener(this);

        ivClose.setOnClickListener(this);

        ivCloseCustomer.setOnClickListener(this);

        ivCloseOutward.setOnClickListener(this);

        ivCloseInvoice.setOnClickListener(this);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Utils.hideKeyboard(context);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:

                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        sheetBehaviorOutward.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Utils.hideKeyboard(context);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:

                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        sheetBehaviorInvoice.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Utils.hideKeyboard(context);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:

                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        setInvoiceGenerationDue();
        setPaidStatusCommon(spPaidStatus);
        setPaidStatusCommon(spPaidStatusOut);
        setPaidStatusCommon(spPaidStatusInv);
        setPaidOnCommonn(spPaidOn);
        setPaidOnCommonn(spPaidOnOut);
        setPaidOnCommonn(spPaidOnInv);
        setPaidOnCommonn(spInvoiceGeneratedPeriodInv);
        setPaidOnCommonn(spInvoiceGeneratedPeriod);
        setInwardedOutwardedOn(spInwardOn);
        setInwardedOutwardedOn(spnOutwardedOn);

        setSpMonth();
        setSpYear();
        setSpReceiptType();


        setspInvoiceStatus();


        tvFromDate.setOnClickListener(this);

        tvToDate.setOnClickListener(this);

        openHomeFragment(new InwardsListFragment());

        //callTestAPI();

        if (accounts.size() == 0) {
            new AccountAsync().execute();
        }

        tvParty.setOnClickListener(this);
        tvBroker.setOnClickListener(this);
        tvBrokerInvoice.setOnClickListener(this);

        tvItem.setOnClickListener(this);
        tvItemOut.setOnClickListener(this);

        tvInwardFilterReset.setOnClickListener(this);

        tvInwardFilterApply.setOnClickListener(this);

        tvOutwardFilterReset.setOnClickListener(this);

        tvOutwardFilterApply.setOnClickListener(this);

        tvInvoiceFilterReset.setOnClickListener(this);

        tvInvoiceFilterApply.setOnClickListener(this);

        new ItemAsync().execute();
    }


    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivFilter:
                if (tvTitle.getText().equals(getResources().getString(R.string.inword_list_title))) {
                    if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                } else if (tvTitle.getText().equals(getResources().getString(R.string.outword_list_title))) {
                    if (sheetBehaviorOutward.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        sheetBehaviorOutward.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        sheetBehaviorOutward.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                } else if (tvTitle.getText().equals(getResources().getString(R.string.customer_list_title))) {
                    if (sheetBehaviorCustomer.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        sheetBehaviorCustomer.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        sheetBehaviorCustomer.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                } else if (tvTitle.getText().equals(getResources().getString(R.string.invoice_list_title))) {
                    if (sheetBehaviorInvoice.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        sheetBehaviorInvoice.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        sheetBehaviorInvoice.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
                break;
            case R.id.ivClose:
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.ivCloseCustomer:
                sheetBehaviorCustomer.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.ivCloseOutward:
                sheetBehaviorOutward.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.ivCloseInvoice:
                sheetBehaviorInvoice.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.tvToDate:
                Utils.setDate(tvToDate, context);
                break;
            case R.id.tvParty: {
                ArrayList<SearchTextViewModel> itemsList = new ArrayList<>();
                for (int i = 0; i < accounts.size(); i++) {
                    itemsList.add(new SearchTextViewModel(accounts.get(i).getId(), accounts.get(i).getName()));
                }
                Intent i = new Intent(context, SearchTextViewActivity.class);
                i.putExtra("itemsList", itemsList);
                i.putExtra("type", "party");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
            break;
            case R.id.tvBroker: {
                ArrayList<SearchTextViewModel> itemsList = new ArrayList<>();
                for (int i = 0; i < accounts.size(); i++) {
                    itemsList.add(new SearchTextViewModel(accounts.get(i).getId(), accounts.get(i).getName()));
                }
                Intent i = new Intent(context, SearchTextViewActivity.class);
                i.putExtra("itemsList", itemsList);
                i.putExtra("type", "party");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
            break;

            case R.id.tvBrokerInvoice: {
                ArrayList<SearchTextViewModel> itemsList = new ArrayList<>();
                for (int i = 0; i < accounts.size(); i++) {
                    itemsList.add(new SearchTextViewModel(accounts.get(i).getId(), accounts.get(i).getName()));
                }
                Intent i = new Intent(context, SearchTextViewActivity.class);
                i.putExtra("itemsList", itemsList);
                i.putExtra("type", "party");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
            break;

            case R.id.tvItem: {
                ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                items = sqLiteHelperFunctions.getAllItems();
                for (int i = 0; i < items.size(); i++) {
                    searchTextViewModels.add(new SearchTextViewModel(items.get(i).getId(), items.get(i).getName()));
                }
                Intent i = new Intent(context, SearchTextViewActivity.class);
                i.putExtra("itemsList", searchTextViewModels);
                i.putExtra("type", "item");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
            break;
            case R.id.tvItemOut: {
                ArrayList<SearchTextViewModel> searchTextViewModels = new ArrayList<>();
                items = sqLiteHelperFunctions.getAllItems();
                for (int i = 0; i < items.size(); i++) {
                    searchTextViewModels.add(new SearchTextViewModel(items.get(i).getId(), items.get(i).getName()));
                }
                Intent i = new Intent(context, SearchTextViewActivity.class);
                i.putExtra("itemsList", searchTextViewModels);
                i.putExtra("type", "item");
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
            break;
            case R.id.tvInwardFilterApply:
                setInwardFilter();
                break;
            case R.id.tvOutwardFilterApply:
                setOutwardFilter();
                break;
            case R.id.tvInvoiceFilterApply:
                setInvoiceFilter();
                break;
            case R.id.tvInwardFilterReset:
                resetInwardFilter();
                break;
            case R.id.tvOutwardFilterReset:
                resetOutwardFilter();
                break;
            case R.id.tvInvoiceFilterReset:
                resetInvoiceFilter();
                break;
            default:
                //do ur code;
        }
    }

    private void setSpReceiptType() {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("Inward");
        categories.add("Outward");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spInvReceiptType.setAdapter(dataAdapter);

    }

    private void setSpMonth() {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("January");
        categories.add("February");
        categories.add("March");
        categories.add("April");
        categories.add("May");
        categories.add("June");
        categories.add("July");
        categories.add("August");
        categories.add("September");
        categories.add("October");
        categories.add("November");
        categories.add("December");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnMon.setAdapter(dataAdapter);

    }

    private void setSpYear() {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("2016");
        categories.add("2017");
        categories.add("2018");
        categories.add("2019");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spYear.setAdapter(dataAdapter);

    }

    private void setInvoiceFilter() {
        if (onClickListenerInvoice != null) {
            String broker = "", invoiceNo, inwardNo, outwardNo,
                    receiptType = "", paidStatus = "", month = "", year = "";
            int paidOn = -1, invoiceGeneratedPeriod = -1;
            if (selectedAccount != null) {
                if (selectedAccount.getName() != null) {
                    broker = selectedAccount.getName();
                }
            }
            invoiceNo = etInvoiceNo.getText().toString();
            inwardNo = etInwardNoInv.getText().toString();
            outwardNo = etOutwardNoInv.getText().toString();
            switch (spnMon.getSelectedItem().toString()) {
                case "All":
                    month = "0";
                    break;
                case "January":
                    month = "1";
                    break;
                case "Fabruary":
                    month = "2";
                    break;
                case "March":
                    month = "3";
                    break;
                case "April":
                    month = "4";
                    break;
                case "May":
                    month = "5";
                    break;
                case "June":
                    month = "6";
                    break;
                case "July":
                    month = "7";
                    break;
                case "August":
                    month = "8";
                    break;
                case "September":
                    month = "9";
                    break;
                case "October":
                    month = "10";
                    break;
                case "November":
                    month = "11";
                    break;
                case "December":
                    month = "12";
                    break;
            }
            switch (spYear.getSelectedItem().toString()) {
                case "All":
                    year = "0";
                    break;
                case "2016":
                    year = "2016";
                    break;
                case "2017":
                    year = "2017";
                    break;
                case "2018":
                    year = "2018";
                    break;
                case "2019":
                    year = "2019";
                    break;
            }
            switch (spInvReceiptType.getSelectedItem().toString()) {
                case "All":
                    receiptType = "0";
                    break;
                case "Inward":
                    receiptType = "I";
                    break;
                case "Outward":
                    receiptType = "O";
                    break;
            }
            switch (spInvoiceGeneratedPeriodInv.getSelectedItem().toString()) {
                case "All":
                    invoiceGeneratedPeriod = -1;
                    break;
                case "Within 2 Days":
                    invoiceGeneratedPeriod = 2;
                    break;
                case "Today":
                    invoiceGeneratedPeriod = 0;
                    break;
                case "Within 1 Week":
                    invoiceGeneratedPeriod = 8;
                    break;
                case "Within 15 Days":
                    invoiceGeneratedPeriod = 15;
                    break;
                case "Within 30 Days":
                    invoiceGeneratedPeriod = 30;
                    break;
            }
            switch (spPaidStatusInv.getSelectedItem().toString()) {
                case "All":
                    paidStatus = "";
                    break;
                case "Fully Paid":
                    paidStatus = "F";
                    break;
                case "Partially Paid":
                    paidStatus = "P";
                    break;
                case "Not Paid":
                    paidStatus = "N";
                    break;
                case "Zero Amount":
                    paidStatus = "Z";
                    break;
            }
            switch (spPaidOnInv.getSelectedItem().toString()) {
                case "All":
                    paidOn = -1;
                    break;
                case "Within 2 Days":
                    paidOn = 2;
                    break;
                case "Today":
                    paidOn = 0;
                    break;
                case "Within 1 Week":
                    paidOn = 8;
                    break;
                case "Within 15 Days":
                    paidOn = 15;
                    break;
                case "Within 30 Days":
                    paidOn = 30;
                    break;
            }
            onClickListenerInvoice.onFilterInvoiceApplyClick(broker, invoiceNo, inwardNo, outwardNo, month, year, receiptType, invoiceGeneratedPeriod, paidStatus, paidOn);
            sheetBehaviorInvoice.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    private void resetInvoiceFilter() {
        selectedAccount = null;
        tvBrokerInvoice.setText("");
        etInvoiceNo.setText("");
        etInwardNoInv.setText("");
        etOutwardNoInv.setText("");
        spnMon.setSelection(0);
        spYear.setSelection(0);
        spInvReceiptType.setSelection(0);
        spInvoiceGeneratedPeriodInv.setSelection(0);
        spPaidStatusInv.setSelection(0);
        spPaidOnInv.setSelection(0);
        sheetBehaviorInvoice.setState(BottomSheetBehavior.STATE_COLLAPSED);
        setInvoiceFilter();
    }

    private void setOutwardFilter() {
        if (onClickListenerOutward != null) {
            sheetBehaviorOutward.setState(BottomSheetBehavior.STATE_COLLAPSED);
            String broker = "", outwardNo, inwardNo, item = "", unit, location, outwardedOn = "", invoiceStatus = "", paidStatus = "";
            int paidOnOut = -1;
            if (selectedAccount != null) {
                if (selectedAccount.getName() != null) {
                    broker = selectedAccount.getName();
                }
            }
            if (selectedItems != null) {
                if (selectedItems.getName() != null) {
                    item = selectedItems.getName();
                }
            }
            outwardNo = etOutwardNo.getText().toString();
            inwardNo = etInwardNoOut.getText().toString();
            unit = etUnitOut.getText().toString();
            location = etLocationOut.getText().toString();
            switch (spnOutwardedOn.getSelectedItem().toString()) {
                case "All":
                    outwardedOn = "";
                    break;
                case "Yesterday":
                    outwardedOn = "Y";
                    break;
                case "Today":
                    outwardedOn = "T";
                    break;
                case "This Week":
                    outwardedOn = "TW";
                    break;
                case "Last Week":
                    outwardedOn = "LW";
                    break;
                case "This Month":
                    outwardedOn = "TM";
                    break;
                case "Last Month":
                    outwardedOn = "LM";
                    break;
            }
            switch (spInvoiceStatus.getSelectedItem().toString()) {
                case "All":
                    invoiceStatus = "";
                    break;
                case "Invoice Generated":
                    invoiceStatus = "Y";
                    break;
                case "Invoice Not Generated":
                    invoiceStatus = "N";
                    break;

            }
            switch (spPaidStatusOut.getSelectedItem().toString()) {
                case "All":
                    paidStatus = "";
                    break;
                case "Fully Paid":
                    paidStatus = "F";
                    break;
                case "Partially Paid":
                    paidStatus = "P";
                    break;
                case "Not Paid":
                    paidStatus = "N";
                    break;
                case "Zero Amount":
                    paidStatus = "Z";
                    break;
            }
            switch (spPaidOnOut.getSelectedItem().toString()) {
                case "All":
                    paidOnOut = -1;
                    break;
                case "Within 2 Days":
                    paidOnOut = 2;
                    break;
                case "Today":
                    paidOnOut = 0;
                    break;
                case "Within 1 Week":
                    paidOnOut = 8;
                    break;
                case "Within 15 Days":
                    paidOnOut = 15;
                    break;
                case "Within 30 Days":
                    paidOnOut = 30;
                    break;
            }

            onClickListenerOutward.onFilterApplyClickOutward(broker, outwardNo, inwardNo, item, unit, location, outwardedOn
                    , invoiceStatus, paidStatus, paidOnOut);
        }
    }

    private void resetOutwardFilter() {
        selectedAccount = null;
        selectedItems = null;
        tvBroker.setText("");
        tvItemOut.setText("");
        etInwardNoOut.setText("");
        etOutwardNo.setText("");
        etUnitOut.setText("");
        etLocationOut.setText("");
        spnOutwardedOn.setSelection(0);
        spInvoiceStatus.setSelection(0);
        spPaidStatusOut.setSelection(0);
        spPaidOnOut.setSelection(0);
        sheetBehaviorOutward.setState(BottomSheetBehavior.STATE_COLLAPSED);
        setOutwardFilter();
    }

    private void setInwardFilter() {

        if (onClickListener != null) {
            String broker = "", item = "", inwardedOn = "", sortBy = "", sortByExpression = "", paidStatus = "";
            int invoiceGenerationDue = -1, invoiceGeneratedPeriod = -1, paidOn = -1;
            if (selectedAccount != null) {
                if (selectedAccount.getName() != null) {
                    broker = selectedAccount.getName();
                }
            }
            if (selectedItems != null) {
                if (selectedItems.getName() != null) {
                    item = selectedItems.getName();
                }
            }
            String inwardNo = etInwardNo.getText().toString();
            String unit = etUnit.getText().toString();
            String marko = etMarko.getText().toString();
            String location = etLocation.getText().toString();
            switch (spInwardOn.getSelectedItem().toString()) {
                case "All":
                    inwardedOn = "";
                    break;
                case "Yesterday":
                    inwardedOn = "Y";
                    break;
                case "Today":
                    inwardedOn = "T";
                    break;
                case "This Week":
                    inwardedOn = "TW";
                    break;
                case "Last Week":
                    inwardedOn = "LW";
                    break;
                case "This Month":
                    inwardedOn = "TM";
                    break;
                case "Last Month":
                    inwardedOn = "LM";
                    break;
            }
            int selectedId = rgShortBy.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            switch (radioButton.getText().toString()) {
                case "  Sr Number":
                    sortBy = "SrNumber";
                    break;
                case "  Account Name":
                    sortBy = "AccountName";
                    break;
                case "  In. Date":
                    sortBy = "InwardDate";
                    break;
            }
            int selectedSortByExpression = rgSortByExpression.getCheckedRadioButtonId();
            RadioButton radioButtonSortByExpression = findViewById(selectedSortByExpression);
            switch (radioButtonSortByExpression.getText().toString()) {
                case "  Descending":
                    sortByExpression = "DESC";
                    break;
                case "  Ascending":
                    sortByExpression = "ASC";
                    break;
            }
            switch (spInvoiceGenerationDue.getSelectedItem().toString()) {
                case "All":
                    invoiceGenerationDue = -999999999;
                    break;
                case "Within 2 Days":
                    invoiceGenerationDue = 2;
                    break;
                case "Today":
                    invoiceGenerationDue = 0;
                    break;
                case "This Week":
                    invoiceGenerationDue = 8;
                    break;
                case "Since Last 1 Week":
                    invoiceGenerationDue = -7;
                    break;
                case "Since Last 15 Days":
                    invoiceGenerationDue = -15;
                    break;
                case "All Due Invoices":
                    invoiceGenerationDue = -100000;
                    break;
            }
            switch (spInvoiceGeneratedPeriod.getSelectedItem().toString()) {
                case "All":
                    invoiceGeneratedPeriod = -1;
                    break;
                case "Within 2 Days":
                    invoiceGeneratedPeriod = 2;
                    break;
                case "Today":
                    invoiceGeneratedPeriod = 0;
                    break;
                case "Within 1 Week":
                    invoiceGeneratedPeriod = 8;
                    break;
                case "Within 15 Days":
                    invoiceGeneratedPeriod = 15;
                    break;
                case "Within 30 Days":
                    invoiceGeneratedPeriod = 30;
                    break;
            }
            switch (spPaidStatus.getSelectedItem().toString()) {
                case "All":
                    paidStatus = "";
                    break;
                case "Fully Paid":
                    paidStatus = "F";
                    break;
                case "Partially Paid":
                    paidStatus = "P";
                    break;
                case "Not Paid":
                    paidStatus = "N";
                    break;
                case "Zero Amount":
                    paidStatus = "Z";
                    break;
            }
            switch (spPaidOn.getSelectedItem().toString()) {
                case "All":
                    paidOn = -1;
                    break;
                case "Within 2 Days":
                    paidOn = 2;
                    break;
                case "Today":
                    paidOn = 0;
                    break;
                case "Within 1 Week":
                    paidOn = 8;
                    break;
                case "Within 15 Days":
                    paidOn = 15;
                    break;
                case "Within 30 Days":
                    paidOn = 30;
                    break;
            }
            onClickListener.onFilterApplyClick(broker, inwardNo, item, unit, marko, location, inwardedOn
                    , sortBy, sortByExpression, invoiceGenerationDue, invoiceGeneratedPeriod, paidStatus, paidOn);
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    private void resetInwardFilter() {
        selectedAccount = null;
        selectedItems = null;
        tvParty.setText("");
        tvItem.setText("");
        etInwardNo.setText("");
        etUnit.setText("");
        etMarko.setText("");
        etLocation.setText("");
        spInwardOn.setSelection(0);
        rbDefaultShortBy.setChecked(true);
        rbDefaultSortByExpression.setChecked(true);
        spInvoiceGenerationDue.setSelection(0);
        spInvoiceGeneratedPeriod.setSelection(0);
        spPaidStatus.setSelection(0);
        spPaidOn.setSelection(0);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        setInwardFilter();
    }

    public static OnFilterListener onClickListener;

    public static void setOnFilterApplyClickListener(OnFilterListener onClick) {
        onClickListener = onClick;
    }


    public interface OnFilterListener {
        void onFilterApplyClick(String broker, String inwardNo, String item, String unit, String marko
                , String location, String inwardedOn, String sortBy, String sortByExpression, int invoiceGenerationDue
                , int invoiceGeneratedPeriod, String paidStatus, int paidOn);
    }

    public static OnFilterInvoiceListener onClickListenerInvoice;

    public static void setOnFilterInvoiceApplyClickListener(OnFilterInvoiceListener onClick) {
        onClickListenerInvoice = onClick;
    }

    public interface OnFilterInvoiceListener {
        void onFilterInvoiceApplyClick(String broker, String invoiceNo, String inwardNo, String outwardNo, String month
                , String year, String receiptType, int invoiceGeneratedPeriod
                , String paidStatus, int paidOn);
    }

    public static OnFilterOutwardListener onClickListenerOutward;

    public static void setOnFilterOutwardApplyClickListener(OnFilterOutwardListener onClick) {
        onClickListenerOutward = onClick;
    }

    public interface OnFilterOutwardListener {
        void onFilterApplyClickOutward(String broker, String outwardNo, String inwardNo, String item, String unit
                , String location, String outwardedOn, String invoiceStatus, String paidStatus,
                                       int paidOn);
    }

    @SuppressLint("StaticFieldLeak")
    private class ItemAsync extends AsyncTask<String, String, String> {
        boolean isItemFinished = true;

        private void isAllFinish() {
            if (isItemFinished) {
                //rlProgress.setVisibility(View.GONE);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //rlProgress.setVisibility(View.VISIBLE);
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
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            isAllFinish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstant.SEARCH_ACTIVITY_REQUEST_CODE)
            if (resultCode == Activity.RESULT_OK) {
                String type = data.getStringExtra("type");
                if (type.equals("party")) {
                    SearchTextViewModel searchTextViewModel = (SearchTextViewModel) data.getSerializableExtra("search");
                    setParty(searchTextViewModel);
                } else if (type.equals("item")) {
                    SearchTextViewModel searchTextViewModel = (SearchTextViewModel) data.getSerializableExtra("search");
                    setItem(searchTextViewModel);
                }
            }
    }

    private void setItem(SearchTextViewModel searchTextViewModel) {
        for (int i = 0; i < items.size(); i++) {
            if (searchTextViewModel.getId() == items.get(i).getId()) {
                selectedItems = items.get(i);
                if ((tvTitle.getText().equals(getResources().getString(R.string.outword_list_title)))) {
                    tvItemOut.setText(selectedItems.getName());
                } else {
                    tvItem.setText(selectedItems.getName());
                }
                return;
            }
        }
    }

    private void setParty(SearchTextViewModel searchTextViewModel) {
        for (int i = 0; i < accounts.size(); i++) {
            if (searchTextViewModel.getId() == accounts.get(i).getId()) {
                selectedAccount = accounts.get(i);
                if ((tvTitle.getText().equals(getResources().getString(R.string.outword_list_title)))) {
                    tvBroker.setText(selectedAccount.getName());
                } else if (tvTitle.getText().equals(getResources().getString(R.string.inword_list_title))) {
                    tvParty.setText(selectedAccount.getName());
                } else if (tvTitle.getText().equals(getResources().getString(R.string.invoice_list_title))) {
                    tvBrokerInvoice.setText(selectedAccount.getName());
                }
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
            pbBroker.setVisibility(View.VISIBLE);
            tvBroker.setEnabled(false);
            pbBrokerInvoice.setVisibility(View.VISIBLE);
            tvBrokerInvoice.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {

            Call<AccountResponseModel> call = RetrofitInstance.getApiInterface().getAllAccount();
            call.enqueue(new Callback<AccountResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<AccountResponseModel> call, @NonNull Response<AccountResponseModel> response) {
                    AccountResponseModel accountResponseModel = response.body();
                    assert accountResponseModel != null;
                    accounts = accountResponseModel.getAccount();
                    pbParty.setVisibility(View.INVISIBLE);
                    tvParty.setEnabled(true);
                    pbBroker.setVisibility(View.INVISIBLE);
                    tvBroker.setEnabled(true);
                    pbBrokerInvoice.setVisibility(View.INVISIBLE);
                    tvBrokerInvoice.setEnabled(true);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFailure(@NonNull Call<AccountResponseModel> call, @NonNull Throwable t) {
                    pbParty.setVisibility(View.INVISIBLE);
                    tvParty.setText("Fail to load party name");
                    pbBroker.setVisibility(View.INVISIBLE);
                    tvBroker.setText("Fail to load party name");
                    pbBrokerInvoice.setVisibility(View.INVISIBLE);
                    tvBrokerInvoice.setText("Fail to load party name");
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

    private void callTestAPI() {

        String s = "{\"paging\":{\"PageIndex\":1,\"PageSize\":50,\"JsFunction\":\"GetInwradsWithPaging(CurrentPage,'I')\"}," +
                "\"searchField\":{\"InwardNo\":\"\",\"Item\":\"\",\"Unit\":\"\",\"Marko\":\"\",\"Broker\":\"\",\"AccountId\":\"0\",\"Location\":\"\",\"SortByField\":\"SrNumber\",\"SortByExpression\":\"DESC\",\"InwardedOnFilter\":\"\",\"PaidOn\":\"-1\",\"InvoiceDue\":\"-999999999\",\"PaidStatus\":\" \",\"InvoiceGeneratedPeriod\":\"-1\",\"IsInvoiceGenerated\":\"\",\"InwardedOnFrom\":\"\",\"InwardedOnTo\":\"\",\"PageIndex\":1,\"PageSize\":50,\"PageType\":\"I\"}}";
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(s).getAsJsonObject();

        Paging paging = new Paging();
        paging.setPageIndex(1);
        paging.setPageSize(50);
        paging.setJsFunction("GetInwradsWithPaging(CurrentPage,'I')");

        SearchField searchField = new SearchField();
        searchField.setAccountId(0);
        searchField.setSortByField("SrNumber");
        searchField.setPaidOn(-1);
        searchField.setInvoiceDue(-999999999);
        searchField.setInvoiceGeneratedPeriod(-1);
        searchField.setPageIndex(1);
        searchField.setPageSize(50);
        searchField.setPageType("PageType");

        Codebeautify codebeautify = new Codebeautify();
        codebeautify.setPaging(paging);
        codebeautify.setSearchField(searchField);

        Call<String> call = RetrofitInstance.getApiInterface().getTestApi(codebeautify);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                assert response.body() != null;
                Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getMenuName(int menuPosition, int subMenuPosition, int subSubMenuPosition) {
        String menuName = "";
        if (subSubMenuPosition != -1) {
            menuName = menus.get(menuPosition).getChildrens().get(subMenuPosition).getChildrens().get(subSubMenuPosition).getDisplayName();
        } else if (subMenuPosition != -1) {
            menuName = menus.get(menuPosition).getChildrens().get(subMenuPosition).getDisplayName();
        } else if (menuPosition != -1) {
            menuName = menus.get(menuPosition).getDisplayName();
        }
        return menuName;
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.add(R.id.frame_container, fragment);
        //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openHomeFragment(Fragment fragment) {
        AppConstant.canResume = true;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    private void setInwardedOutwardedOn(Spinner spnInOut) {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("Yesterday");
        categories.add("Today");
        categories.add("This Week");
        categories.add("Last Week");
        categories.add("This Month");
        categories.add("Last Month");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnInOut.setAdapter(dataAdapter);
    }

    private void setspInvoiceStatus() {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("Invoice Generated");
        categories.add("Invoice Not Generated");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spInvoiceStatus.setAdapter(dataAdapter);
    }

    private void setInvoiceGenerationDue() {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("This Week");
        categories.add("Within 2 Days");
        categories.add("Today");
        categories.add("Since Last 1 Week");
        categories.add("Since Last 15 Days");
        categories.add("All Due Invoices");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spInvoiceGenerationDue.setAdapter(dataAdapter);
    }

    private void setPaidStatusCommon(Spinner spnPaidStatus) {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("Fully Paid");
        categories.add("Partially Paid");
        categories.add("Not Paid");
        categories.add("Zero Amount");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnPaidStatus.setAdapter(dataAdapter);
    }


    private void setPaidOnCommonn(Spinner spPaid) {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("Today");
        categories.add("Within 2 Days");
        categories.add("Within 1 Week");
        categories.add("Within 15 Days");
        categories.add("Within 30 Days");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spPaid.setAdapter(dataAdapter);
    }


    private void initComp() {
        mainView = findViewById(R.id.mainView);
        ivFilter = findViewById(R.id.ivFilter);
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        layoutBottomSheetCustomer = findViewById(R.id.bottom_sheet_customer);
        layoutBottomSheetOutward = findViewById(R.id.layoutBottomSheetOutward);
        layoutBottomSheetInvoice = findViewById(R.id.layoutBottomSheetInvoice);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehaviorCustomer = BottomSheetBehavior.from(layoutBottomSheetCustomer);
        sheetBehaviorOutward = BottomSheetBehavior.from(layoutBottomSheetOutward);
        sheetBehaviorInvoice = BottomSheetBehavior.from(layoutBottomSheetInvoice);
        ivClose = findViewById(R.id.ivClose);
        ivCloseCustomer = findViewById(R.id.ivCloseCustomer);
        ivCloseOutward = findViewById(R.id.ivCloseOutward);
        ivCloseInvoice = findViewById(R.id.ivCloseInvoice);
        spInwardOn = findViewById(R.id.spInwardOn);
        spInvoiceGenerationDue = findViewById(R.id.spInvoiceGenerationDue);
        spInvoiceGeneratedPeriod = findViewById(R.id.spInvoiceGeneratedPeriod);
        spPaidStatus = findViewById(R.id.spPaidStatus);
        spPaidOn = findViewById(R.id.spPaidOn);
        tvInwardFilterReset = findViewById(R.id.tvInwardFilterReset);
        tvResetCustomer = findViewById(R.id.tvResetCustomer);
        tvTitle = findViewById(R.id.tvTitle);
        tvFromDate = findViewById(R.id.tvFromDate);
        tvToDate = findViewById(R.id.tvToDate);
        etLocationOut = findViewById(R.id.etLocationOut);
        pbItemOut = findViewById(R.id.pbItemOut);
        tvParty = findViewById(R.id.tvParty);
        tvItemOut = findViewById(R.id.tvItemOut);
        tvItem = findViewById(R.id.tvItem);
        etInwardNoOut = findViewById(R.id.etInwardNoOut);
        etUnitOut = findViewById(R.id.etUnitOut);
        pbParty = findViewById(R.id.pbParty);
        etOutwardNo = findViewById(R.id.etOutwardNo);
        pbBroker = findViewById(R.id.pbBroker);
        tvInwardFilterApply = findViewById(R.id.tvInwardFilterApply);
        etInwardNo = findViewById(R.id.etInwardNo);
        etUnit = findViewById(R.id.etUnit);
        etMarko = findViewById(R.id.etMarko);
        etLocation = findViewById(R.id.etLocation);
        rgShortBy = findViewById(R.id.rgShortBy);
        rgSortByExpression = findViewById(R.id.rgSortByExpression);
        rbDefaultShortBy = findViewById(R.id.rbDefaultShortBy);
        rbDefaultSortByExpression = findViewById(R.id.rbDefaultSortByExpression);
        tvBroker = findViewById(R.id.tvBroker);
        spnOutwardedOn = findViewById(R.id.spnOutwardedOn);
        spInvoiceStatus = findViewById(R.id.spInvoiceStatus);
        spPaidStatusOut = findViewById(R.id.spPaidStatusOut);
        spPaidOnOut = findViewById(R.id.spPaidOnOut);
        tvOutwardFilterReset = findViewById(R.id.tvOutwardFilterReset);
        tvOutwardFilterApply = findViewById(R.id.tvOutwardFilterApply);
        tvBrokerInvoice = findViewById(R.id.tvBrokerInvoice);
        spnMon = findViewById(R.id.spnMon);
        spYear = findViewById(R.id.spYear);
        spInvReceiptType = findViewById(R.id.spInvReceiptType);
        spInvoiceGeneratedPeriodInv = findViewById(R.id.spInvoiceGeneratedPeriodInv);
        etInvoiceNo = findViewById(R.id.etInvoiceNo);
        etInwardNoInv = findViewById(R.id.etInwardNoInv);
        etOutwardNoInv = findViewById(R.id.etOutwardNoInv);
        spPaidStatusInv = findViewById(R.id.spPaidStatusInv);
        spPaidOnInv = findViewById(R.id.spPaidOnInv);
        pbBrokerInvoice = findViewById(R.id.pbBrokerInvoice);
        tvInvoiceFilterReset = findViewById(R.id.tvInvoiceFilterReset);
        tvInvoiceFilterApply = findViewById(R.id.tvInvoiceFilterApply);
    }

    List<Menu> menus;


    @SuppressLint("SetTextI18n")
    private void setNavBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setContentInsetStartWithNavigation(0);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                drawerView.animate().scaleX(1).scaleY(1);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
                mainView.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        RecyclerView rvMenu = mDrawerLayout.findViewById(R.id.rvMenu);
        TextView tvLogout = mDrawerLayout.findViewById(R.id.tvLogout);
        TextView tvName = mDrawerLayout.findViewById(R.id.tvName);

        menus = Utils.getMenus(context);

        menuAdapter = new MenuAdapter(context);
        menuAdapter.setList(menus);
        rvMenu.setLayoutManager(new LinearLayoutManager(context));
        rvMenu.setAdapter(menuAdapter);

        tvName.setText(Utils.getPersonalInfo(context).getFirstName() + " " + Utils.getPersonalInfo(context).getLastName());

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                openLogoutDialog();
            }
        });

        menuAdapter.setOnItemClickListener(new MenuAdapter.OnClickListener() {
            @Override
            public void onClick(int menuPosition, int subMenuPosition, int subSubMenuPosition) {

                String menuName = getMenuName(menuPosition, subMenuPosition, subSubMenuPosition);

                if (menuName.endsWith("Inwards")) {
                    mDrawerLayout.closeDrawers();
                    if (!tvTitle.getText().equals(getResources().getString(R.string.inword_list_title))) {
                        openHomeFragment(new InwardsListFragment());
                    }
                } else if (menuName.equals("Outwards")) {
                    mDrawerLayout.closeDrawers();
                    if (!tvTitle.getText().equals(getResources().getString(R.string.outword_list_title))) {
                        openFragment(new OutwardListFragment());
                    }
                } else if (menuName.equals("Customers")) {
                    mDrawerLayout.closeDrawers();
                    if (!tvTitle.getText().equals(getResources().getString(R.string.customer_list_title))) {
                        openFragment(new CustomerListFragment());
                    }
                } else if (menuName.equals("Invoices")) {
                    mDrawerLayout.closeDrawers();
                    if (!tvTitle.getText().equals(getResources().getString(R.string.invoice_list_title))) {
                        openFragment(new InvoiceFragment());
                    }
                } else {
                    Toast.makeText(context, getMenuName(menuPosition, subMenuPosition, subSubMenuPosition) + " - Under Construction...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openLogoutDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.create();
        AlertDialog alertDialog;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams") final View alertLayout = inflater.inflate(R.layout.popup_logout, null);

        TextView tvOk, tvCancel;

        tvOk = alertLayout.findViewById(R.id.tvOk);
        tvCancel = alertLayout.findViewById(R.id.tvCancel);

        alertDialogBuilder.setView(alertLayout);

        alertDialog = alertDialogBuilder.create();

        final AlertDialog finalAlertDialog = alertDialog;
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalAlertDialog.dismiss();
                Utils.Logout(context);
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalAlertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Utils.showToast(context, getResources().getString(R.string.press_again_exit_app));
            exitTime = System.currentTimeMillis();
        } else {
            finishAffinity();
        }
    }

    @Override
    public void onBackPressed() {
        if (tvTitle.getText().equals(getResources().getString(R.string.inword_list_title))) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawers();
            } else {
                doExitApp();
            }
        } else {
            super.onBackPressed();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            if (currentFragment instanceof InwardsListFragment) {
                tvTitle.setText(getResources().getString(R.string.inword_list_title));
            } else if (currentFragment instanceof CustomerListFragment) {
                tvTitle.getResources().getString(R.string.customer_list_title);
            } else if (currentFragment instanceof OutwardListFragment) {
                tvTitle.getResources().getString(R.string.outword_list_title);
            }
        }
    }
}