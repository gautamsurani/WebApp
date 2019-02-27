package tech.fraction.webapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.activity.PaymentHistoryActivity;
import tech.fraction.webapp.model.InventoryDetail;

public class InwordsAdapter extends RecyclerView.Adapter<InwordsAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private List<InventoryDetail> inventoryDetails = new ArrayList<>();

    private Context context;

    private OnClickListener onClickListener;

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public InwordsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setList(List<InventoryDetail> inventoryDetails) {
        this.inventoryDetails = inventoryDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_inword, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final InventoryDetail inventoryDetail = inventoryDetails.get(position);

        ProductAdapter productAdapter = new ProductAdapter(context);
        productAdapter.setList(inventoryDetail.getInwardItems());

        productAdapter.setOnItemClickListener(new ProductAdapter.OnClickListener() {
            @Override
            public void onClick(int i, int witch) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });

        holder.rvProduct.setLayoutManager(new LinearLayoutManager(context));
        holder.rvProduct.setAdapter(productAdapter);

        holder.tvName.setText(inventoryDetail.getAccountName());
        holder.tvInwardNo.setText(inventoryDetail.getInwardNo());

        NumberFormat nf = new DecimalFormat("#.##");
        Double d2 = Double.parseDouble(inventoryDetail.getPaidAmount());
        String s = nf.format(d2);

        holder.tvAmount.setText(" " + context.getResources().getString(R.string.rs) + s);
        holder.tvDate.setText(inventoryDetail.getInwardDateinDDMMYYYY());

        if (!inventoryDetails.get(position).getInvoiceMessage().isEmpty() && !inventoryDetails.get(position).getColor().isEmpty()) {
            holder.tvInvoiceDue.setTextColor(Color.parseColor(inventoryDetails.get(position).getColor()));
            holder.tvInvoiceDue.setText(inventoryDetails.get(position).getInvoiceMessage());
            holder.tvInvoiceDue.setVisibility(View.VISIBLE);
        } else {
            holder.tvInvoiceDue.setVisibility(View.GONE);
        }
        /*TO  DO change paynow  button according to paid status*/
        String paidStatus = inventoryDetail.getPaidStatus();
        if (paidStatus.equalsIgnoreCase("f")) {
            holder.tvPayNow.setText("View Pay History");
            holder.tvPayNow.setVisibility(View.VISIBLE);
        } else if (paidStatus.equalsIgnoreCase("p") || paidStatus.equalsIgnoreCase("z")) {
            holder.tvPayNow.setText("Pay Now");
            holder.tvPayNow.setVisibility(View.VISIBLE);

        } else {
            holder.tvPayNow.setVisibility(View.GONE);
        }

        holder.tvPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaymentHistoryActivity.class);
                intent.putExtra("inwardId", inventoryDetail.getInwardDetailId());
                context.startActivity(intent);
            }
        });

        holder.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });
    }

    public void clearData() {
        inventoryDetails.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return inventoryDetails.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvInwardNo, tvDate, tvAmount, tvPayNow, tvInvoiceDue;
        RelativeLayout rlMain;
        RecyclerView rvProduct;

        ViewHolder(View item) {
            super(item);
            tvName = item.findViewById(R.id.tvName);
            rlMain = item.findViewById(R.id.rlMain);
            rvProduct = item.findViewById(R.id.rvProduct);
            tvInwardNo = item.findViewById(R.id.tvInwardNo);
            tvDate = item.findViewById(R.id.tvDate);
            tvAmount = item.findViewById(R.id.tvAmount);
            tvPayNow = item.findViewById(R.id.tvPayNow);
            tvInvoiceDue = item.findViewById(R.id.tvInvoiceDue);
        }
    }
}