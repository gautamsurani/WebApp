package tech.fraction.webapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.Childrens;
import tech.fraction.webapp.model.SubSubMenuList;

public class SubSubMenuAdapter extends RecyclerView.Adapter<SubSubMenuAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Childrens> menuLists = new ArrayList<>();
    private Context context;

    private OnClickListener onClickListener;

    private int selectedItem = -1;

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    public SubSubMenuAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setList(List<Childrens> menuLists) {
        this.menuLists = menuLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_sub_sub_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Childrens menuList = menuLists.get(position);

        holder.tvMenuName.setText(menuList.getDisplayName());

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            }
        });
    }

    public void clearData() {
        menuLists.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return menuLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMenuName;
        LinearLayout llMain, llMenu;
        RecyclerView rvSubMenu;

        ViewHolder(View item) {
            super(item);
            tvMenuName = item.findViewById(R.id.tvMenuName);
            rvSubMenu = item.findViewById(R.id.rvSubMenu);
            llMain = item.findViewById(R.id.llMain);
            llMenu = item.findViewById(R.id.llMenu);
        }
    }
}