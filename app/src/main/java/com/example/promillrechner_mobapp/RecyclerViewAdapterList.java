package com.example.promillrechner_mobapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

        TextView textWeight = holder.itemView.findViewById(R.id.textWeight);
        textWeight.setText(persons.get(position).getWeight() + " kg");

        TextView textSize = holder.itemView.findViewById(R.id.textHeight);
        textSize.setText(persons.get(position).getSize() + " cm");


    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textWeight;
        RelativeLayout parentLayout;


        public ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textWeight = itemView.findViewById(R.id.textWeight);
            parentLayout = itemView.findViewById(R.id.parentLayoutListDatabase);
            delete = itemView.findViewById(R.id.item_delete);

            itemView.setOnClickListener(view ->
                    listener.onItemClick(persons.get(getAdapterPosition())));

            delete.setOnClickListener(v -> {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                dialogBuilder.setMessage("Willst du "+textName.getText().toString()+" wirklich löschen?")
                            .setPositiveButton("Ja", (dialog, which) -> listener.onDeleteClick(persons.get(getAdapterPosition())))
                            .setNegativeButton("Abbrechen", (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
            });

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
