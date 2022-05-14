package com.example.previewofigroup;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.net.*;
import java.security.Key;

public class Main_Message extends AppCompatActivity {
public Button send;
public EditText message;
public String your_message;
public Socket ser_conn;
public ScrollView scrool_other_msg;
public TextView tv;
    public int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);
        send = (Button) findViewById(R.id.send);
        message = (EditText) findViewById(R.id.message);
        tv = (TextView) findViewById(R.id.her_message_scrool);
        Intent intent = new Intent(getIntent());
        String name = intent.getStringExtra("name");
        System.out.println(name);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ser_conn==null){
                send.setText("SEND");
                Toast.makeText(Main_Message.this, "Click in the send button for 2 times. :Sana", Toast.LENGTH_SHORT).show();}
                send_ send_obj = new send_(name);
                send_obj.execute();
                your_message = message.getText().toString();
                if(ser_conn!=null){
                    recv recv_obj = new recv();
                    Thread thread_obj = new Thread(recv_obj);
                    thread_obj.start();
                }
                count = count + 1;
            }

        });
    }



    class send_ extends AsyncTask<String,Void,String>{
        public String name;
        public  send_(String name){
            this.name = name;
        }
        @Override
        protected String doInBackground(String... strings) {
            System.out.println("threaddddddddddd2");
            if(count==1){
            try {
                System.out.println("threaddddddddddd1");
                ser_conn = new Socket("192.168.1.110",9999);
                System.out.println(count+"counttttttttttttttttttttttttttt");
                PrintWriter pr = new PrintWriter(ser_conn.getOutputStream());
                pr.println(name);
                pr.flush();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Main_Message.this, your_message, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }}
            else if(count>2){
                PrintWriter pr = null;
                try {
                    pr = new PrintWriter(ser_conn.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pr.println(your_message);
                System.out.println(your_message);
                pr.flush();
            }
            return null;
        }
    }
    class recv implements Runnable{
        @Override
        public void run() {
            while(true){
                // count = count + 1;
                try {
                    InputStreamReader inputStreamReader_obj = new InputStreamReader(ser_conn.getInputStream());
                    BufferedReader bfr = new BufferedReader(inputStreamReader_obj);
                    String message = bfr.readLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.append(message+"\n");
                            System.out.println(message);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Ok");
            }
        }
    }

}