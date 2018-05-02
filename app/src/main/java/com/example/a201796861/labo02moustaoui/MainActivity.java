package com.example.a201796861.labo02moustaoui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button insererBtn, listerBtn, chercherBtn, effacerBtn, modifierBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insererBtn = findViewById(R.id.btnInsererData);
        listerBtn = findViewById(R.id.btnListerData);
        chercherBtn = findViewById(R.id.btnChercher);
        effacerBtn = findViewById(R.id.btnEffacer);
        modifierBtn = findViewById(R.id.btnModifier);

        insererBtn.setOnClickListener(this);
        listerBtn.setOnClickListener(this);
        chercherBtn.setOnClickListener(this);
        effacerBtn.setOnClickListener(this);
        modifierBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){

            case R.id.btnInsererData:
                Toast.makeText(MainActivity.this,
                        "btnInsererData", Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this, InsererActivity.class);
                startActivity(intent);
                break;
            case R.id.btnListerData:
                Toast.makeText(MainActivity.this,
                        "btnListerData", Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this, ListerActivity.class);
                startActivity(intent);
                break;

            case R.id.btnChercher:
                Toast.makeText(MainActivity.this,
                        "btnChercher", Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this, ChercherActivity.class);
                startActivity(intent);
                break;

            case R.id.btnEffacer:
                Toast.makeText(MainActivity.this,
                        "btnEffacer", Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this, EffacerActivity.class);
                startActivity(intent);
                break;

            case R.id.btnModifier:
                Toast.makeText(MainActivity.this,
                        "btnModifier", Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this, ModifierActivity.class);
                startActivity(intent);
                break;
        }


    }
}
