package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private  List<Note> notes;

    Adapter(Context context,List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custome_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            String title = notes.get(position).getTitle();
            String date = notes.get(position).getDate();
            String time = notes.get(position).getTime();
            long  id    = notes.get(position).getID();
            Log.v("IDcheck","ID->"+id);
            holder.nTitle.setText(title);
            holder.nDate.setText(date);
            holder.nTime.setText(time);
            holder.nId.setText(String.valueOf(notes.get(position).getID()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView nTitle,nDetails,nDate,nTime,nId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.ndate);
            nTime = itemView.findViewById(R.id.ntime);
            nId = itemView.findViewById(R.id.listId);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),Details.class);
                        i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                        Log.v("IDselect","ID->"+notes.get(getAdapterPosition()).getID());
                        v.getContext().startActivity(i);
                    }
                });

        }
    }
}
