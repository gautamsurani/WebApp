package tech.fraction.webapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import tech.fraction.webapp.R;
import tech.fraction.webapp.util.Global;

public class BaseFragment extends Fragment {

    public Global global;
    SimpleArcDialog mDialog;

    @Override
    public void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        global = new Global(getContext());
    }

    public void dismissProgressDialog() {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
    }

    public void startProgressDialog() {
        if (mDialog == null) {
            try {
                int[] color = new int[2];
                color[0] = getContext().getResources().getColor(R.color.black);
                color[1] = getContext().getResources().getColor(R.color.black);

                mDialog = new SimpleArcDialog(getContext());
                ArcConfiguration arcConfiguration = new ArcConfiguration(getContext());
                arcConfiguration.setColors(color);
                mDialog.setConfiguration(arcConfiguration);
                mDialog.setCancelable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mDialog.show();
    }
}