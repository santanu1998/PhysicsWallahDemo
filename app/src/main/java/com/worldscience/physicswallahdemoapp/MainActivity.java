package com.worldscience.physicswallahdemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Teachers> teachersList;
    private static String JSON_URL = "https://my-json-server.typicode.com/easygautam/data/users";
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.profileList);
        teachersList = new ArrayList<>();
        extractDetails();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        myAdapter = new MyAdapter(this, teachersList);
//        recyclerView.setAdapter(myAdapter);
    }

    private void extractDetails() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject teachersObject = response.getJSONObject(i);
                        Teachers teachers = new Teachers();
                        teachers.setName(teachersObject.getString("name").toString());
                        teachers.setSubjects(teachersObject.getString("subjects").toString());
                        teachers.setQualification(teachersObject.getString("qualification").toString());
//                        teachers.setId(teachersObject.getString("id").toString());
                        teachers.setProfileImage(teachersObject.getString("profileImage").toString());
                        teachersList.add(teachers);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    myAdapter = new MyAdapter(getApplicationContext(), teachersList);
                    recyclerView.setAdapter(myAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}