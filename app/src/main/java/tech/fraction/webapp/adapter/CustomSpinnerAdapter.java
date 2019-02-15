package tech.fraction.webapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.LocationModel;

public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    ArrayList<LocationModel> locationList;

    public CustomSpinnerAdapter(Context context, ArrayList<LocationModel> locationList) {
        this.context = context;
        this.locationList = locationList;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.custom_spinner_item, null);
        TextView names = view.findViewById(R.id.textView);
        names.setText(locationList.get(position).getName());
        return view;
    }
}
