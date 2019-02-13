package tech.fraction.webapp.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.activity.PartyWiseInvoiceActivity;
import tech.fraction.webapp.adapter.InvoiceAdapter;
import tech.fraction.webapp.model.Invoices;
import tech.fraction.webapp.model.PartyWiseInvoicePaging;
import tech.fraction.webapp.model.PersonInformationInvoice;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiRequestModel.InvoiceRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.InvoiceResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;


public class InvoiceFragment extends Fragment {

    RecyclerView rvInvoice;
    View view;
    TextView tvPartyInvoice,tvTitle;
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
        lstpartyWiseInvoicePagings=new ArrayList<>();
        lstInvoice=new ArrayList<>();
        apiInterface = retrofit.create(ApiInterface.class);
        tvPartyInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PartyWiseInvoiceActivity.class);
                startActivity(intent);
            }
        });

        partyWiseInvoicePaging=new PartyWiseInvoicePaging(0,0,0);
        lstpartyWiseInvoicePagings.add(partyWiseInvoicePaging);

        invoiceRequestModel=new InvoiceRequestModel(0,"0",lstpartyWiseInvoicePagings,50,0,
                "GetPartyWiseInvoicesWithPaging(CurrentPage)",0,"desc","","","",-1,
                "","A",false,"0",null,"",-1,0,0,"0",1,
                "I", Utils.getPersonalInfo(context).getPersonId());
        CallInvoiceApi();
        invoiceAdapter = new InvoiceAdapter(context);
        rvInvoice.setLayoutManager(new LinearLayoutManager(context));
        rvInvoice.setHasFixedSize(true);

        invoiceAdapter.setList(lstInvoice);
        rvInvoice.setAdapter(invoiceAdapter);

        return view;
    }

    private void CallInvoiceApi() {
        Call<InvoiceResponseModel> call=apiInterface.getAllInvoice(invoiceRequestModel);
        call.enqueue(new Callback<InvoiceResponseModel>() {
            @Override
            public void onResponse(Call<InvoiceResponseModel> call, Response<InvoiceResponseModel> response) {
                InvoiceResponseModel invoiceResponseModel=response.body();
                lstInvoice=invoiceResponseModel.getData().getPersonInformation().get(0).getInvoices();
                invoiceAdapter.setList(lstInvoice);
                invoiceAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<InvoiceResponseModel> call, Throwable t) {
                Log.d("fsd","Failure");

            }
        });
    }

    private void initComp() {
        rvInvoice = view.findViewById(R.id.rvInvoice);
        progress_circular = view.findViewById(R.id.progress_circular);
        tvPartyInvoice = view.findViewById(R.id.tvPartyInvoice);
        tvTitle = context.findViewById(R.id.tvTitle);

    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(getResources().getString(R.string.invoice_list_title));
    }
}
