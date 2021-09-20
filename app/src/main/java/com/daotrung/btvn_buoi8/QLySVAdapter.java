package com.daotrung.btvn_buoi8;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QLySVAdapter extends RecyclerView.Adapter<QLySVAdapter.MyViewHolder>{
    private final ArrayList<QLySV> mData;
    LayoutInflater mInflater ;
    ItemClickListener mClickListener ;

    public QLySVAdapter(ArrayList<QLySV> data,Context context) {
        this.mData = data ;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_dong,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtName.setText(mData.get(position).getName());
        holder.txtAddress.setText(mData.get(position).getAddress());
        holder.txtPhone.setText(mData.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public QLySV getItemid(int id){
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {

        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgUser , imgDel ;
        TextView txtName , txtAddress , txtPhone ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDel = itemView.findViewById(R.id.img_del);
            imgUser = itemView.findViewById(R.id.img_user);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAddress = itemView.findViewById(R.id.txt_address);
            txtPhone = itemView.findViewById(R.id.txt_phone);
            imgDel.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
              if(mClickListener !=null)
                  mClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
