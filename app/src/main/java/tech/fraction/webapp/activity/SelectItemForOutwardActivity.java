package tech.fraction.webapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import tech.fraction.webapp.adapter.InvoiceAdapter;
import tech.fraction.webapp.adapter.SelectItemForOutwardAdapter;
import tech.fraction.webapp.base.BaseActivity;
import tech.fraction.webapp.base.NoNetworkActivity;
import tech.fraction.webapp.model.OutwardDetails;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.rest.ApiInterface.ApiInterface;
import tech.fraction.webapp.rest.ApiRequestModel.InwardRequestModel;
import tech.fraction.webapp.rest.ApiResponseModel.InwardResponseModel;
import tech.fraction.webapp.rest.ApiResponseModel.OutwardItemRespondModel;
import tech.fraction.webapp.rest.RetrofitInstance;
import tech.fraction.webapp.util.Utils;

import static tech.fraction.webapp.util.AppConstant.NO_NETWORK_REQUEST_CODE;

public class SelectItemForOutwardActivity extends BaseActivity {

    RecyclerView rvItems;
    Activity context;
    boolean IsLAstLoading = true;

    Retrofit retrofit;
    ApiInterface apiInterface;
    ProgressBar progress_circular;
    RelativeLayout rlMain;

    ImageView ivBack;
    TextView tvTitle, tvDone;

    LinearLayoutManager linearLayoutManager;

    InwardRequestModel inwardRequestModel;

    LinearLayout linearShowToastMsg;

    TextView txtToastCountMsg;

    List<OutwardDetails> outwardDetails = new ArrayList<>();
    List<OutwardDetails> selectedOutwardDetails = new ArrayList<>();

    SelectItemForOutwardAdapter selectItemForOutwardAdapter;

    int accountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item_for_outward);

        context = this;

        initComp();

        selectedOutwardDetails = (ArrayList<OutwardDetails>) getIntent().getSerializableExtra("outwardDetails");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            accountId = bundle.getInt("accountId", -1);
        }

        retrofit = RetrofitInstance.getClient();

        apiInterface = retrofit.create(ApiInterface.class);

        selectItemForOutwardAdapter = new SelectItemForOutwardAdapter(context);
        rvItems.setLayoutManager(new LinearLayoutManager(context));
        rvItems.setHasFixedSize(true);
        rvItems.setAdapter(selectItemForOutwardAdapter);

        if (!global.isNetworkAvailable()) {
            retryInternet("getOutwardItem");
        } else {
            callGetOutwardItemAPI();
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<OutwardDetails> selectedItem = new ArrayList<>();
                for (int i = 0; i < outwardDetails.size(); i++) {
                    if (outwardDetails.get(i).isSelected()) {
                        selectedItem.add(outwardDetails.get(i));
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("outwardDetails", selectedItem);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NO_NETWORK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String extraValue = data.getStringExtra("extraValue");
                if (extraValue.equalsIgnoreCase("getOutwardItem")) {
                    callGetOutwardItemAPI();
                }
            }
        }
    }

    private void callGetOutwardItemAPI() {

        progress_circular.setVisibility(View.VISIBLE);


        Call<OutwardItemRespondModel> call = apiInterface.getOutwardItem(accountId);

        call.enqueue(new Callback<OutwardItemRespondModel>() {
            @Override
            public void onResponse(@NonNull Call<OutwardItemRespondModel> call, @NonNull Response<OutwardItemRespondModel> response) {
                IsLAstLoading = true;
                progress_circular.setVisibility(View.GONE);
                OutwardItemRespondModel outwardItemRespondModel = response.body();
                assert outwardItemRespondModel != null;
                outwardDetails.addAll(outwardItemRespondModel.getData().getOutwardDetails());

                for (int i = 0; i < outwardDetails.size(); i++) {
                    for (int j = 0; j < selectedOutwardDetails.size(); j++) {
                        if (outwardDetails.get(i).getInwardDetailId() == selectedOutwardDetails.get(j).getInwardDetailId()) {
                            outwardDetails.get(i).setSelected(true);
                        }
                    }
                }

                selectItemForOutwardAdapter.setList(outwardDetails);
                selectItemForOutwardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OutwardItemRespondModel> call, Throwable t) {
                progress_circular.setVisibility(View.GONE);
                Utils.ShowSnakBar("Failure", rlMain, context);
            }
        });
    }

    public void retryInternet(String extraValue) {
        Intent i = new Intent(context, NoNetworkActivity.class);
        i.putExtra("extraValue", extraValue);
        startActivityForResult(i, NO_NETWORK_REQUEST_CODE);
    }

    private void initComp() {
        rvItems = findViewById(R.id.rvItems);
        progress_circular = findViewById(R.id.progress_circular);
        rlMain = findViewById(R.id.rlMain);
        tvDone = findViewById(R.id.tvDone);
        linearShowToastMsg = findViewById(R.id.linearShowToastMsg);
        txtToastCountMsg = findViewById(R.id.txtToastCountMsg);
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        tvTitle.setText("Select Items");
    }
}
