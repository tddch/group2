package com.example.day_11.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day_11.R;
import com.example.day_11.adapter.RecyAdapter;
import com.example.day_11.bean.RecyBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class RightFragment extends Fragment {

    String BASE_URL = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/2";
    private RecyclerView recycler;
    private RecyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_right, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String gethttpString = gethttpString(BASE_URL);
                    RecyBean recyBean = new Gson().fromJson(gethttpString, RecyBean.class);
                    final List<RecyBean.ResultsBean> results = recyBean.getResults();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addbanner(results);
                            adapter.additem(results);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static final String TAG = "RightFragment";
    private String gethttpString(String url) throws IOException {
        InputStream inputStream = new URL(url).openConnection().getInputStream();
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            stringBuilder.append(new String(bytes, 0, len));
        }
        inputStream.close();
        Log.d(TAG, "gethttpString: "+stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void initView(View item) {
        recycler = item.findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapter = new RecyAdapter(getActivity());
        recycler.setAdapter(adapter);

    }

}
