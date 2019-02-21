package tech.fraction.webapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tech.fraction.webapp.R;
import tech.fraction.webapp.adapter.SearchActivityAdapter;
import tech.fraction.webapp.model.SearchTextViewModel;
import tech.fraction.webapp.util.Utils;

public class SearchTextViewActivity extends AppCompatActivity {

    EditText edtSearch;
    Intent intent;
    TextView tvNoFound;
    ArrayList<SearchTextViewModel> itemList;
    RecyclerView rec_view;
    SearchActivityAdapter searchActivityAdapter;
    ArrayList<SearchTextViewModel> searchList = new ArrayList<>();
    String type;
    ImageView ivBack;
    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_text_view);

        context = this;

        rec_view = findViewById(R.id.rec_view);
        tvNoFound = findViewById(R.id.tv_no_found);
        edtSearch = findViewById(R.id.edt_search);
        ivBack = findViewById(R.id.ivBack);
        itemList = new ArrayList<>();
        intent = getIntent();
        itemList = (ArrayList<SearchTextViewModel>) getIntent().getSerializableExtra("itemsList");
        type = getIntent().getStringExtra("type");

        searchActivityAdapter = new SearchActivityAdapter(SearchTextViewActivity.this);
        rec_view.setLayoutManager(new LinearLayoutManager(this));

        rec_view.setHasFixedSize(true);
        searchActivityAdapter.setList(itemList);
        searchActivityAdapter.setOnItemClickListener(new SearchActivityAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int witch) {
                Utils.hideKeyboard(context);
                SearchTextViewModel searchItem = itemList.get(position);
                Intent intent = new Intent();
                intent.putExtra("search", searchItem);
                intent.putExtra("type", type);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        rec_view.setAdapter(searchActivityAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchList.clear();
                try {
                    for (SearchTextViewModel c : itemList) {
                        if (c.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                            searchList.add(c);
                        }
                    }
                    searchActivityAdapter = new SearchActivityAdapter(SearchTextViewActivity.this);
                    searchActivityAdapter.setList(searchList);
                    rec_view.setLayoutManager(new LinearLayoutManager(SearchTextViewActivity.this));
                    rec_view.setAdapter(searchActivityAdapter);
                    searchActivityAdapter.setOnItemClickListener(new SearchActivityAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position, int witch) {
                            Utils.hideKeyboard(context);
                            SearchTextViewModel searchItem = searchList.get(position);
                            Intent intent = new Intent();
                            intent.putExtra("search", searchItem);
                            intent.putExtra("type", type);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }
                    });

                    if (searchList.size() == 0) {
                        tvNoFound.setVisibility(View.VISIBLE);
                        rec_view.setVisibility(View.GONE);
                    } else {
                        rec_view.setVisibility(View.VISIBLE);
                        tvNoFound.setVisibility(View.GONE);
                    }
                } catch (NullPointerException ne) {
                    ne.getMessage();
                }
            }
        });
    }
}