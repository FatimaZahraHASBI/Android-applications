package com.example.tp2_splashscreen_loginstatic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()" , Toast.LENGTH_SHORT).show(); }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()" , Toast.LENGTH_SHORT).show(); }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause()" , Toast.LENGTH_SHORT).show(); }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onReStart()" , Toast.LENGTH_SHORT).show(); }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop()" , Toast.LENGTH_SHORT).show(); }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy()" , Toast.LENGTH_SHORT).show(); }

    private EditText email;
    private EditText password;
    private Button btnlogin;
    TextView toast_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btn_login);
        toast_msg = findViewById(R.id.toast_msg);

        btnlogin.setOnClickListener(view -> {
            if(email.getText().toString().equals("") || password.getText().toString().equals("")){
                Toast.makeText(this, toast_msg.getText().toString() , Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", email.getText().toString());
                bundle.putString("password", password.getText().toString());
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }
}
