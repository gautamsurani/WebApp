package tech.fraction.webapp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.activity.AddCustomerActivity;
import tech.fraction.webapp.adapter.CustomerListAdapter;
import tech.fraction.webapp.model.CustomerListModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends Fragment {

    RecyclerView rec_view;
    CustomerListAdapter customerListAdapter;
    List<CustomerListModel> customerList;
    TextView txtAddCustomer, tvTitle;
    Activity context;

    public CustomerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();

        initComp(view);
        customerList = new ArrayList<CustomerListModel>();
        customerList.add(new CustomerListModel("Aashita Jaroli", "aashitajaroli@gmail.com", "9855555555,9855555555", "Customer"));
        customerList.add(new CustomerListModel("Gautam Surani", "gautamsurani@gmail.com", "9855555555,9855555555", "Account Manager"));
        customerListAdapter = new CustomerListAdapter(context);
        rec_view.setLayoutManager(new LinearLayoutManager(context));
        rec_view.setHasFixedSize(true);
        customerListAdapter.setList(customerList);

        rec_view.setAdapter(customerListAdapter);
        txtAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AddCustomerActivity.class);
                startActivity(i);
                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(getResources().getString(R.string.customer_list_title));
    }

    private void initComp(View view) {
        rec_view = view.findViewById(R.id.rec_view);
        txtAddCustomer = view.findViewById(R.id.txt_add_customer);
        tvTitle = context.findViewById(R.id.tvTitle);
    }
}
