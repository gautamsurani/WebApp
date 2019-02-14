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
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.SelectItemForOutwardAdapter;
import tech.fraction.webapp.adapter.SelectedOutwardAdapter;
import tech.fraction.webapp.model.OutwardDetails;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.util.AppConstant;

public class SelectedOutwardActivity extends AppCompatActivity {

    RecyclerView rvSelectedOutward;
    FloatingActionButton btnFloatAddItem;
    TextView tvSave, tvClose, tvTitle, tvLstEmpty;
    List<OutwardDetails> outwardDetails = new ArrayList<>();
    SelectedOutwardAdapter selectedOutwardAdapter;
    Context context;

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

        btnFloatAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SelectItemForOutwardActivity.class);
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
                ArrayList<OutwardDetails> outwardDetails = (ArrayList<OutwardDetails>) data.getSerializableExtra("outwardDetails");
                selectedOutwardAdapter.setList(outwardDetails);
                selectedOutwardAdapter.notifyDataSetChanged();
            }
    }

    private void initRecyclerView() {
        rvSelectedOutward.setLayoutManager(new LinearLayoutManager(this));
        rvSelectedOutward.setHasFixedSize(true);
        selectedOutwardAdapter.setList(outwardDetails);
        rvSelectedOutward.setAdapter(selectedOutwardAdapter);


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
}
