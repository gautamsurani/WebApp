package tech.fraction.webapp.activity;

import android.content.Context;
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

public class SelectedOutwardActivity extends AppCompatActivity {

    RecyclerView rvSelectedOutward;
    FloatingActionButton btnFloatAddItem;
    TextView tvSave, tvClose, tvTitle,tvLstEmpty;
    ArrayList<String> lstSelectedItem = new ArrayList<>();
    SelectedOutwardAdapter selectedOutwardAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_outward);
        context = this;
        lstSelectedItem.add("One");
        lstSelectedItem.add("two");
        lstSelectedItem.add("three");
        lstSelectedItem.add("One");
        lstSelectedItem.add("two");
        lstSelectedItem.add("three");
        selectedOutwardAdapter = new SelectedOutwardAdapter(context);
        initComp();
        if (lstSelectedItem.size() == 0) {
            tvLstEmpty.setVisibility(View.VISIBLE);
        } else {
            initRecyclerView();
        }

        btnFloatAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

    private void initRecyclerView() {
        rvSelectedOutward.setLayoutManager(new LinearLayoutManager(this));
        rvSelectedOutward.setHasFixedSize(true);
        selectedOutwardAdapter.setList(lstSelectedItem);
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
