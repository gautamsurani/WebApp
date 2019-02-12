package tech.fraction.webapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.CustomerListModel;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<CustomerListModel> customerList = new ArrayList<>();
    private Context context;

    public CustomerListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    private OnClickListener onClickListener;

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public void setList(List<CustomerListModel> customerList) {
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_customer_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        viewHolder.tvName.setText(customerList.get(position).getName());
        viewHolder.tvEmailId.setText(customerList.get(position).getEmailId());
        viewHolder.tvPhoneNo.setText(customerList.get(position).getPhoneNo());
        SpannableStringBuilder str = new SpannableStringBuilder("Role : " + customerList.get(position).getRole());
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.tvRole.setText(str);
        viewHolder.rlCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmailId, tvPhoneNo, tvRole;
        CardView rlCustomer;
        ImageView imgCustomer;

        ViewHolder(@NonNull View item) {
            super(item);
            imgCustomer = item.findViewById(R.id.img_customer);
            tvName = item.findViewById(R.id.txt_name);
            tvEmailId = item.findViewById(R.id.txt_email);
            tvPhoneNo = item.findViewById(R.id.txt_phone_no);
            tvRole = item.findViewById(R.id.txt_role);
            rlCustomer = item.findViewById(R.id.rl_customer);

        }
    }
}