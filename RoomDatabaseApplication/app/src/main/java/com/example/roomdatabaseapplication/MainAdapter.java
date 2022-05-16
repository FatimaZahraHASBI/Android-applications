package com.example.roomdatabaseapplication;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //Initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(Activity context, List<MainData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //initialize main data
        MainData data = dataList.get(position);
        //initialize database
        database = RoomDB.getInstance(context);
        //set text on textview
        holder.idview.setText(data.getName());

        holder.btn_Edit.setOnClickListener(view -> {
            //initialize main data
            MainData d = dataList.get(holder.getAdapterPosition());
            //get id
            int userID = d.getId();
            //get name
            String nameText = d.getName();

            //create dialog
            Dialog dialog = new Dialog(context);
            //set content view
            dialog.setContentView(R.layout.dialog_update);
            //initialize width
            int width = WindowManager.LayoutParams.MATCH_PARENT;
            //initialize height
            int height = WindowManager.LayoutParams.WRAP_CONTENT;
            //set layout
            dialog.getWindow().setLayout(width, height);
            //show dialog
            dialog.show();

            //initialize and assign variable
            EditText editName = dialog.findViewById(R.id.edit_name);
            Button btn_update = dialog.findViewById(R.id.btn_update);

            //set text on edit text
            editName.setText(nameText);

            btn_update.setOnClickListener(view1 -> {
                //Dismiss dialog
                dialog.dismiss();
                //get Updated text from edit text
                String uText = editName.getText().toString().trim();
                //update text in database
                database.mainDao().update(userID, uText);
                //notify when data is updated
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                notifyDataSetChanged();

            });
        });
        holder.btn_Delete.setOnClickListener(view -> {
            //initialize main data
            MainData d = dataList.get(holder.getAdapterPosition());
            //dalete user from database
            database.mainDao().delete(d);
            //notify when data is deleted
            int pos = holder.getAdapterPosition();
            dataList.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, dataList.size());
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //initialize variable
        TextView idview;
        ImageView btn_Edit, btn_Delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //assign variables
            idview = itemView.findViewById(R.id.idview);
            btn_Edit = itemView.findViewById(R.id.btn_edit);
            btn_Delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
