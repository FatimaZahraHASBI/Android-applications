package hasbi.fatimazahra.tasksapp_mysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText task;
    Button btn_add, btn_reset;
    List<Task> taskList = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        task = findViewById(R.id.taskText);
        btn_add = findViewById(R.id.btn_add);
        btn_reset = findViewById(R.id.btn_reset);
        recyclerView = findViewById(R.id.recycler_view);

        //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8083/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSON_Interface json_interface = retrofit.create(JSON_Interface.class);

        getTasksfromDB(json_interface);

        btn_add.setOnClickListener(view -> {
            String taskName = task.getText().toString().trim();
            //check condition
            if (!taskName.equals("")){
                Task t = new Task(taskName);
                //insert to database
                Call<Task> call1=json_interface.addTask(t);
                call1.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        taskList.add(response.body());

                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                task.setText("");
                Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show();
                //notify when data is inserted
                taskList.clear();
                //get data from database
                getTasksfromDB(json_interface);
            }
        });

//        btn_reset.setOnClickListener(view -> {
//            //delete all from database table
////            database.mainDao().reset(taskList);
//            taskList.clear();
//            //add all from database
//            getTasksfromDB(json_interface);
//        });


}
    public void getTasksfromDB(JSON_Interface json_interface) {
        Call<List<Task>> call = json_interface.getTask();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                taskList = response.body();
                //initialize adapter
                adapter = new MyAdapter(MainActivity.this, taskList);
                //set adapter
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}











