package tech.fraction.webapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import tech.fraction.webapp.R;
import tech.fraction.webapp.model.InvoiceDetails;
import tech.fraction.webapp.util.Utils;


public class InvoiceProductAdapter extends RecyclerView.Adapter<InvoiceProductAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<InvoiceDetails> invoiceProductList = new ArrayList<>();
    private Context context;


    public InvoiceProductAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    private InvoiceProductAdapter.OnClickListener onClickListener;

    public void setOnItemClickListener(InvoiceProductAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public void setList(List<InvoiceDetails> invoiceProductList) {
        this.invoiceProductList = invoiceProductList;
    }

    @NonNull
    @Override
    public InvoiceProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_invoice_product, parent, false);

        return new InvoiceProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceProductAdapter.ViewHolder viewHolder, final int position) {


        viewHolder.tvItemName.setText(invoiceProductList.get(position).getItemName() + invoiceProductList.get(position).getWeight());
        viewHolder.tvItemPrice.setText(String.format("%.2f", invoiceProductList.get(position).getTotalAmount()) + context.getResources().getString(R.string.rs));
        viewHolder.tvDate.setText(Utils.FormatDate(invoiceProductList.get(position).getFromDate()) + " to " + Utils.FormatDate(invoiceProductList.get(position).getToDate()));
        viewHolder.recRowProductInvoice.setOnClickListener(new View.OnClickListener() {
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
        return invoiceProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName, tvItemPrice, tvDate;
        RelativeLayout recRowProductInvoice;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvItemName = item.findViewById(R.id.tvItemName);
            tvItemPrice = item.findViewById(R.id.tvItemPrice);
            tvDate = item.findViewById(R.id.tvDate);
            recRowProductInvoice = item.findViewById(R.id.recRowProductInvoice);

        }
    }
}
 
