package com.vitalgroundz.fortuneteller;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;
import java.util.Random;

public class FortuneReader implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Context context;
    private String response = "";

    public FortuneReader(Context context){
        this.context = context;
    }

    public void startFortune(String question){

        tts = new TextToSpeech(context, this);
        tts.setLanguage(Locale.US);
        tts.setPitch((float) 2);

        //String questionModify = question.toLowerCase();
        if(question.contains("who") ||
                question.contains("what")||
                question.contains("when")||
                question.contains("where")||
                question.contains("how")||
                question.contains("why")||
                question.contains("will")){

            response =  getNiceResponse();
        }else{
            response = getNastyResponse();
        }
    }

    public String getNiceResponse(){
        Random random = new Random();

        String answer;

        int number = random.nextInt(4)+1;

        switch (number){
            case 0:
                answer = "In due time you will know...";
                break;
            case 1:
                answer = "I can see something";
                break;
            case 2:
                answer = "random 1";
                break;
            case 3:
                answer = "random 2";
                break;
            default:
                answer = "I dont know";
                break;
        }
        return answer;

    }

    public String getNastyResponse(){
        Random random = new Random();

        String answer;

        switch (random.nextInt(4)+1){
            case 0:
                answer = "NOOOOOOOO!";
                break;
            case 1:
                answer = "I hate you...";
                break;
            case 2:
                answer = "Ask me a real question, dummy";
                break;
            case 3:
                answer = "Go home and never come back";
                break;
            default:
                answer = "Be gone from me";
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