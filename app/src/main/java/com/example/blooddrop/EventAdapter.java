package com.example.blooddrop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    Context context;
    ArrayList<Event1> list;

    public EventAdapter(Context context, ArrayList<Event1> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.evententry,parent,false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event1 event = list.get(position);
        holder.event.setText(event.getEvent());
        holder.date.setText(event.getDate());
        holder.time.setText(event.getTime());
        holder.venue.setText(event.getVenue());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView event, date, time, venue;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            event = itemView.findViewById(R.id.txtevent);
            date = itemView.findViewById(R.id.txtdate);
            time  = itemView.findViewById(R.id.txttime);
            venue = itemView.findViewById(R.id.txtvenue);

        }
    }
}
