package com.seyma.chatactivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<UserModel> userModelList;

    public UserAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList; // Dışarıdan gelen listeyi kullan
    }

    public void add(UserModel userModel){
        userModelList.add(userModel);
        notifyItemInserted(userModelList.size() - 1);
    }

    public void clear(){
        userModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        UserModel userModel = userModelList.get(position);
        holder.name.setText(userModel.getUserName());
        holder.email.setText(userModel.getUserEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("id", userModel.getUserID());
                intent.putExtra("name", userModel.getUserName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public List<UserModel> getUserModelList() { // Büyük harf 'L'
        return userModelList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.useremail);
        }
    }
}
