package tech.fraction.webapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.ProductList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<InwardItems> inwardItems = new ArrayList<>();
    private Context context;

    private OnClickListener onClickListener;

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public ProductAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setList(List<InwardItems> inwardItems) {
        this.inwardItems = inwardItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        InwardItems inwardItems1 = inwardItems.get(position);
        holder.tvProductName.setText(inwardItems1.getItemName());
        holder.tvCount.setText(inwardItems1.getStock() + " / " + inwardItems1.getQuantity());
        holder.tvUnloadCharge.setText("Unloading Charge: " + inwardItems1.getUnloadingCharges());
        holder.layout_view_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });
    }

    public void clearData() {
        inwardItems.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return inwardItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvCount, tvUnloadCharge;
        LinearLayout layout_view_one;

        ViewHolder(View item) {
            super(item);
            tvProductName = item.findViewById(R.id.tvProductName);
            tvCount = item.findViewById(R.id.tvCount);
            tvUnloadCharge = item.findViewById(R.id.tvUnloadCharge);
            layout_view_one = item.findViewById(R.id.layout_view_one);
        }
    }
}