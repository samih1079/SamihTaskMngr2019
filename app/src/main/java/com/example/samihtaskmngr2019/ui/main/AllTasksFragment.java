package com.example.samihtaskmngr2019.ui.main;


import android.os.Bundle;

import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.samihtaskmngr2019.R;
import com.example.samihtaskmngr2019.data.MyTask;
import com.example.samihtaskmngr2019.data.TasksAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTasksFragment extends Fragment {

    private TasksAdapter tasksAdapter;
    private ListView lvTasks;

    //0 search: add ET amd Btn to xml
    //1 search:
    private ImageView imSearch;
    private EditText etTitleTosearch;

    public AllTasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tasksAdapter=new TasksAdapter(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);
        lvTasks=view.findViewById(R.id.lstvTasks);
        lvTasks.setAdapter(tasksAdapter);
        //2 search:
        imSearch=view.findViewById(R.id.imSearch);
        etTitleTosearch=view.findViewById(R.id.etTitleTosearch);
        //3 search event:
        imSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSearch= etTitleTosearch.getText().toString();
                //5 search
                readTasksFromFirebase(toSearch);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //6 search: delete method calling
        readTasksFromFirebase("");
    }
                        //4 search: add parameter toi search
    public void readTask    sFromFirebase(final String stTosearch)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();//to connect to database
        FirebaseAuth auth=FirebaseAuth.getInstance();//to get current UID
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();
                                           //orderByChild("title").equalTo(stTosearch)// 5+6
        reference.child("tasks").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                tasksAdapter.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    MyTask t=d.getValue(MyTask.class);
                    Log.d("MYTASK",t.toString());
                    //5 search:
                    if(stTosearch==null || stTosearch.length()==0)
                    {
                        tasksAdapter.add(t);

                    }
                    else //6 search:
                    if(t.getTitle().contains(stTosearch))
                        tasksAdapter.add(t);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
