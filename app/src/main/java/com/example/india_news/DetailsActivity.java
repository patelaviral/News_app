package com.example.india_news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.india_news.Modules.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    NewsHeadlines headlines;

    TextView txt_content, txt_author, txt_time, txt_details, txt_Title;
    ImageView img_news;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_author = findViewById(R.id.text_details_author);
        txt_content = findViewById(R.id.text_detail_content);
        txt_details = findViewById(R.id.text_detail_details);
        txt_Title = findViewById(R.id.text_details_title);
        txt_time = findViewById(R.id.text_detail_time);
        img_news = findViewById(R.id.img_details_News);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        assert headlines != null;
        txt_Title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_time.setText(headlines.getPublishedAt());
        txt_details.setText(headlines.getDescription());
        txt_content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(img_news);
    }
}