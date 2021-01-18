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

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class CourseAdapter extends FirestoreRecyclerAdapter<Course, CourseAdapter.CourseViewholder> {

    public CourseAdapter(@NonNull FirestoreRecyclerOptions<Course> options) {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull CourseViewholder holder, int position, @NonNull Course model) {
        holder.title.setText(model.getTitle());
    }

    @NonNull
    @Override
    public CourseViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_course_item, parent, false);
        return new CourseAdapter.CourseViewholder(view);
    }

    class CourseViewholder extends RecyclerView.ViewHolder {
        TextView title;
        public CourseViewholder(@NonNull View itemView)
        {
            super(itemView);

            title = itemView.findViewById(R.id.main_course_item_tv_title);
        }
    }
}

