package com.example.ejemploobserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private EditText resultsEditText;
    private Button launchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Suscrita a los cambios que puedan suceder a través del metodo de registro
        EventBus.getDefault().register(this);

        resultsEditText = (EditText) findViewById(R.id.EditTextMain);
        launchButton = (Button) findViewById(R.id.LaunchButtonMain);

        launchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ChildActivity.class);
                startActivity(intent);
            }
        });
    }
    //Nos suscribimos a los eventos de los cuales nos interesa saber sus cambios
    @Subscribe
    public void onEvent(CustomMessageEvent event){
        //Almacenamos en el registro el evento y además, estamos mostrando los resultados en el resultedittext
        Log.d("Ejemplo", "Evento disparado" + event.getCustomMessage());
        resultsEditText.setText(event.getCustomMessage());
    }
}