package com.example.tutor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class TutorAdapter extends FirebaseRecyclerAdapter<RegisterUser, TutorAdapter.TutorViewHolder>{
    Context context;

    public TutorAdapter(@NonNull FirebaseRecyclerOptions<RegisterUser> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull TutorViewHolder holder, int position, @NonNull RegisterUser model) {
        holder.tvTutor_Name.setText(model.getName());
        holder.tvTutor_Price.setText(model.getCharges());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TutorDetails.class);
                intent.putExtra("id", getRef(position).getKey());
                context.startActivity(intent);

            }
        });
      //  Glide.with(holder.profile_Picture.getContext()).load(model.getImage()).into(holder.profile_Picture);


    }

    @NonNull
    @Override
    public TutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tutors_view, parent, false);

        return new TutorViewHolder(view);
    }

    public class TutorViewHolder extends RecyclerView.ViewHolder{
        TextView tvTutor_Name,tvTutor_Price;
        CircleImageView profile_Picture;
            public TutorViewHolder(@NonNull View itemView) {
                super(itemView);
               init();

            }

        private void init() {
            tvTutor_Name = itemView.findViewById(R.id.tvTutor_Name);
            tvTutor_Price = itemView.findViewById(R.id.tvTutor_Price);
            profile_Picture = itemView.findViewById(R.id.Detail_profile_Picture);



        }
    }
}