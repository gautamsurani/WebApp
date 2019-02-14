package tech.fraction.webapp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fraction.webapp.R;
import tech.fraction.webapp.activity.AddEditInwardActivity;
import tech.fraction.webapp.activity.AddEditOutwardActivity;
import tech.fraction.webapp.activity.MainActivity;
import tech.fraction.webapp.activity.OutwardDetailActivity;
import tech.fraction.webapp.adapter.InwordsAdapter;
import tech.fraction.webapp.adapter.OutwardListAdapter;
import tech.fraction.webapp.base.BaseFragment;
import tech.fraction.webapp.base.NoNetworkActivity;
import tech.fraction.webapp.model.InventoryDetailOutward;
import tech.fraction.webapp.model.OutWardListModel;
import tech.fraction.webapp.model.PersonInformation;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiRequestModel.InwardRequestModel;
import tech.fraction.webapp.rest.ApiRequestModel.OutwardRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.OutwardResoinseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;

import static tech.fraction.webapp.util.AppConstant.NO_NETWORK_REQUEST_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutwordListFragment extends BaseFragment {

    RecyclerView rvOutwords;
    Activity context;
    InwordsAdapter inwordsAdapter;
    TextView tvTitle,tvAddOutward;
    Retrofit retrofit;
    ApiInterface apiInterface;
    ProgressBar progress_circular;
    RelativeLayout rlMain;
    OutwardListAdapter outwardListAdapter;

    int this_visible_item_count = 0;

    int visibleItemCount, totalItemCount, pastVisibleItems;

    boolean IsLAstLoading = true;

    LinearLayoutManager linearLayoutManager;

    OutwardRequestModel outwardRequestModel;

    LinearLayout linearShowToastMsg;

    TextView txtToastCountMsg, tvAddInward;
    ArrayList<InventoryDetailOutward> outWardList;

    public OutwordListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_outword_list, container, false);

        retrofit = RetrofitInstance.getClient();

        context = getActivity();

        initComp(view);

        apiInterface = retrofit.create(ApiInterface.class);
        outWardList = new ArrayList<InventoryDetailOutward>();

        linearLayoutManager = new LinearLayoutManager(context);


        outwardListAdapter = new OutwardListAdapter(context);
        rvOutwords.setLayoutManager(linearLayoutManager);
        rvOutwords.setHasFixedSize(true);
        outwardListAdapter.setOnItemClickListener(new OutwardListAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                Intent i = new Intent(context, OutwardDetailActivity.class);
                i.putExtra("outWardList", outWardList.get(position));
                startActivity(i);
                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        rvOutwords.setAdapter(outwardListAdapter);
        rvOutwords.addOnScrollListener(new RecyclerView.OnScrollListener() {

            CountDownTimer timer = null;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                linearShowToastMsg.setVisibility(View.VISIBLE);

                this_visible_item_count = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if (this_visible_item_count != -1) {
                    txtToastCountMsg.setText("Showing " + String.valueOf(this_visible_item_count + "/" + 5000 + " items"));
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
                            int i = outwardRequestModel.getPageIndex();
                            outwardRequestModel.setPageIndex(i + 1);
                            callGetOutwardAPI();
                        }
                    }
                }
            }


        });

        tvAddOutward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddEditOutwardActivity.class);
                intent.putExtra("mode", "add");
                startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        PersonInformation personInformation = Utils.getPersonalInfo(context);
        outwardRequestModel = new OutwardRequestModel(0, "", "", 50, "", "", -1, "",
                "", "", "GetOutwradsWithPaging(CurrentPage)", personInformation.getAccountId(), "desc", "", "", 1,
                "", personInformation.getPersonId(), "",
                "", personInformation.getPersonType(), "");

        if (!global.isNetworkAvailable()) {
            retryInternet("getOutward");
        } else {
            callGetOutwardAPI();
        }


        MainActivity.setOnFilterOutwardApplyClickListener(new MainActivity.OnFilterOutwardListener() {
            @Override
            public void onFilterApplyClickOutward(String broker, String outwardNo, String inwardNo, String item, String unit, String location,
                                                  String outwardedOn, String invoiceStatus, String paidStatus, int paidOn) {
                outWardList.clear();
                outwardRequestModel.setBroker(broker);
                outwardRequestModel.setOutwardNo(outwardNo);
                outwardRequestModel.setInwardNo(inwardNo);
                outwardRequestModel.setItem(item);
                outwardRequestModel.setUnit(unit);
                outwardRequestModel.setLocation(location);
                outwardRequestModel.setOutwardedOnFilter(outwardedOn);
                outwardRequestModel.setIsInvoiceGenerated(invoiceStatus);
                outwardRequestModel.setPaidStatus(paidStatus);
                outwardRequestModel.setPaidOn(paidOn);
                if (!global.isNetworkAvailable()) {
                    retryInternet("getOutward");
                } else {
                    callGetOutwardAPI();
                }

            }
        });
        return view;
    }

    private void callGetOutwardAPI() {
        Call<OutwardResoinseModel> call = apiInterface.getAllOurward(outwardRequestModel);
        call.enqueue(new Callback<OutwardResoinseModel>() {
            @Override
            public void onResponse(Call<OutwardResoinseModel> call, Response<OutwardResoinseModel> response) {
                IsLAstLoading = true;
                progress_circular.setVisibility(View.GONE);
                OutwardResoinseModel outwardResoinseModel = new OutwardResoinseModel();
                assert outwardResoinseModel != null;
                outwardResoinseModel = response.body();
                outWardList.addAll(outwardResoinseModel.getData().getResponse());

                outwardListAdapter.setList(outWardList);
                outwardListAdapter.notifyDataSetChanged();


            }




            @Override
            public void onFailure(Call<OutwardResoinseModel> call, Throwable t) {

                progress_circular.setVisibility(View.GONE);
                Utils.ShowSnakBar("Failure", rlMain, context);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NO_NETWORK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String extraValue = data.getStringExtra("extraValue");
                if (extraValue.equalsIgnoreCase("getOutward")) {
                    callGetOutwardAPI();
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(getResources().getString(R.string.outword_list_title));
    }
    public void retryInternet(String extraValue) {
        Intent i = new Intent(context, NoNetworkActivity.class);
        i.putExtra("extraValue", extraValue);
        startActivityForResult(i, NO_NETWORK_REQUEST_CODE);
    }

    private void initComp(View view) {
        rvOutwords = view.findViewById(R.id.rvOutwords);
        tvTitle = context.findViewById(R.id.tvTitle);
        tvAddOutward = view.findViewById(R.id.tvAddOutward);
        progress_circular = view.findViewById(R.id.progress_circular);
        linearShowToastMsg = view.findViewById(R.id.linearShowToastMsg);
        txtToastCountMsg = view.findViewById(R.id.txtToastCountMsg);
    }
}
