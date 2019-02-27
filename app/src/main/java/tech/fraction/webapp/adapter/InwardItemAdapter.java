package tech.fraction.webapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.InwardItems;


public class InwardItemAdapter extends RecyclerView.Adapter<InwardItemAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private List<InwardItems> inwardItems = new ArrayList<>();

    private Context context;

    private boolean isEditable;

    private OnClickListener onClickListener;

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public InwardItemAdapter(Context context, boolean isEditable) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.isEditable = isEditable;
    }

    public void setList(List<InwardItems> inwardItems) {
        this.inwardItems = inwardItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (isEditable) {
            view = inflater.inflate(R.layout.row_inward_editdeataillist, parent, false);
        } else {
            view = inflater.inflate(R.layout.row_inward_deataillist, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        InwardItems items = inwardItems.get(position);

        holder.tvName.setText(items.getItemName());

        String location = "";
        if (items.getInwardLocationModel() != null) {
            for (int i = 0; i < items.getInwardLocationModel().size(); i++) {
                if (location.isEmpty()) {
                    location = " " + items.getInwardLocationModel().get(i).getRackName();
                } else {
                    location = location + ", " + items.getInwardLocationModel().get(i).getRackName();
                }
            }
        }

        holder.tvLocation.setText(location);

        holder.tv_unloadingCharges.setText(" " + context.getResources().getString(R.string.rs) + items.getUnloadingCharges());
        holder.tv_value.setText("0/" + items.getQuantity());
        holder.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });

        if (isEditable) {
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(position, 1);
                    }
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(position, 2);
                    }
                }
            });
        } else {

        }
    }

    public void clearData() {
        inwardItems.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return inwardItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tv_unloadingCharges, tv_value;
        RelativeLayout rlMain;
        ImageView edit, delete;

        ViewHolder(View item) {
            super(item);
            tvName = item.findViewById(R.id.txt_name);
            tvLocation = item.findViewById(R.id.txt_location);
            tv_unloadingCharges = item.findViewById(R.id.txt_unloading_charges);
            tv_value = item.findViewById(R.id.detail_value);
            rlMain = item.findViewById(R.id.rl_detail);
            if (isEditable) {
                edit = item.findViewById(R.id.edit);
                delete = item.findViewById(R.id.delete);
            }
        }
    }
}