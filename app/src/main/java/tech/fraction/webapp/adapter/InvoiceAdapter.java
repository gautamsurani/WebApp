package tech.fraction.webapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.Invoices;
import tech.fraction.webapp.model.PersonInformationInvoice;
import tech.fraction.webapp.util.Utils;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<Invoices> invoiceList = new ArrayList<Invoices>();
    private Context context;
    private InvoiceProductAdapter invoiceProductAdapter;


    public InvoiceAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    private InvoiceAdapter.OnClickListener onClickListener;

    public void setOnItemClickListener(InvoiceAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public void setList(List<Invoices> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @NonNull
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_invoice, parent, false);

        return new InvoiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.tvName.setText(invoiceList.get(position).getAccountName());


        viewHolder.tvInvDate.setText(Utils.FormatDate(invoiceList.get(position).getGeneratedOn()) );
        viewHolder.tvPaidAmount.setText("PA: "+invoiceList.get(position).getPaidAmount()+context.getResources().getString(R.string.rs));
        viewHolder.tvInvNo.setText(invoiceList.get(position).getNumber());
        String s="TA: "+String.format("%.2f", invoiceList.get(position).getTotalAmount())+context.getResources().getString(R.string.rs);
        Spannable wordtoSpan = new SpannableString(s);

        wordtoSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)), 4, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        viewHolder.tvTotalAmount.setText(wordtoSpan);


        invoiceProductAdapter = new InvoiceProductAdapter(context);
        viewHolder.rec_view.setLayoutManager(new LinearLayoutManager(context));
        viewHolder.rec_view.setHasFixedSize(true);
        invoiceProductAdapter.setList(invoiceList.get(position).getInvoiceDetails());
        viewHolder.rec_view.setAdapter(invoiceProductAdapter);
        viewHolder.cardRowInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });
        viewHolder.tvPayNow.setOnClickListener(new View.OnClickListener() {
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
        return invoiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvInvDate, tvInvNo, tvPayNow,tvPaidAmount,tvTotalAmount;
        CardView cardRowInvoice;
        RecyclerView rec_view;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvName = item.findViewById(R.id.tvName);
            tvInvDate = item.findViewById(R.id.tvInvDate);
            tvInvNo = item.findViewById(R.id.tvInvNo);
            tvPayNow = item.findViewById(R.id.tvPayNow);
            tvPaidAmount = item.findViewById(R.id.tvPaidAmount);
            tvTotalAmount = item.findViewById(R.id.tvTotalAmount);
            cardRowInvoice = item.findViewById(R.id.cardRowInvoice);
            rec_view = item.findViewById(R.id.rec_view);

        }
    }
}

