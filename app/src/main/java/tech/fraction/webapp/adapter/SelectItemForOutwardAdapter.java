package tech.fraction.webapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.OutwardDetails;

public class SelectItemForOutwardAdapter extends RecyclerView.Adapter<SelectItemForOutwardAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<OutwardDetails> outwardDetails = new ArrayList<>();
    private Context context;

    public SelectItemForOutwardAdapter(Context context) {
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

    public void setList(List<OutwardDetails> outwardDetails) {
        this.outwardDetails = outwardDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_select_item_for_outward, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        viewHolder.tvInwardNo.setText(outwardDetails.get(position).getInwardDetail().getNumber());
        viewHolder.tvName.setText(outwardDetails.get(position).getItemName() + "-" + outwardDetails.get(position).getUnitName());

        viewHolder.tvStock.setText("Stock : " + outwardDetails.get(position).getStock() + " / " + outwardDetails.get(position).getQuantity());

        if (outwardDetails.get(position).isSelected()) {
            viewHolder.cBox.setChecked(true);

            viewHolder.llMain.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            viewHolder.tvInwardNo.setTextColor(context.getResources().getColor(R.color.white));
            viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.white));
            viewHolder.tvStock.setTextColor(context.getResources().getColor(R.color.white));

        } else {
            viewHolder.cBox.setChecked(false);

            viewHolder.llMain.setBackgroundColor(context.getResources().getColor(R.color.white));
            viewHolder.tvInwardNo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.light_black));
            viewHolder.tvStock.setTextColor(context.getResources().getColor(R.color.light_black));

        }

        viewHolder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (outwardDetails.get(position).isSelected()) {
                    outwardDetails.get(position).setSelected(false);
                } else {
                    outwardDetails.get(position).setSelected(true);
                }
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return outwardDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInwardNo, tvName, tvStock;
        LinearLayout llMain;
        CheckBox cBox;
        CardView SelectedCardView;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvInwardNo = item.findViewById(R.id.tvInwardNo);
            tvName = item.findViewById(R.id.tvName);
            cBox = item.findViewById(R.id.cBox);
            tvStock = item.findViewById(R.id.tvStock);
            llMain = item.findViewById(R.id.llMain);

            SelectedCardView = item.findViewById(R.id.SelectedCardView);
        }
    }
}