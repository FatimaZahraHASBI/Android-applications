package com.example.listview_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Model> liste=new ArrayList<Model>();

        //Remplir notre liste
        liste.add(new Model("1", "Fatima Zahra", "hasbi@gmail.com", "project manager", "0693833002"));
        liste.add(new Model("1", "Hasnaa", "hasnaa@gmail.com", "developer", "0666998833"));
        liste.add(new Model("1", "Halima", "hamza@gmail.com", "teacher", "0708070807"));
        liste.add(new Model("1", "Islam", "islam@gmail.com", "student", "0609090909"));


        //Apler l'adaptateur
        monAdaptateur myAdapter = new
                monAdaptateur(liste); ListView lv =
                findViewById(R.id.maliste);
        lv.setAdapter(myAdapter);
    }

    class monAdaptateur extends BaseAdapter {
        ArrayList<Model> eltList = new ArrayList<Model>();

        public monAdaptateur(ArrayList<Model> elts){ this.eltList=elts;
        } public int getCount() { return eltList.size(); }

        public Object getItem(int i) { return eltList.get(i).getName();
        } public long getItemId(int i) { return i; }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater linflater = getLayoutInflater();
                View view1 = linflater.inflate(R.layout.item_list,null);
                //Récupérer chaque elt de la vue
                TextView nom = view1.findViewById(R.id.idname);
                TextView email= view1.findViewById(R.id.idemail);
                TextView phone= view1.findViewById(R.id.idphone);
                TextView job= view1.findViewById(R.id.idjob);
                //Attribuer à chaque elt de vue sa propre val
                nom.setText(eltList.get(i).getName());
                phone.setText(eltList.get(i).getPhone());
                email.setText(eltList.get(i).getEmail());
                job.setText(eltList.get(i).getJob());
                return view1;
        }
    }
}