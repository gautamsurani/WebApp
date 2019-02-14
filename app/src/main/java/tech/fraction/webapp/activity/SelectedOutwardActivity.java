package tech.fraction.webapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.SelectedOutwardAdapter;
import tech.fraction.webapp.model.OutwardDetails;
import tech.fraction.webapp.util.AppConstant;

public class SelectedOutwardActivity extends AppCompatActivity {

    RecyclerView rvSelectedOutward;
    FloatingActionButton btnFloatAddItem;
    TextView tvSave, tvClose, tvTitle, tvLstEmpty;
    ArrayList<OutwardDetails> outwardDetails = new ArrayList<>();
    SelectedOutwardAdapter selectedOutwardAdapter;
    Context context;
    int accountId = -1;

    @Override
    protected void onResume() {
        super.onResume();
        if (outwardDetails.size() == 0) {
            tvLstEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_outward);
        context = this;
        selectedOutwardAdapter = new SelectedOutwardAdapter(context);
        initComp();

        initRecyclerView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            accountId = bundle.getInt("accountId", -1);
        }

        btnFloatAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SelectItemForOutwardActivity.class);
                i.putExtra("outwardDetails", outwardDetails);
                i.putExtra("accountId", accountId);
                startActivityForResult(i, AppConstant.SEARCH_ACTIVITY_REQUEST_CODE);
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConstant.SEARCH_ACTIVITY_REQUEST_CODE)
            if (resultCode == Activity.RESULT_OK) {
                outwardDetails = (ArrayList<OutwardDetails>) data.getSerializableExtra("outwardDetails");
                selectedOutwardAdapter.setList(outwardDetails);
                selectedOutwardAdapter.notifyDataSetChanged();
            }
    }

    private void initRecyclerView() {
        rvSelectedOutward.setLayoutManager(new LinearLayoutManager(this));
        rvSelectedOutward.setHasFixedSize(true);
        selectedOutwardAdapter.setList(outwardDetails);
        rvSelectedOutward.setAdapter(selectedOutwardAdapter);
        selectedOutwardAdapter.setOnItemClickListener(new SelectedOutwardAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                if (witch == 2) {
                    outwardDetails.remove(position);
                    selectedOutwardAdapter.setList(outwardDetails);
                    selectedOutwardAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initComp() {
        rvSelectedOutward = findViewById(R.id.rvSelectedOutward);
        btnFloatAddItem = findViewById(R.id.btnFloatAddItem);
        tvSave = findViewById(R.id.tvSave);
        tvClose = findViewById(R.id.tvClose);
        tvTitle = findViewById(R.id.tvTitle);
        tvLstEmpty = findViewById(R.id.tvLstEmpty);
        tvTitle.setText("Selected Item");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}