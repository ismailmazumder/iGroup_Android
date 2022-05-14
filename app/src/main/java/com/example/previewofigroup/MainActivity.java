package com.example.previewofigroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
public Button submit;
public EditText your_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.submit);
        your_name = (EditText) findViewById(R.id.your_name);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String your_name_str = your_name.getText().toString();
                Intent message_menu = new Intent(getApplicationContext(),Main_Message.class);
                message_menu.putExtra("name",your_name_str);
                startActivity(message_menu);
                System.out.println("ok");
            }
        });
    }
}