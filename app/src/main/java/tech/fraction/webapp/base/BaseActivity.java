package tech.fraction.webapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import tech.fraction.webapp.R;
import tech.fraction.webapp.util.Global;

public class BaseActivity extends AppCompatActivity {

    public Global global;
    SimpleArcDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        global = new Global(this);

        int[] color = new int[2];
        color[0] = getResources().getColor(R.color.black);
        color[1] = getResources().getColor(R.color.black);

        mDialog = new SimpleArcDialog(this);
        ArcConfiguration arcConfiguration = new ArcConfiguration(this);
        arcConfiguration.setColors(color);
        mDialog.setConfiguration(arcConfiguration);
        mDialog.setCancelable(false);
    }

    @Override
    public void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    public void dismissProgressDialog() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void startProgressDialog() {
        mDialog.show();
    }
}