package com.theopenschool.android.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.theopenschool.android.R;
import com.theopenschool.android.models.Course;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private RecyclerView rvCourses;

    private CourseAdapter courseAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        rvCourses = view.findViewById(R.id.main_rv_courses);
        rvCourses.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query query = rootRef.collection("courses");
        FirestoreRecyclerOptions<Course> options = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(query, Course.class)
                .build();

        courseAdapter = new CourseAdapter(options);
        rvCourses.setAdapter(courseAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
        courseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (courseAdapter != null) {
            courseAdapter.stopListening();
        }
    }

}