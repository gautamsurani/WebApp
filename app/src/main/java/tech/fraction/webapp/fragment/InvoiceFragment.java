package tech.fraction.webapp.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.activity.MainActivity;
import tech.fraction.webapp.activity.PartyWiseInvoiceActivity;
import tech.fraction.webapp.adapter.InvoiceAdapter;
import tech.fraction.webapp.base.BaseFragment;
import tech.fraction.webapp.base.NoNetworkActivity;
import tech.fraction.webapp.model.Invoices;
import tech.fraction.webapp.model.PartyWiseInvoicePaging;
import tech.fraction.webapp.model.PersonInformationInvoice;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiRequestModel.InvoiceRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.InvoiceResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;

import static tech.fraction.webapp.util.AppConstant.NO_NETWORK_REQUEST_CODE;


public class InvoiceFragment extends BaseFragment {

    RecyclerView rvInvoice;
    View view;
    TextView tvPartyInvoice, tvTitle, txtToastCountMsg;
    ProgressBar progress_circular;
    Activity context;
    RelativeLayout rlMain;
    Retrofit retrofit;
    ApiInterface apiInterface;
    InvoiceAdapter invoiceAdapter;
    ArrayList<PartyWiseInvoicePaging> lstpartyWiseInvoicePagings;
    ArrayList<Invoices> lstInvoice;
    PartyWiseInvoicePaging partyWiseInvoicePaging;
    InvoiceRequestModel invoiceRequestModel;
    LinearLayoutManager linearLayoutManager;
    InvoiceResponseModel invoiceResponseModel;
    LinearLayout linearShowToastMsg;
    int this_visible_item_count = 0;

    int visibleItemCount, totalItemCount, pastVisibleItems;

    boolean IsLAstLoading = true;


    public InvoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_invoice, container, false);
        context = getActivity();
        initComp();
        retrofit = RetrofitInstance.getClient();
        lstpartyWiseInvoicePagings = new ArrayList<>();
        lstInvoice = new ArrayList<>();
        apiInterface = retrofit.create(ApiInterface.class);
        tvPartyInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = tvPartyInvoice.getText().toString();
                if (a.equals("Invoices")) {
                    tvPartyInvoice.setText("Partywise Invoices");
                    lstInvoice.clear();
                    invoiceAdapter.notifyDataSetChanged();


                    partyWiseInvoicePaging = new PartyWiseInvoicePaging(0, 0, 0);
                    lstpartyWiseInvoicePagings.add(partyWiseInvoicePaging);


                    invoiceRequestModel = new InvoiceRequestModel(0, "0", lstpartyWiseInvoicePagings, 50, 0,
                            "GetPartyWiseInvoicesWithPaging(CurrentPage)", 0, "desc", "", "", "",
                            -1,
                            "", "A", false, "0", null, "", -1, 0, 0, "0",
                            1,
                            "I", Utils.getPersonalInfo(context).getPersonId());


                    CallInvoiceApi();


                } else {
                    tvPartyInvoice.setText("Invoices");
                    lstInvoice.clear();
                    invoiceAdapter.notifyDataSetChanged();

                    partyWiseInvoicePaging = new PartyWiseInvoicePaging(0, 0, 0);
                    lstpartyWiseInvoicePagings.add(partyWiseInvoicePaging);
                   /* {
                        "totalRecords": 0,
                            "sortBy": "", "sortDirection": "desc",
                            "jsFunction": "GetPartyWiseInvoicesWithPaging(CurrentPage)", "invoiceNo": "", "personId": 1,
                            "accountId": 0, "partyWisePagingAccountId": 0, "inwardNumber": "",
                            "outwardNumber": "", "personType": "A", "invoiceGeneratedPeriod": -1, "paidOn": -1, "isPartyWiseInvoice": true,
                            "reporType": "P", "broker": "",
                            "month": "0", "year": "0", "pageIndex": 0, "pageSize": 50, "receiptType": "0",
                            "paidStatus": "", "partywisePageIndex": 1, "partywisePageSize": 50, "partyWiseInvoicePaging": [
                        {
                            "accountId": 0,
                                "pageIndex": 0,
                                "pageSize": 0
                        }
                    ]
                    }*/

                    invoiceRequestModel = new InvoiceRequestModel(0, "0", lstpartyWiseInvoicePagings, 50, 0,
                            "GetPartyWiseInvoicesWithPaging(CurrentPage)", 1, "desc", "", "",
                            "",
                            -1,
                            "", "A", true, "0", null, "", -1, 0,
                            50, "0",
                            0,
                            "P", Utils.getPersonalInfo(context).getPersonId());
                    CallInvoiceApi();
                }
            }
        });
        linearLayoutManager = new LinearLayoutManager(context);
        partyWiseInvoicePaging = new PartyWiseInvoicePaging(0, 0, 0);
        lstpartyWiseInvoicePagings.add(partyWiseInvoicePaging);

        invoiceRequestModel = new InvoiceRequestModel(0, "0", lstpartyWiseInvoicePagings, 50, 0,
                "GetPartyWiseInvoicesWithPaging(CurrentPage)", 0, "desc", "", "", "",
                -1,
                "", "A", false, "0", null, "", -1, 0, 0, "0",
                1,
                "I", Utils.getPersonalInfo(context).getPersonId());

        if (!global.isNetworkAvailable()) {
            retryInternet("getInward");
        } else {
            CallInvoiceApi();
        }
        MainActivity.setOnFilterInvoiceApplyClickListener(new MainActivity.OnFilterInvoiceListener() {
            @Override
            public void onFilterInvoiceApplyClick(String broker, String invoiceNo, String inwardNo, String outwardNo, String month,
                                                  String year, String receiptType, int invoiceGeneratedPeriod, String paidStatus, int paidOn) {

                lstInvoice.clear();
                invoiceRequestModel.setBroker(broker);
                invoiceRequestModel.setInvoiceNo(invoiceNo);
                invoiceRequestModel.setInwardNumber(inwardNo);
                invoiceRequestModel.setOutwardNumber(outwardNo);
                invoiceRequestModel.setMonth(month);
                invoiceRequestModel.setYear(year);
                invoiceRequestModel.setReceiptType(receiptType);
                invoiceRequestModel.setInvoiceGeneratedPeriod(invoiceGeneratedPeriod);
                invoiceRequestModel.setPaidStatus(paidStatus);
                invoiceRequestModel.setPaidOn(paidOn);


                if (!global.isNetworkAvailable()) {
                    retryInternet("getInward");
                } else {
                    CallInvoiceApi();
                }


            }
        });
        invoiceAdapter = new InvoiceAdapter(context);
        rvInvoice.setLayoutManager(linearLayoutManager);
        rvInvoice.setHasFixedSize(true);

        invoiceAdapter.setList(lstInvoice);
        rvInvoice.setAdapter(invoiceAdapter);

        rvInvoice.addOnScrollListener(new RecyclerView.OnScrollListener() {

            CountDownTimer timer = null;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                linearShowToastMsg.setVisibility(View.VISIBLE);

                this_visible_item_count = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if (this_visible_item_count != -1) {
                    txtToastCountMsg.setText("Showing " + String.valueOf(this_visible_item_count + "/" + invoiceResponseModel.getData().getPaging().getPageSize() + " items"));
                }

                if (timer != null) {
                    timer.cancel();
                }

                timer = new CountDownTimer(3000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        linearShowToastMsg.setVisibility(View.GONE);
                    }
                }.start();

                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (IsLAstLoading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount &&
                                recyclerView.getChildAt(recyclerView.getChildCount() - 1).getBottom() <= recyclerView.getHeight()) {
                            IsLAstLoading = false;
                            int i = invoiceRequestModel.getPageIndex();
                            invoiceRequestModel.setPageIndex(i + 1);
                            CallInvoiceApi();
                        }
                    }
                }
            }
        });
        return view;
    }



    private void CallInvoiceApi() {
        progress_circular.setVisibility(View.VISIBLE);
        Call<InvoiceResponseModel> call = apiInterface.getAllInvoice(invoiceRequestModel);
        call.enqueue(new Callback<InvoiceResponseModel>() {
            @Override
            public void onResponse(Call<InvoiceResponseModel> call, Response<InvoiceResponseModel> response) {
                progress_circular.setVisibility(View.INVISIBLE);
                invoiceResponseModel = response.body();
                lstInvoice = invoiceResponseModel.getData().getPersonInformation().get(0).getInvoices();
                invoiceAdapter.setList(lstInvoice);
                invoiceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<InvoiceResponseModel> call, Throwable t) {
                progress_circular.setVisibility(View.INVISIBLE);
                Utils.ShowSnakBar("Failure", rlMain, context);
                Log.d("fsd", "Failure");

            }
        });
    }

    public void retryInternet(String extraValue) {
        Intent i = new Intent(context, NoNetworkActivity.class);
        i.putExtra("extraValue", extraValue);
        startActivityForResult(i, NO_NETWORK_REQUEST_CODE);
    }

    private void initComp() {
        rvInvoice = view.findViewById(R.id.rvInvoice);
        progress_circular = view.findViewById(R.id.progress_circular);
        tvPartyInvoice = view.findViewById(R.id.tvPartyInvoice);
        tvTitle = context.findViewById(R.id.tvTitle);
        linearShowToastMsg = view.findViewById(R.id.linearShowToastMsg);
        txtToastCountMsg = view.findViewById(R.id.txtToastCountMsg);
        rlMain = view.findViewById(R.id.rlMain);

    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(getResources().getString(R.string.invoice_list_title));
    }
}