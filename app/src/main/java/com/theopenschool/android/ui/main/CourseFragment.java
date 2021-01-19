package com.theopenschool.android.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.theopenschool.android.R;
import com.theopenschool.android.models.Course;

public class CourseFragment extends Fragment {

    private TextView tvTitle;
    private Button btnDownload;

    private String courseId;
    private Course course;

    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        courseId = CourseFragmentArgs.fromBundle(getArguments()).getCourseId();
        course = CourseFragmentArgs.fromBundle(getArguments()).getCourse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        tvTitle = view.findViewById(R.id.course_tv_title);
        tvTitle.setText(course.getTitle());

        btnDownload = view.findViewById(R.id.course_btn_download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("courses").document(courseId).collection("sections")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            CourseFragmentDirections.ActionCourseFragmentToUnitFragment action = CourseFragmentDirections.actionCourseFragmentToUnitFragment(courseId, 0);
                            NavHostFragment.findNavController(CourseFragment.this).navigate(action);

                            //List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();
                            //tvContent.setText(.getString("text"));
                        } else {
                            Log.d("Error", "Error getting documents: ", task.getException());
                        }
                    }
                });
            }
        });

        return view;
    }
}