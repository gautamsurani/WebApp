package tech.fraction.webapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import tech.fraction.webapp.activity.InwardDetailActivity;
import tech.fraction.webapp.activity.MainActivity;
import tech.fraction.webapp.adapter.InwordsAdapter;
import tech.fraction.webapp.base.BaseFragment;
import tech.fraction.webapp.base.NoNetworkActivity;
import tech.fraction.webapp.model.InventoryDetail;
import tech.fraction.webapp.model.PersonInformation;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiRequestModel.InwardRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.InwardResponseModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;

import static tech.fraction.webapp.util.AppConstant.NO_NETWORK_REQUEST_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class InwardsListFragment extends BaseFragment {

    RecyclerView rvInwords;
    Activity context;
    InwordsAdapter inwordsAdapter;
    TextView tvTitle;
    Retrofit retrofit;
    ApiInterface apiInterface;
    ProgressBar progress_circular;
    RelativeLayout rlMain;

    int this_visible_item_count = 0;

    int visibleItemCount, totalItemCount, pastVisibleItems;

    boolean IsLAstLoading = true;

    LinearLayoutManager linearLayoutManager;

    InwardRequestModel inwardRequestModel;

    LinearLayout linearShowToastMsg;

    TextView txtToastCountMsg, tvAddInward;

    public InwardsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inwards_list, container, false);

        context = getActivity();

        initComp(view);

        retrofit = RetrofitInstance.getClient();

        apiInterface = retrofit.create(ApiInterface.class);

        linearLayoutManager = new LinearLayoutManager(context);

        inwordsAdapter = new InwordsAdapter(context);
        rvInwords.setLayoutManager(linearLayoutManager);
        rvInwords.setAdapter(inwordsAdapter);

        inwordsAdapter.setOnItemClickListener(new InwordsAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {

                Intent intent = new Intent(context, AddEditInwardActivity.class);
                intent.putExtra("mode", "edit");
                intent.putExtra("inventoryDetails", inventoryDetails.get(position));
                startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                /*Intent intent = new Intent(context, InwardDetailActivity.class);
                intent.putExtra("inward", inventoryDetails.get(position));
                startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/
            }
        });

        rvInwords.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                            int i = inwardRequestModel.getPageIndex();
                            inwardRequestModel.setPageIndex(i + 1);
                            callGetInwardAPI();
                        }
                    }
                }
            }
        });

        tvAddInward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddEditInwardActivity.class);
                intent.putExtra("mode", "add");
                startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        PersonInformation personInformation = Utils.getPersonalInfo(context);

        inwardRequestModel = new InwardRequestModel(0, 25, "",
                "GetInwradsWithPaging(CurrentPage,'I')", "", "SrNumber", "", "", "",
                -1, personInformation.getPersonType(), "", "", "",
                -1, "", personInformation.getAccountId(), "", "", "DESC",
                -999999999, 1, personInformation.getAccountId(), "", "");

        if (!global.isNetworkAvailable()) {
            retryInternet("getInward");
        } else {
            callGetInwardAPI();
        }

        MainActivity.setOnFilterApplyClickListener(new MainActivity.OnFilterListener() {
            @Override
            public void onFilterApplyClick(String broker, String inwardNo, String item, String unit,
                                           String marko, String location, String inwardedOn, String sortBy,
                                           String sortByExpression, int invoiceGenerationDue, int invoiceGeneratedPeriod,
                                           String paidStatus, int paidOn) {
                inventoryDetails.clear();
                inwardRequestModel.setBroker(broker);
                inwardRequestModel.setPageIndex(1);
                inwardRequestModel.setInwardNo(inwardNo);
                inwardRequestModel.setItem(item);
                inwardRequestModel.setUnit(unit);
                inwardRequestModel.setMarko(marko);
                inwardRequestModel.setLocation(location);
                inwardRequestModel.setInwardedOnFilter(inwardedOn);
                inwardRequestModel.setSortByField(sortBy);
                inwardRequestModel.setSortByExpression(sortByExpression);
                inwardRequestModel.setInvoiceDue(invoiceGenerationDue);
                inwardRequestModel.setInvoiceGeneratedPeriod(invoiceGeneratedPeriod);
                inwardRequestModel.setPaidStatus(paidStatus);
                inwardRequestModel.setPaidOn(paidOn);
                if (!global.isNetworkAvailable()) {
                    retryInternet("getInward");
                } else {
                    callGetInwardAPI();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    List<InventoryDetail> inventoryDetails = new ArrayList<>();

    private void callGetInwardAPI() {

        progress_circular.setVisibility(View.VISIBLE);


        Call<InwardResponseModel> call = apiInterface.getInward(inwardRequestModel);

        call.enqueue(new Callback<InwardResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<InwardResponseModel> call, @NonNull Response<InwardResponseModel> response) {
                IsLAstLoading = true;
                progress_circular.setVisibility(View.GONE);
                InwardResponseModel inwardResponseModels = response.body();
                assert inwardResponseModels != null;
                inventoryDetails.addAll(inwardResponseModels.getData().getInventoryDetail());
                inwordsAdapter.setList(inventoryDetails);
                inwordsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<InwardResponseModel> call, @NonNull Throwable t) {
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
                if (extraValue.equalsIgnoreCase("getInward")) {
                    callGetInwardAPI();
                }
            }
        }
    }

    public void retryInternet(String extraValue) {
        Intent i = new Intent(context, NoNetworkActivity.class);
        i.putExtra("extraValue", extraValue);
        startActivityForResult(i, NO_NETWORK_REQUEST_CODE);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(getResources().getString(R.string.inword_list_title));
    }

    private void initComp(View view) {
        rvInwords = view.findViewById(R.id.rvInwords);
        progress_circular = view.findViewById(R.id.progress_circular);
        rlMain = view.findViewById(R.id.rlMain);
        linearShowToastMsg = view.findViewById(R.id.linearShowToastMsg);
        txtToastCountMsg = view.findViewById(R.id.txtToastCountMsg);
        tvAddInward = view.findViewById(R.id.tvAddInward);
        tvTitle = context.findViewById(R.id.tvTitle);
    }
}