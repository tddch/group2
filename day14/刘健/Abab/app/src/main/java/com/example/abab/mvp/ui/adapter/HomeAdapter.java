package com.example.abab.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abab.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> list;
    private Context context;

    public HomeAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_one, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
                ((ViewHolder) holder).tvCourse.setText(list.get(position));
                ((ViewHolder) holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if(i!=0){
                        strings.add(list.get(i));
                    }
                }

                HomeCourse course = new HomeCourse(strings, context);
                ((ViewHolder) holder).rv.setAdapter(course);
                course.notifyDataSetChanged();

        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_course)
        TextView tvCourse;
        @BindView(R.id.rv)
        RecyclerView rv;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
