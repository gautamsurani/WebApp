package tech.fraction.webapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.InventoryDetailOutward;


public class OutwardListAdapter extends RecyclerView.Adapter<OutwardListAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private List<InventoryDetailOutward> outWardList = new ArrayList<>();

    private Context context;

    private OutwardProductAdapter outwardProductAdapter;

    public OutwardListAdapter(Context context) {
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

    public void setList(List<InventoryDetailOutward> outWardList) {
        this.outWardList = outWardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_outwardlist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.tvName.setText(outWardList.get(position).getAccountName());
        viewHolder.tvOutNo.setText("OUT" + outWardList.get(position).getOutwardNo());


        viewHolder.tv0utDate.setText(outWardList.get(position).getOutwardDateinDDMMYYYY());
        viewHolder.cardRowOutword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });

        outwardProductAdapter = new OutwardProductAdapter(context);
        outwardProductAdapter.setOnItemClickListener(new OutwardProductAdapter.OnClickListener() {
            @Override
            public void onClick(int i, int witch) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, 0);
                }
            }
        });
        viewHolder.rec_view.setLayoutManager(new LinearLayoutManager(context));
        viewHolder.rec_view.setHasFixedSize(true);
        outwardProductAdapter.setList(outWardList.get(position).getOutwardItems());
        viewHolder.rec_view.setAdapter(outwardProductAdapter);

    }

    @Override
    public int getItemCount() {
        return outWardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvOutNo, tv0utDate, tvDate;
        CardView cardRowOutword;
        RecyclerView rec_view;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvName = item.findViewById(R.id.tvName);
            tvOutNo = item.findViewById(R.id.tvOutNo);
            tv0utDate = item.findViewById(R.id.tv0utDate);
            cardRowOutword = item.findViewById(R.id.cardRowOutword);
            rec_view = item.findViewById(R.id.rec_view);

        }
    }
}