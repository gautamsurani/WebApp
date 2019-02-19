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
import tech.fraction.webapp.model.OutwardItems;


public class OutwardProductAdapter extends RecyclerView.Adapter<OutwardProductAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<OutwardItems> outwardItems = new ArrayList<>();
    private Context context;


    public OutwardProductAdapter(Context context) {
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

    public void setList(List<OutwardItems> outwardItems) {
        this.outwardItems = outwardItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_outward_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.tvItemName.setText(outwardItems.get(position).getItemName());
        viewHolder.tvStock.setText(outwardItems.get(position).getOutwardQuantity() + "/" + outwardItems.get(position).getStock());
        viewHolder.tvUnloadingCharge.setText("Loading Charges: " + outwardItems.get(position).getLoadingCharges());
        viewHolder.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return outwardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName, tvStock, tvUnloadingCharge;
        RelativeLayout rlMain;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvItemName = item.findViewById(R.id.tvItemName);
            tvStock = item.findViewById(R.id.tvStock);
            tvUnloadingCharge = item.findViewById(R.id.tvUnloadingCharge);
            rlMain = item.findViewById(R.id.rlMain);
        }
    }
}
 
