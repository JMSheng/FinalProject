package com.example.james.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Enter extends AppCompatActivity {
    static String theWord;
    static EditText theword;
    static TextView feedback;
    static int remainingChance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);

        Button proceed = (Button) findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theword = findViewById(R.id.word);
                theWord = theword.getText().toString();
                feedback = findViewById(R.id.wordfeedback);
                if (theWord.length() != 0) {
                    if (theWord.length() == 1 || theWord.length() == 2 || theWord.length() == 3 || theWord.length() == 4) {
                        remainingChance = 2;
                    } else if (theWord.length() == 5 || theWord.length() == 6) {
                        remainingChance = 3;
                    } else if (theWord.length() == 7 || theWord.length() == 8) {
                        remainingChance = 4;
                    } else if (theWord.length() == 9 || theWord.length() == 10) {
                        remainingChance = 5;
                    } else if (theWord.length() > 10 && theWord.length() <= 14) {
                        remainingChance = 6;
                    } else {
                        remainingChance = 7;
                    }
                    Intent intent = new Intent(v.getContext(), Guess.class);
                    startActivity(intent);
                } else {
                    feedback.setText("Please enter a word that is at least one character long!!!");
                }
            }
        });
    }
}
