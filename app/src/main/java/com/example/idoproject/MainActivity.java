package com.example.idoproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2,bt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);


    }

    public void animal(View view) {
        Intent go = new Intent(this,hangman.class);
        go.putExtra("wordesType", "a");
        startActivity(go);
    }

    public void countries(View view) {
        Intent go = new Intent(this,hangman.class);
        go.putExtra("wordesType", "b");
        startActivity(go);
    }

    public void cities(View view) {
        Intent go = new Intent(this,hangman.class);
        go.putExtra("wordesType","c");
        startActivity(go);
    }
}
