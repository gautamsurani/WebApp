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
import tech.fraction.webapp.model.SearchTextViewModel;

public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<SearchTextViewModel> outWardList = new ArrayList<>();
    private Context context;


    public SearchActivityAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    private SearchActivityAdapter.OnClickListener onClickListener;

    public void setOnItemClickListener(SearchActivityAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int witch);
    }

    public void setList(List<SearchTextViewModel> outWardList) {
        this.outWardList = outWardList;
    }

    @NonNull
    @Override
    public SearchActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;

        view = inflater.inflate(R.layout.row_search, parent, false);

        return new SearchActivityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchActivityAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.tvSearch.setText(outWardList.get(position).getName());

        viewHolder.tvSearch.setOnClickListener(new View.OnClickListener() {
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
        return outWardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSearch;

        public ViewHolder(@NonNull View item) {
            super(item);
            tvSearch = item.findViewById(R.id.txt_search);

        }
    }
}

