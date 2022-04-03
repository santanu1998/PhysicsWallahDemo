package com.worldscience.physicswallahdemoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    List<Teachers> teachersList;

    public MyAdapter(Context context, List<Teachers> teachersList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.teachersList = teachersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(teachersList.get(position).getName());
        holder.subject.setText(teachersList.get(position).getSubjects());
        holder.qualification.setText(teachersList.get(position).getQualification());
        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MyAdapter.this, "Showing", Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.get().load(teachersList.get(position).getProfileImage()).into(holder.profile);
    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, subject, qualification;
        Button viewMore;
        ImageView profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            subject = itemView.findViewById(R.id.tvSubject);
            qualification = itemView.findViewById(R.id.tvQualification);
            viewMore = itemView.findViewById(R.id.viewMore);
            profile = itemView.findViewById(R.id.profileImage);
        }
    }

}
