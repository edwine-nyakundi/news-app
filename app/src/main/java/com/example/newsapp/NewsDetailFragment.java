package com.example.newsapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.bumptech.glide.Glide;

public class NewsDetailFragment extends Fragment {

    private static final String ARG_NEWS_ITEM = "news_item";

    private NewsItem newsItem;
    private RecyclerView relatedRecyclerView;
    private RelatedNewsAdapter relatedNewsAdapter;

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    public static NewsDetailFragment newInstance(NewsItem newsItem) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NEWS_ITEM, newsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsItem = getArguments().getParcelable(ARG_NEWS_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        ImageView newsImage = view.findViewById(R.id.news_image);
        TextView newsDescription = view.findViewById(R.id.news_description);
        relatedRecyclerView = view.findViewById(R.id.related_recycler_view);

        Glide.with(this).load(newsItem.getImageUrl()).into(newsImage);
        newsDescription.setText(newsItem.getDescription());

        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        relatedNewsAdapter = new RelatedNewsAdapter(getRelatedNewsData());
        relatedRecyclerView.setAdapter(relatedNewsAdapter);

        return view;
    }

    private List<NewsItem> getRelatedNewsData() {
        List<NewsItem> relatedNews = new ArrayList<>();
        // Add dummy related news data
        relatedNews.add(new NewsItem("Related Title 1", "Related Description 1", "https://example.com/related_image1.jpg"));
        relatedNews.add(new NewsItem("Related Title 2", "Related Description 2", "https://example.com/related_image2.jpg"));
        return relatedNews;
    }
}
