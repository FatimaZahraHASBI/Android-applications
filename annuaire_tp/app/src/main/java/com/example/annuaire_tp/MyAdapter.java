package com.example.annuaire_tp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactHolder> {

    private List<Contact> contacts = new ArrayList<>();

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder contactHolder, int position) {

        Contact currContact = contacts.get(position);
        contactHolder.name.setText(currContact.getName());
        contactHolder.job.setText(currContact.getJob());
        contactHolder.email.setText(currContact.getEmail());
        contactHolder.phone.setText(currContact.getPhone());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    class ContactHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView job;
        TextView email;
        TextView phone;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            job = itemView.findViewById(R.id.job);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }

//    //Initialize variable
//    private List<Contact> contacts = new ArrayList<>();
//    private Activity context;
//    private RoomDB database;
//
//    public MyAdapter(Activity context, List<Contact> dataList){
//        this.context = context;
//        this.contacts = dataList;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        //initialize view
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        //initialize main data
//        Contact data = contacts.get(position);
//        //initialize database
//        database = RoomDB.getInstance(context);
//        //set text on textview
//        holder.name.setText(data.getName());
//        holder.job.setText(data.getJob());
//        holder.email.setText(data.getEmail());
//        holder.phone.setText(data.getPhone());
////        holder.btn_Edit.setOnClickListener(view -> {
////            //initialize main data
////            Contact d = contacts.get(holder.getAdapterPosition());
////            //get id
////            int userID = d.getId();
////            //get name
////            String nameText = d.getName();
////            String jobText = d.getJob();
////            String emailText = d.getEmail();
////            String phoneText = d.getPhone();
////            String photoText = d.getPhoto();
//
////            //initialize and assign variable
////            EditText editName = dialog.findViewById(R.id.edit_name);
////            Button btn_update = dialog.findViewById(R.id.btn_update);
////
////            //set text on edit text
////            editName.setText(nameText);
//
////            btn_update.setOnClickListener(view1 -> {
////                //Dismiss dialog
////                dialog.dismiss();
////                //get Updated text from edit text
////                String uText = editName.getText().toString().trim();
////                //update text in database
////                database.mainDao().update(userID, uText);
////                //notify when data is updated
////                dataList.clear();
////                dataList.addAll(database.mainDao().getAll());
////                notifyDataSetChanged();
////
////            });
////        });
////        holder.btn_Delete.setOnClickListener(view -> {
////            //initialize main data
////            Contact d = dataList.get(holder.getAdapterPosition());
////            //dalete user from database
////            database.contactDao().delete(d);
////            //notify when data is deleted
////            int pos = holder.getAdapterPosition();
////            dataList.remove(pos);
////            notifyItemRemoved(pos);
////            notifyItemRangeChanged(pos, dataList.size());
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return contacts.size();
//    }
//
//
//    public void setContacts(List<Contact> contacts){
//        this.contacts=contacts;
//        notifyDataSetChanged();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        //initialize variable
//        CircleImageView photo;
//        TextView name;
//        TextView job;
//        TextView email;
//        TextView phone;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            //assign variables
//            photo = itemView.findViewById(R.id.photo);
//            name = itemView.findViewById(R.id.name);
//            job = itemView.findViewById(R.id.job);
//            email = itemView.findViewById(R.id.email);
//            phone = itemView.findViewById(R.id.phone);
//        }
//    }


}
