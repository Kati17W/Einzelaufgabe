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
    Button button2;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);
        textView2 = (TextView) findViewById(R.id.textView2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button:
                Server server = new Server();
                new Thread(server).start();
                break;
            case R.id.button2:
                ASCII ascii = new ASCII();
                new Thread(ascii).start();
        }
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

                handler.post(()->{
                    textView2.setText(out);
                });

            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }

    }

    public class ASCII extends Thread{
        private String in = editTextNumber2.getText().toString();
        String[] stringArray = in.split("");
        char[] charArray = new char[in.length()];
        String string1 = new String();

        public void run() {
            string1=convert(in);
        }

        public String convert(String in) {
            String string1 = new String();
            for(int i =0;i<stringArray.length;i++) {
                if(i % 2 ==0) {
                    switch (stringArray[i]){
                        case "1":
                            stringArray[i] = "a";
                            break;
                        case "2":
                            stringArray[i] = "b";
                            break;
                        case "3":
                            stringArray[i] = "c";
                            break;
                        case "4":
                            stringArray[i] = "d";
                            break;
                        case "5":
                            stringArray[i] = "e";
                            break;
                        case "6":
                            stringArray[i] = "f";
                            break;
                        case "7":
                            stringArray[i] = "g";
                            break;
                        case "8":
                            stringArray[i] = "h";
                            break;
                        case "9":
                            stringArray[i] = "i";
                            break;
                        case "0":
                            stringArray[i] = "j";
                    }
                }
            }
            return string1 = stringArray.toString();

            /*for(int i = 0; i < this.in.length(); i++){
                charArray[i] = this.in.charAt(i);
            }
            return charArray;*/
        }
    }
}