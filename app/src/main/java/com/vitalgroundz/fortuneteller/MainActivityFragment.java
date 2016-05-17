package com.vitalgroundz.fortuneteller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.R;

import java.util.List;


/**
 * Created by manny on 5/11/16.
 */
public class MainActivityFragment extends Fragment{

    private static final int SPEECH_REQUEST_CODE = 0;
    Button button;
    FortuneReader fortuneReader;

    public MainActivityFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_list_item, container, false);

        button = (Button) layout.findViewById(R.id.button1);

        String bang= "bang";
        button.setText(bang);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("flow", "button clicked");
                displaySpeechRecognizer();
            }
        });


        return layout;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }


    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        Log.d("flow", "start intent");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPEECH_REQUEST_CODE) {

            if(resultCode == Activity.RESULT_OK) {

                List<String> results = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                String spokenText = results.get(0);

                fortuneReader = new FortuneReader(getActivity());
                fortuneReader.startFortune(spokenText);
            } else {
                Log.d("flow", "failure");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}