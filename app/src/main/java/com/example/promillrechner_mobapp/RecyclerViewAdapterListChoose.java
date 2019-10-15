package com.example.promillrechner_mobapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.promillrechner_mobapp.calculator.Alcohol;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapterListChoose extends RecyclerView.Adapter<RecyclerViewAdapterListChoose.ViewHolder> implements View.OnClickListener {

    //private static RecyclerViewClickListener itemListener;
    private List<Person> persons = Collections.emptyList();


    Context mContext;
    private PersonDao dao;
    public RecyclerViewAdapterListChoose(Context context){
        mContext =context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_simple, parent, false);
        RecyclerView.ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterListChoose.ViewHolder holder, final int position) {
        dao = Room.getDatabase(mContext).person_dao();

        TextView textName = holder.itemView.findViewById(R.id.textName1);
        textName.setText(persons.get(position).getName());

        holder.parentLayout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), Alcohol.class);
        v.getContext().startActivity(intent);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName1);
            parentLayout = itemView.findViewById(R.id.parentLayoutList);
        }
    }
    public void setPersons(List<Person> persons){
        this.persons = persons;
        notifyDataSetChanged();
    }

    class DeleteAlcoholTask extends AsyncTask<Person, Void, List<Person>> {

        @Override
        protected List<Person> doInBackground(Person... People) {
                dao.delete(People[0]);
                return dao.getAll();
            }
        @Override
        protected void onPostExecute(List<Person> People){
            super.onPostExecute(People);
            setPersons(People);
        }
        }

    }
