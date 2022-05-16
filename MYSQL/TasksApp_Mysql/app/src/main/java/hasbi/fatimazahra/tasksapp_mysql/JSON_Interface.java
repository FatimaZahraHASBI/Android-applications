package hasbi.fatimazahra.tasksapp_mysql;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSON_Interface {

        @GET("tasks")
        Call<List<Task>> getTask();

        @POST("tasks")
        Call<Task> addTask(@Body Task task);

        @PUT("tasks/{id}")
        Call<Task> editTask(@Path("id") int id, @Body Task task);

        @DELETE("tasks/{id}")
        void deleteTask(@Path("id") int id);

}
