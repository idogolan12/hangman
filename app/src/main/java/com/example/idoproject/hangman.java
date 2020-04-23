package com.example.idoproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class hangman extends AppCompatActivity implements View.OnClickListener {

    int aa = 0;
    Intent go;
    String av;
    GridLayout BGT;
    LinearLayout LLLet;
    int btnWidth = 100, btnHeight = 100;
    String a = "כיטחזוהדגבאתשרקצפעסנמלץףךןם";
    int G = a.length();
    Button[] bt = new Button[G];
    TextView[] tv;
    ArrayList<String> words = new ArrayList<>();
    int[] IVS = {R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g};
    ImageView IV;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    String st;
    int size;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        BGT = findViewById(R.id.BGT);
        IV = findViewById(R.id.iv);
        go = new Intent(this,hangman.class);
        BGT.setColumnCount(11);
        Build_board2();
        newGame();


    }

    private void newGame() {
        counter = 0;
        LLLet = findViewById(R.id.LLLet);
        is = getResources().openRawResource(R.raw.animal);
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        LLLet.removeAllViews();
        while (true) {
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            words.add(st);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        size = words.size();
        int num = (int) ((size * Math.random()));
        tv = new TextView[words.get(num).length()];
        Build_board1(words.get(num).length() , words.get(num));
        av = words.get(num);
    }


    private void Build_board1(int NumLet , String st1) {
        //LLLet.clear...
        for (int i = 0; i < NumLet; i++) {
            tv[i] = new TextView(LLLet.getContext());
            LLLet.addView(tv[i], btnWidth, btnHeight);
            tv[i].setText("____");
            if (st1.charAt(i) == ' ') {
                tv[i].setVisibility(View.INVISIBLE);
                counter=1;
            }
        }
    }

    private void Build_board2() {
        //BGT.clear...
        for (int i = 0; i < bt.length; i++) {
            bt[i] = new Button(BGT.getContext());
            BGT.addView(bt[i], btnWidth, btnHeight);
            String ab = a.charAt(i) + "";
            bt[i].setText(ab);
            bt[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        String a = ((Button) v).getText().toString();
        if (av.indexOf(((Button) v).getText().toString()) != -1)
        {
            for (int i = 0; i < av.length(); i++)
            {
                if (((Button) v).getText().equals(av.charAt(i) + ""))
                {
                    tv[av.length() - i - 1].setText(((Button) v).getText());
                    counter++;
                    if (counter == av.length())
                    {
                        AlertDialog.Builder ads = new AlertDialog.Builder(this);
                    ads.setTitle("כל הכבוד!                                              ");
                    ads.setMessage( "הצלחת לנחש את המילה" +" " +"*"+av+"*"+ "\n" +"מה ברצונך לעשות?" );
                    ads.setPositiveButton("להמשיך", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            newGame();
                        }
                    });
                    ads.setNegativeButton("לצאת", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(hangman.this, "aa", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog ad = ads.create();
                    ad.show();
                }


                }


            }
        }
        else
        {
            if(aa < 5)
            {
                IV.setImageResource(IVS[aa]);
                aa++;
                Toast.makeText(this, "dosen't exist", Toast.LENGTH_SHORT).show();
            }
            else
            {
                IV.setImageResource(IVS[aa]);
                finishGame();
            }





        }
    }

    private void finishGame()
    {
        Toast.makeText(this, "finish game", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle( "לא הצלחת לנחש את המילה והפסדת:(      "  );
        adb.setMessage("המילה הייתה" + " " + "*" + av + "*" + " " + "מה תרצה לעשות?");
        adb.setPositiveButton("לשחק שוב באותו נושא", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(go);
            }
        });
        adb.setNeutralButton("משחקי איש תלוי בנושאים אחרים", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.setNegativeButton("לצאת", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(hangman.this, "aa", Toast.LENGTH_SHORT).show();
            }
        });


        AlertDialog ad = adb.create();
        ad.show();
    }
}
