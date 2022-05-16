package com.example.exercice_sql_lite1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText id, name;
    Button add, delete, update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<User> liste=new ArrayList<User>();

        liste.addAll(UserDatabase.getDatabase(getApplicationContext()).userDao().getAll());

        id=findViewById(R.id.id);
        name=findViewById(R.id.name);

        add.setOnClickListener(view -> {
            addData();
        });

        update.setOnClickListener(view -> {
            updateData();
        });

        delete.setOnClickListener(view -> {
            deleteData();
        });

        monAdaptateur myAdapter = new
                monAdaptateur(liste); ListView lv =
                findViewById(R.id.maliste);
        lv.setAdapter(myAdapter);

    }

    private void addData(){
        String id_text = id.getText().toString().trim();
        String name_text = name.getText().toString().trim();

        if (id_text!=null && name_text!=null){
            User userModel= new User();
            userModel.setName(name_text);
            userModel.setName(id_text);
            UserDatabase.getDatabase(getApplicationContext()).userDao().insertAll(userModel);

            Toast.makeText(this, "Data successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData(){

    }

    private void updateData(){

    }

    class monAdaptateur extends BaseAdapter {
        ArrayList<User> eltList = new ArrayList<User>();

        public monAdaptateur(ArrayList<User> elts){ this.eltList=elts;
        } public int getCount() { return eltList.size(); }

        public Object getItem(int i) { return eltList.get(i).getName();
        } public long getItemId(int i) { return i; }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater linflater = getLayoutInflater();
            View view1 = linflater.inflate(R.layout.item_list,null);
            //Récupérer chaque elt de la vue
            TextView id = view1.findViewById(R.id.id);
            TextView name= view1.findViewById(R.id.name);
            //Attribuer à chaque elt de vue sa propre val
            id.setText(eltList.get(i).getId());
            name.setText(eltList.get(i).getName());
            return view1;
        }
    }

}