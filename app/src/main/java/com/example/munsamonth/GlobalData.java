package com.example.munsamonth;

import android.content.SharedPreferences;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class GlobalData {
    public static ArrayList<FinanceProject> projectList = new ArrayList<>();
    public static void saveProjects(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(GlobalData.projectList);
        prefs.edit().putString("projects", json).apply();
        Log.d("STATE", "SAVING PROJECTS");
    }
    public static void loadProjects(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("projects", null);
        Type type = new TypeToken<ArrayList<FinanceProject>>(){}.getType();
        ArrayList<FinanceProject> list = gson.fromJson(json, type);
        if (list != null) GlobalData.projectList = list;
        Log.d("STATE", "LOADING PROJECTS");

    }
}
