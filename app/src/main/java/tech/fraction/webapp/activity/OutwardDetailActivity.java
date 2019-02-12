package tech.fraction.webapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.OutwardDetailListAdapter;
import tech.fraction.webapp.model.InventoryDetailOutward;
import tech.fraction.webapp.model.OutwardItems;
import tech.fraction.webapp.model.Transporter;
import tech.fraction.webapp.util.Utils;
import tech.fraction.webapp.util.ValidationUtil;

public class OutwardDetailActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rec_view;
    EditText edt_vehicleNo, edt_transporter, edt_driverName, edt_driverNo, edt_remark;

    TextView txtSave, txtPrint, txtClose,tvTitle;

    OutwardDetailListAdapter outwardDetailListAdapter;

    ArrayList<OutwardItems> lstItems;

    InventoryDetailOutward outWardList;

    ImageView ivBack;

    String vehicleNo, transporterName, driverName, driverNo, remark;
    LinearLayout nestedScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outward_detail);
        initComp();
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        outWardList=(InventoryDetailOutward) getIntent().getSerializableExtra("outWardList");
        lstItems=outWardList.getOutwardItems();
        setOutwardDetail();





        outwardDetailListAdapter.setOnItemClickListener(new OutwardDetailListAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {

            }
        });
        txtSave.setOnClickListener(this);
        txtPrint.setOnClickListener(this);
        txtClose.setOnClickListener(this);



        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void setOutwardDetail() {
        tvTitle.setText(outWardList.getOutwardNo());
        outwardDetailListAdapter = new OutwardDetailListAdapter(OutwardDetailActivity.this, false);
        rec_view.setLayoutManager(new LinearLayoutManager(this));
        rec_view.setHasFixedSize(true);
        outwardDetailListAdapter.setList(lstItems);
        rec_view.setAdapter(outwardDetailListAdapter);
        Transporter transporter=outWardList.getTransporter();
        if(transporter==null)
        {
            edt_vehicleNo.setText("");
            edt_transporter.setText("");
            edt_driverName.setText("");
            edt_driverNo.setText("");
            edt_remark.setText("");
        }
        else
        {
        edt_vehicleNo.setText(transporter.getVehicleNo());
        edt_transporter.setText(transporter.getTransporterDetail());
        edt_driverName.setText(transporter.getDriverName());
        edt_driverNo.setText(transporter.getDriverContactNumber());
        edt_remark.setText(transporter.getRemarks());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_save:

                break;
            case R.id.txt_print:

                break;
            case R.id.txt_close:

                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean result = super.onPrepareOptionsMenu(menu);
        styleMenuButton();
        return result;
    }

    private void styleMenuButton() {
        // Find the menu item you want to style
        View view = findViewById(R.id.detail);

        // Cast to a TextView instance if the menu item was found
        if (view != null && view instanceof TextView) {
            ((TextView) view).setTextColor(Color.BLUE); // Make text colour blue
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit:

                Intent intent = new Intent(OutwardDetailActivity.this, AddEditOutwardActivity.class);
                intent.putExtra("mode", "edit");
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

    private void initComp() {
        rec_view = findViewById(R.id.rec_view);
        edt_vehicleNo = findViewById(R.id.edt_vehicleNo);
        edt_transporter = findViewById(R.id.edt_transporter);
        edt_driverName = findViewById(R.id.edt_driverName);
        edt_driverNo = findViewById(R.id.edt_driverNo);
        edt_remark = findViewById(R.id.edt_remark);
        txtSave = findViewById(R.id.txt_save);
        txtPrint = findViewById(R.id.txt_print);
        txtClose = findViewById(R.id.txt_close);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);

        nestedScrollView = findViewById(R.id.nes_out_de_act);


    }

    private boolean validateField(String vehicleNo, String transporterName, String driverName, String driverNo, String remark) {

        if (!ValidationUtil.isValidVehicleNumber(vehicleNo)) {

            Utils.ShowSnakBar("Please Enter Valid Vehicle No", nestedScrollView, OutwardDetailActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidName(transporterName)) {

            Utils.ShowSnakBar("Please Enter Valid Name", nestedScrollView, OutwardDetailActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidName(driverName)) {
            Utils.ShowSnakBar("Please Enter Valid Name", nestedScrollView, OutwardDetailActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidPhoneNumber(driverNo)) {
            Utils.ShowSnakBar("Please Enter Valid Mobile No", nestedScrollView, OutwardDetailActivity.this);
            return false;
        }
        if (!ValidationUtil.isValidRemark(remark)) {
            Utils.ShowSnakBar("Lenghth must be more then 10 ", nestedScrollView, OutwardDetailActivity.this);
            return false;
        }


        return true;
    }


    public void getData() {
        vehicleNo = edt_vehicleNo.getText().toString();
        transporterName = edt_transporter.getText().toString();
        driverName = edt_driverName.getText().toString();
        driverNo = edt_driverNo.getText().toString();
        remark = edt_remark.getText().toString();


    }


}
