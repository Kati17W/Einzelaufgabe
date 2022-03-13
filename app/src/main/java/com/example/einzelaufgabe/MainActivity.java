package com.example.einzelaufgabe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button button;
    EditText editTextNumber2;
    TextView textView2;

    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);
        textView2 = (TextView) findViewById(R.id.textView2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Server server = new Server();
        new Thread(server).start();

    }


    class Server extends Thread {
        private String in = editTextNumber2.getText().toString();
        private String out = "";

        public void run() {

            try {
                Socket s = new Socket("se2-isys.aau.at", 53212);

                DataOutputStream output = new DataOutputStream(s.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));

                if (in != null) {
                    output.writeBytes(in + '\n');
                } else {
                    Log.e("INPUT ERROR", "ERROR");
                }

                out = bufferedReader.readLine();
                s.close();

            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }

        public String ascii(ArrayList<Character> editTextNumber2) {
            char result = editTextNumber2.get(0);

            for (int i = 1; i < editTextNumber2.size(); i++) {
                if (i % 2 == 0) {

                } else {


                }
            }

            return String.valueOf(result);
        }

    }
}