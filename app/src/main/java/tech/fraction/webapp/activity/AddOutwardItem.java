package tech.fraction.webapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tech.fraction.webapp.R;

public class AddOutwardItem extends AppCompatActivity implements View.OnClickListener {

    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outward_item);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                onBackPressed();
            default:

        }
    }


}
