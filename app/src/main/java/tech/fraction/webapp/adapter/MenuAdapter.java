package tech.fraction.webapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.R;
import tech.fraction.webapp.model.Menu;
import tech.fraction.webapp.model.MenuList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Menu> menuLists = new ArrayList<>();
    private Context context;

    private OnClickListener onClickListener;

    private int selectedItem = -1;

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int menuPosition, int subMenuPosition, int subSubMenuPosition);
    }

    public MenuAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setList(List<Menu> menuLists) {
        this.menuLists = menuLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Menu menuList = menuLists.get(position);
        holder.tvMenuName.setText(menuList.getDisplayName());

        if (menuList.getChildrens().size() == 0) {
            holder.ivSubMenu.setVisibility(View.GONE);
        } else {
            holder.ivSubMenu.setVisibility(View.VISIBLE);

            SubMenuAdapter subMenuAdapter = new SubMenuAdapter(context);
            subMenuAdapter.setList(menuList.getChildrens());
            holder.rvSubMenu.setLayoutManager(new LinearLayoutManager(context));
            holder.rvSubMenu.setAdapter(subMenuAdapter);

            subMenuAdapter.setOnItemClickListener(new SubMenuAdapter.OnClickListener() {
                @Override
                public void onClick(int subMenuPosition, int subSubMenuPosition) {
                    if (onClickListener != null) {
                        onClickListener.onClick(position, subMenuPosition, subSubMenuPosition);
                    } else {

                    }
                }
            });
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuList.getChildrens().size() == 0) {
                    if (onClickListener != null) {
                        onClickListener.onClick(position, -1, -1);
                    } else {

                    }
                } else {
                    if (holder.rvSubMenu.getVisibility() == View.VISIBLE) {
                        selectedItem = -1;
                    } else {
                        selectedItem = position;
                    }
                    notifyDataSetChanged();
                }
            }
        });

        if (selectedItem == position) {
            holder.rvSubMenu.setVisibility(View.VISIBLE);
            holder.llMenu.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            rotate(90, holder.ivSubMenu);
        } else {
            holder.rvSubMenu.setVisibility(View.GONE);
            holder.llMenu.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void rotate(float degree, ImageView imageView) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(500);
        rotateAnim.setFillAfter(true);
        imageView.startAnimation(rotateAnim);
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
        ImageView ivSubMenu;

        ViewHolder(View item) {
            super(item);
            tvMenuName = item.findViewById(R.id.tvMenuName);
            rvSubMenu = item.findViewById(R.id.rvSubMenu);
            llMain = item.findViewById(R.id.llMain);
            llMenu = item.findViewById(R.id.llMenu);
            ivSubMenu = item.findViewById(R.id.ivSubMenu);
        }
    }
}