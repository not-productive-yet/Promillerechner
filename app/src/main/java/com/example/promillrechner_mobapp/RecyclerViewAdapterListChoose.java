package com.example.promillrechner_mobapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapterListChoose extends RecyclerView.Adapter<RecyclerViewAdapterListChoose.ViewHolder> {

    private List<Person> persons = Collections.emptyList();

    public static RadioButton lastCheckedRB = null;
    public static double weight = 0.0;
    public static boolean male = true;

    Context mContext;
    private PersonDao dao;
    public RecyclerViewAdapterListChoose(Context context){
        mContext =context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_simple, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterListChoose.ViewHolder holder, final int position) {
        dao = Room.getDatabase(mContext).person_dao();


        RadioButton textName = holder.itemView.findViewById(R.id.textName1);
        textName.setText(persons.get(position).getName());

        textName.setOnClickListener(v -> {

            RadioButton current = (RadioButton) v;
            if(lastCheckedRB != null){
                lastCheckedRB.setChecked(false);
            }
            lastCheckedRB = current;

            saveNewState(persons.get(position).getWeight(), persons.get(position).isMale());

        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void saveNewState(double weight, boolean male){
        this.weight = weight;
        this.male = male;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout parentLayout;
        RadioButton checkedTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayoutList);
            checkedTextView = itemView.findViewById(R.id.textName1);
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
