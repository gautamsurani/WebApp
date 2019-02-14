package tech.fraction.webapp.adapter;

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
import tech.fraction.webapp.model.InvoiceDetails;
import tech.fraction.webapp.util.Utils;


public class SelectedOutwardAdapter extends RecyclerView.Adapter<SelectedOutwardAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<String> invoiceProductList = new ArrayList<>();
    private Context context;


    public SelectedOutwardAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    private SelectedOutwardAdapter.OnClickListener onClickListener;

    public void setOnItemClickListener(SelectedOutwardAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public void setList(List<String> invoiceProductList) {
        this.invoiceProductList = invoiceProductList;
    }

    @NonNull
    @Override
    public SelectedOutwardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_selected_outward, parent, false);

        return new SelectedOutwardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedOutwardAdapter.ViewHolder viewHolder, final int position) {




    }


    @Override
    public int getItemCount() {
        return invoiceProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName,tvItemUnit,tvStock, tvOutNo,tv0utDate,tvLocation;

        ImageView imgDelete;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvItemName = item.findViewById(R.id.tvItemName);
            imgDelete = item.findViewById(R.id.imgDelete);

        }
    }
}
 
