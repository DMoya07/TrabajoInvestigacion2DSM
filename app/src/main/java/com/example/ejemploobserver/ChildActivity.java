package com.example.ejemploobserver;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ejemploobserver.databinding.ActivityChildBinding;

import org.greenrobot.eventbus.EventBus;

public class ChildActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityChildBinding binding;

    private Button triggerEventButton;
    private EditText messageEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChildBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_child);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        messageEditText = (EditText) findViewById(R.id.EditTextChild);
        triggerEventButton = (Button)findViewById(R.id.buttonChild);

        triggerEventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                Obtenemos el valor almacenado en el input
                Creamos el evento y estamos seteando un mensaje
                personalizado, en este caso lo escrito en el input
                y por último posteamos el evento y finalizamos la actividad
                 */

                String userText = messageEditText.getText().toString();

                CustomMessageEvent event = new CustomMessageEvent();
                event.setCustomMessage(userText);
                EventBus.getDefault().post(event);

                finish();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_child);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}