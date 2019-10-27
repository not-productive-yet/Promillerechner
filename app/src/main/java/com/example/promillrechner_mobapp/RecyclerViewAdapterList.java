package com.example.promillrechner_mobapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapterList extends RecyclerView.Adapter<RecyclerViewAdapterList.ViewHolder> {
    private List<Person> persons = Collections.emptyList();

    Context mContext;
    OnItemClickListener mListener;
    public ImageView delete;
    private PersonDao dao;

    public interface OnItemClickListener {
        void onItemClick(Person position);
        void onDeleteClick(Person position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {mListener = listener;}


    public RecyclerViewAdapterList(Context context){
        mContext =context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail, parent, false);
        RecyclerView.ViewHolder holder = new RecyclerViewAdapterList.ViewHolder(view, mListener);
        return new RecyclerViewAdapterList.ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterList.ViewHolder holder, final int position) {
        dao = Room.getDatabase(mContext).person_dao();

        TextView textName = holder.itemView.findViewById(R.id.textName);
        textName.setText(persons.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        RelativeLayout parentLayout;


        public ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName1);
            parentLayout = itemView.findViewById(R.id.parentLayoutListDatabase);
            delete = itemView.findViewById(R.id.item_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(persons.get(getAdapterPosition()));
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(persons.get(getAdapterPosition()));
                }
            });
        }
    }
    public void setPersons(List<Person> persons){
        this.persons = persons;
        notifyDataSetChanged();
    }


}
