package tech.fraction.webapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.InwardItemAdapter;
import tech.fraction.webapp.model.InventoryDetail;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.Transporter;

public class InwardDetailActivity extends AppCompatActivity {

    RecyclerView rec_view;

    EditText etVehicleNo, etTransporter, etDriverName, etDriverNo, etRemark;

    TextView txtSave, txtPrint, txtClose, tvTitle;

    InwardItemAdapter inwardItemAdapter;

    List<InwardItems> inwardItems = new ArrayList<>();

    ImageView ivBack;

    Activity context;

    InventoryDetail inventoryDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inward_detail);

        context = this;

        initComp();

        inventoryDetails = (InventoryDetail) getIntent().getSerializableExtra("inward");

        setInwardDetail();

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        inwardItemAdapter.setOnItemClickListener(new InwardItemAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setInwardDetail() {
        tvTitle.setText(inventoryDetails.getInwardNo());

        inwardItemAdapter = new InwardItemAdapter(InwardDetailActivity.this, false);
        rec_view.setLayoutManager(new LinearLayoutManager(this));
        rec_view.setHasFixedSize(true);
        inwardItems.addAll(inventoryDetails.getInwardItems());
        inwardItemAdapter.setList(inwardItems);
        rec_view.setAdapter(inwardItemAdapter);

        Transporter transporter = inventoryDetails.getTransporter();

        if(transporter==null)
        {
            etVehicleNo.setText("");
            etTransporter.setText("");
            etDriverName.setText("");
            etDriverNo.setText("");
            etRemark.setText("");
        }
        else
        {
            etVehicleNo.setText(transporter.getVehicleNo());
            etTransporter.setText(transporter.getTransporterDetail());
            etDriverName.setText(transporter.getDriverName());
            etDriverNo.setText(transporter.getDriverContactNumber());
            etRemark.setText(transporter.getRemarks());

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(context, AddEditInwardActivity.class);
                intent.putExtra("mode", "edit");
                intent.putExtra("inventoryDetails",inventoryDetails);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            case R.id.detail:

                return true;
            case R.id.print:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void initComp() {
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        rec_view = findViewById(R.id.rec_view);
        etVehicleNo = findViewById(R.id.etVehicleNo);
        etTransporter = findViewById(R.id.etTransporter);
        etDriverName = findViewById(R.id.etDriverName);
        etDriverNo = findViewById(R.id.etDriverNo);
        etRemark = findViewById(R.id.etRemark);
        txtSave = findViewById(R.id.txt_save);
        txtPrint = findViewById(R.id.txt_print);
        txtClose = findViewById(R.id.txt_close);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
    }
}