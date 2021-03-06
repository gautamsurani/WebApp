package tech.fraction.webapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.InwardItemLocationPoco;
import tech.fraction.webapp.model.OutwardItemLocations;

public class RacksOutwardAdapter extends RecyclerView.Adapter<RacksOutwardAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<OutwardItemLocations> racks = new ArrayList<>();
    private Context context;


    public RacksOutwardAdapter(Context context) {
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


    public void setList(List<OutwardItemLocations> racks) {
        this.racks = racks;
    }

    @NonNull
    @Override
    public RacksOutwardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_edit_item, parent, false);

        return new RacksOutwardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.tvlName.setText(racks.get(position).getRackName());

        viewHolder.imgClose.setOnClickListener(new View.OnClickListener() {
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
        return racks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvlName;
        ImageView imgClose;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvlName = item.findViewById(R.id.tvLNAME);
            imgClose = item.findViewById(R.id.IMG_CLOSE);

        }
    }
}
