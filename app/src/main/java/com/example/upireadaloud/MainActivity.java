package com.example.upireadaloud;



import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MessageListenerInterface {
    // creating variables on below line for text view.
    private TextView msgTV;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing variables on below line.
        msgTV = findViewById(R.id.idTVMessage);
        // adding bind listener for message receiver on below line.
        MessageBroadcastReceiver.bindListener(this);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }


    @Override
    public void messageReceived(String message) {
        // setting message in our text view on below line.
        msgTV.setText(message);
        if (message.startsWith("An amount")) {
            String[] messagecontent= message.split(" ");
            String speech= "INR "+messagecontent[4]+" received";
            t1.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
        else {
            System.out.println("Good evening.");
        }
        }




    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}