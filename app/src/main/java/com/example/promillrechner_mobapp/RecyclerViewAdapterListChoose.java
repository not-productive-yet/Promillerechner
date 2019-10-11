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

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapterListChoose extends RecyclerView.Adapter<RecyclerViewAdapterListChoose.ViewHolder> implements View.OnClickListener {

    //private static RecyclerViewClickListener itemListener;
    private List<data_person> persons = Collections.emptyList();


    Context mContext;
    private data_person_dao dao;
    RecyclerViewAdapterListChoose(Context context){
        mContext =context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_person_list_item_choose, parent, false);
        RecyclerView.ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterListChoose.ViewHolder holder, final int position) {
        dao = data_person_room_database.getDatabase(mContext).person_dao();

        TextView textName = holder.itemView.findViewById(R.id.textName);
        textName.setText(persons.get(position).getName());
        TextView textWeight = holder.itemView.findViewById(R.id.textWeight);
        textWeight.setText(Double.toString(persons.get(position).getWeight()));
        TextView textSize = holder.itemView.findViewById(R.id.textSize);
        textSize.setText(Integer.toString(persons.get(position).getSize()));

        holder.parentLayout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), Calc_choose_alcohol.class);
        intent.putExtra("Weight",persons.get(0).getWeight());
        v.getContext().startActivity(intent);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textWeight;
        TextView textSize;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textWeight = itemView.findViewById(R.id.textWeight);
            textSize = itemView.findViewById(R.id.textSize);
            parentLayout = itemView.findViewById(R.id.parentLayoutListChoose);
        }
    }
    public void setPersons(List<data_person> persons){
        this.persons = persons;
        notifyDataSetChanged();
    }

    class DeleteAlcoholTask extends AsyncTask<data_person, Void, List<data_person>> {

        @Override
        protected List<data_person> doInBackground(data_person... data_persons) {
                dao.delete(data_persons[0]);
                return dao.getAll();
            }
        @Override
        protected void onPostExecute(List<data_person> data_persons){
            super.onPostExecute(data_persons);
            setPersons(data_persons);
        }
        }

    }
