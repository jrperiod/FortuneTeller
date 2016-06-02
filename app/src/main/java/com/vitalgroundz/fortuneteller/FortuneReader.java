package com.vitalgroundz.fortuneteller;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;
import java.util.Random;

/**
 * Created by bryan on 10/11/15.
 */
public class FortuneReader implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private Context context;
    private String response = "";

    public FortuneReader(Context context) {
        this.context = context;
    }

    public void readFortune(String question) {

        tts = new TextToSpeech(context, this);
        tts.setLanguage(Locale.US);
        tts.setPitch((float) 2);

        String questionEdit = question.toLowerCase();
        if(questionEdit.contains("who") ||
                questionEdit.contains("what") ||
                questionEdit.contains("where")||
                questionEdit.contains("why") ||
                questionEdit.contains("how") ||
                questionEdit.contains("when") ||
                questionEdit.contains("will")||
                questionEdit.contains("was")) {

            response = getNiceAnswer();
        } else {
            response = getNastyAnswer();
        }
    }

    public String getNiceAnswer() {
        String answer = "outlook unclear";
        Random randomNumber = new Random();

        int number = randomNumber.nextInt(4) + 1;

        switch(number) {
            case 1:
                answer = "Most definitely";
                break;
            case 2:
                answer = "Good chance";
                break;
            case 3:
                answer = "Anything is possible if you drink enough";
                break;
            case 4:
                answer = "Of course";
                break;
        }

        return answer;
    }

    public String getNastyAnswer() {
        String answer = "outlook unclear";
        Random randomNumber = new Random();

        int number = randomNumber.nextInt(4) + 1;

        switch(number) {
            case 1:
                answer = "Stupidity is a virtue, dumbass";
                break;
            case 2:
                answer = "Fuck you, Fuck who you are, fuck who you were";
                break;
            case 3:
                answer = "I'm going to pop a cap in your ass";
                break;
            case 4:
                answer = "Why don't you ask me a real question, dick-head";
                break;
        }

        return answer;
    }



    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            Log.d("flow", "success");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tts.speak(response, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                tts.speak(response, TextToSpeech.QUEUE_ADD, null);
            }

        }
    }
}