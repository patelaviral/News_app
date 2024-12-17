package com.example.india_news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.india_news.Modules.NewsApiResponse;
import com.example.india_news.Modules.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdaptor adaptor;
    ProgressDialog dialog;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);

        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);

        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);
        

        dialog  = new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles..");
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);
    }

    private final onFetchDataListener<NewsApiResponse> listener = new onFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adaptor = new CustomAdaptor(this,list,this);
        recyclerView.setAdapter(adaptor);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                .putExtra("data",headlines));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        dialog.setTitle("Fetching News articles of "+category);
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,category,null);
    }
}