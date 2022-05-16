package com.example.tp2_splashscreen_loginstatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String email;
    String password;
    TextView user_email;
    TextView user_password;
    Button btn_logout;
    Button btnPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
        password = bundle.getString("password");
        user_email = findViewById(R.id.user_email);
        user_password= findViewById(R.id.user_password);
        user_email.setText(email);
        user_password.setText(password);

        btn_logout = findViewById(R.id.btn_logout);
        btnPref = findViewById(R.id.btnPref);

        btn_logout.setOnClickListener(view -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        });

        btnPref.setOnClickListener(view -> {
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        });

    }
}