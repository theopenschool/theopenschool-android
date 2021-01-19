package com.theopenschool.android.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.theopenschool.android.R;
import com.theopenschool.android.models.Course;

public class CourseAdapter extends FirestoreRecyclerAdapter<Course, CourseAdapter.CourseViewholder> {

    private static ClickListener clickListener;

    public CourseAdapter(@NonNull FirestoreRecyclerOptions<Course> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CourseViewholder holder, int position, @NonNull Course model) {
        holder.title.setText(model.getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onItemClick(position, CourseAdapter.this.getItem(position), v);
                }
            }
        });
    }

    @NonNull
    @Override
    public CourseViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_course_item, parent, false);
        return new CourseAdapter.CourseViewholder(view);
    }

    public class CourseViewholder extends RecyclerView.ViewHolder {
        View view;
        TextView title;
        public CourseViewholder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            title = itemView.findViewById(R.id.main_course_item_tv_title);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        CourseAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, Course course, View v);
    }
}

