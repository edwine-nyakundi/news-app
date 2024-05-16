package com.example.newsapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        newsAdapter = new NewsAdapter(getNewsData(), newsItem -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).openNewsDetail(newsItem);
            }
        });
        recyclerView.setAdapter(newsAdapter);

        return view;
    }

    private List<NewsItem> getNewsData() {
        List<NewsItem> newsItems = new ArrayList<>();
        // Add dummy data
        newsItems.add(new NewsItem("Title 1", "Description 1", "https://example.com/image1.jpg"));
        newsItems.add(new NewsItem("Title 2", "Description 2", "https://example.com/image2.jpg"));
        return newsItems;
    }
}
