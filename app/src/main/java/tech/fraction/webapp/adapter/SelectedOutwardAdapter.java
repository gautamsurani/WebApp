package tech.fraction.webapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.InvoiceDetails;
import tech.fraction.webapp.model.OutwardDetails;
import tech.fraction.webapp.util.Utils;


public class SelectedOutwardAdapter extends RecyclerView.Adapter<SelectedOutwardAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<OutwardDetails> outwardDetails = new ArrayList<>();
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

    public void setList(List<OutwardDetails> outwardDetails) {
        this.outwardDetails = outwardDetails;
    }

    @NonNull
    @Override
    public SelectedOutwardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_selected_outward, parent, false);

        return new SelectedOutwardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectedOutwardAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.tvItemName.setText(outwardDetails.get(position).getItemName());

        String location = "";
        for (int i = 0; i < outwardDetails.get(position).getInwardItemLocationPoco().size(); i++) {
            if (location.isEmpty()) {
                location = outwardDetails.get(position).getInwardItemLocationPoco().get(i).getRackName();
            } else {
                location = location + ", " + outwardDetails.get(position).getInwardItemLocationPoco().get(i).getRackName();
            }
        }
        viewHolder.tvLocation.setText("Location: " + location);
        if (!outwardDetails.get(position).getInwardedOn().isEmpty()) {
            viewHolder.tv0utDate.setText(Utils.FormatDate(outwardDetails.get(position).getInwardedOn()));
        } else {
            viewHolder.tv0utDate.setText("");
        }
        viewHolder.tvOutNo.setText(Utils.ifIsStringNull(outwardDetails.get(position).getInwardDetail().getNumber()));

        viewHolder.etQty.setText(String.valueOf(outwardDetails.get(position).getOutwardQuantity()));
        viewHolder.etLC.setText(String.valueOf(outwardDetails.get(position).getLoadingCharges()));
        viewHolder.etOC.setText(String.valueOf(outwardDetails.get(position).getOtherCharges()));
        viewHolder.tvStock.setText("Stock : " + outwardDetails.get(position).getStock() + " / " + outwardDetails.get(position).getQuantity());
        viewHolder.tvItemUnit.setText(outwardDetails.get(position).getItemName() + "-" + outwardDetails.get(position).getUnitName());

        viewHolder.etQty.addTextChangedListener(new TextWatcher() {
            String qty;

            public void afterTextChanged(Editable s) {
                if (!qty.isEmpty()) {
                    outwardDetails.get(position).setOutwardQuantity(Integer.parseInt(qty));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                qty = s.toString();
                if (!qty.isEmpty()) {

                    if (Integer.parseInt(qty) > outwardDetails.get(position).getStock()) {
                        final Dialog openDialog = new Dialog(context);
                        openDialog.setContentView(R.layout.customdialog_layout);
                        Window window = openDialog.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        openDialog.setTitle("Custom Dialog Box");
                        TextView tvOkay = openDialog.findViewById(R.id.tvOkay);
                        TextView tvMessage = openDialog.findViewById(R.id.tvMessage);
                        tvMessage.setText(outwardDetails.get(position).getItemName() + " - " + outwardDetails.get(position).getUnitName() + " available stock is " +
                                outwardDetails.get(position).getStock() + "\n" + "Please Enter Proper Quantity");
                        tvOkay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openDialog.dismiss();
                                viewHolder.etQty.setText("");

                            }
                        });
                        openDialog.show();
                    }
                }

            }

        });

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position, 2);
            }
        });
    }


    @Override
    public int getItemCount() {
        return outwardDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName, tvItemUnit, tvStock, tvOutNo, tv0utDate, tvLocation;

        EditText etLC, etOC, etQty;
        ImageView imgDelete;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvItemName = item.findViewById(R.id.tvItemName);
            imgDelete = item.findViewById(R.id.imgDelete);
            tvItemUnit = item.findViewById(R.id.tvItemUnit);
            tvStock = item.findViewById(R.id.tvStock);
            tvOutNo = item.findViewById(R.id.tvOutNo);
            tv0utDate = item.findViewById(R.id.tv0utDate);
            tvLocation = item.findViewById(R.id.tvLocation);
            etLC = item.findViewById(R.id.etLC);
            etOC = item.findViewById(R.id.etOC);
            etQty = item.findViewById(R.id.etQty);

        }
    }
}
 
