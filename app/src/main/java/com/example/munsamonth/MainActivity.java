package com.example.munsamonth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private Button createButton;
    private RecyclerView recyclerView;
    private ProjectAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createButton = (Button) findViewById(R.id.createButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        loadProjects();

        adapter = new ProjectAdapter(GlobalData.projectList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("STATE", "NEWPROJ");
                startActivity(new Intent(MainActivity.this, CreationDialog.class));
            }
        });
    }

    private void loadProjects() {
        GlobalData.loadProjects(this);
        Log.d("STATE", "LOADING CARDS");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("STATE", "DETECTED STOP, SAVING");
        GlobalData.saveProjects(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged(); // refresh the list
    }
}