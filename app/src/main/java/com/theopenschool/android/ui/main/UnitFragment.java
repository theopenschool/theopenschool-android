package com.theopenschool.android.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.theopenschool.android.R;
import com.theopenschool.android.models.Course;

import java.util.List;

public class UnitFragment extends Fragment {

    private TextView tvContent;

    private int position;

    public UnitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = UnitFragmentArgs.fromBundle(getArguments()).getPosition();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unit, container, false);

        TextView tvContent = view.findViewById(R.id.unit_tv_content);

        String courseId = UnitFragmentArgs.fromBundle(getArguments()).getCourseId();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("courses").document(courseId).collection("sections").whereEqualTo("position", position)
                .limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                    tvContent.setText(documentSnapshots.get(0).getString("type"));

                    //List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();
                    //tvContent.setText(.getString("text"));
                } else {
                    Log.d("Error", "Error getting documents: ", task.getException());
                }
            }
        });


        return view;
    }
}