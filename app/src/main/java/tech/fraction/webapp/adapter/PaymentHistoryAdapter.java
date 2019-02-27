package tech.fraction.webapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.InwardItemLocationPoco;
import tech.fraction.webapp.model.PastPaymentDetail;
import tech.fraction.webapp.util.Utils;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder> {


    private LayoutInflater inflater;

    private ArrayList<PastPaymentDetail> paymentHistory = new ArrayList<>();

    private Context context;


    public PaymentHistoryAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    private PaymentHistoryAdapter.OnClickListener onClickListener;

    public void setOnItemClickListener(PaymentHistoryAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }


    public void setList(ArrayList<PastPaymentDetail> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    @NonNull
    @Override
    public PaymentHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_payment_history, parent, false);

        return new PaymentHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentHistoryAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.tvPaidAmount.setText(paymentHistory.get(position).getSettlementAmount() + " " + context.getResources().getString(R.string.rs));
        viewHolder.tvDate.setText(Utils.FormatDate(paymentHistory.get(position).getSettlementDate()));
        if (paymentHistory.get(position).isProgressing()) {
            viewHolder.pbPayHisory.setVisibility(View.VISIBLE);
            viewHolder.imgDelete.setVisibility(View.GONE);
        } else {
            viewHolder.pbPayHisory.setVisibility(View.GONE);
            viewHolder.imgDelete.setVisibility(View.VISIBLE);
        }

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    paymentHistory.get(position).setProgressing(true);
                    notifyDataSetChanged();
                    onClickListener.onClick(position, 0);

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return paymentHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPaidAmount, tvDate;
        ImageView imgDelete;
        ProgressBar pbPayHisory;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvPaidAmount = item.findViewById(R.id.tvPaidAmount);
            tvDate = item.findViewById(R.id.tvDate);
            imgDelete = item.findViewById(R.id.imgDelete);
            pbPayHisory = item.findViewById(R.id.pbPayHisory);

        }
    }
}

