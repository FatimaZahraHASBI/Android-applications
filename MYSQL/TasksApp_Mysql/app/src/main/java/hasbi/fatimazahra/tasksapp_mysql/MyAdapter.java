package hasbi.fatimazahra.tasksapp_mysql;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    //Initialize variable
    private List<Task> taskList;
    private Activity context;


    RecyclerView recyclerView;
    MyAdapter adapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8083/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JSON_Interface json_interface = retrofit.create(JSON_Interface.class);


    public MyAdapter(Activity context, List<Task> taskList){
        this.context = context;
        this.taskList = taskList;
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
        Task task = taskList.get(position);

        //set text on textview
        holder.idview.setText(task.getName());


        holder.btn_Edit.setOnClickListener(view -> {
            //initialize main data
            Task t = taskList.get(holder.getAdapterPosition());
            //get id
            int taskId = t.getId();
            //get task name
            String taskName = t.getName();

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
            EditText editName = dialog.findViewById(R.id.edit_task);
            Button btn_update = dialog.findViewById(R.id.btn_update);

            //set text on edit text
            editName.setText(taskName);

            btn_update.setOnClickListener(view1 -> {
                //Dismiss dialog
                dialog.dismiss();
                //get Updated text from edit text
                String taskText = editName.getText().toString().trim();
                t.setName(taskName);
                //update text in database
//                updateInDatabase(taskId, t);
//                //notify when data is updated
//                taskList.clear();
//                //get data from database
//                MainActivity.getTasksfromDB();
//                notifyDataSetChanged();
            });
        });
//        holder.btn_Delete.setOnClickListener(view -> {
//            //initialize main data
//            Task t = taskList.get(holder.getAdapterPosition());
//            //dalete user from database
////            database.mainDao().delete(d);
//            //notify when data is deleted
//            int pos = holder.getAdapterPosition();
//            taskList.remove(pos);
//            notifyItemRemoved(pos);
//            notifyItemRangeChanged(pos, taskList.size());
//        });
    }



    @Override
    public int getItemCount() {
        return taskList.size();
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

//    public void updateInDatabase(int id, Task task){
//        Call<Task> call1=json_interface.editTask(id, task);
//        call1.enqueue(new Callback<Task>() {
//            @Override
//            public void onResponse(Call<Task> call, Response<Task> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//                taskList.add(response.body());
//
//            }
//
//            @Override
//            public void onFailure(Call<Task> call, Throwable t) {
//                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
//        //notify when data is changed
//        taskList.clear();
//        //get data from database
//        getTasksfromDB(json_interface);
//    }
//
//    public void getTasksfromDB(JSON_Interface json_interface) {
//        Call<List<Task>> call = json_interface.getTask();
//        call.enqueue(new Callback<List<Task>>() {
//            @Override
//            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//                taskList = response.body();
//                //initialize adapter
//                adapter = new MyAdapter(context, taskList);
//                //set adapter
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Task>> call, Throwable t) {
//                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}

