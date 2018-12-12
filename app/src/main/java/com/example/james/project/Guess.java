package com.example.james.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Guess extends AppCompatActivity {
    static EditText guess;
    static String guessAsString;
    static TextView feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);



        Button check = (Button) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guessAsString != null && guessAsString.length() == 0) {
                    guess = findViewById(R.id.guess);
                    guessAsString = guess.getText().toString();
                    feedback = findViewById(R.id.feedback);
                } else{
                    feedback = findViewById(R.id.feedback);
                    feedback.setText("Please enter a character！");
            }
                Guess.guessTheWord(Enter.theWord);
            }
        });
    }
    public static void guessTheWord(String theWord) {
        int remainingChance;
        int n = 0;
        boolean done = true;
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
        feedback.setText("You have" + remainingChance + "chances remaining!");
        while (remainingChance >= 0 && n < theWord.length()) {
            if (done) {
                done = false;
                if (Guess.guess(theWord, guessAsString, n)) {
                    n++;
                    done = true;
                    feedback.setText("It's correct! You have" + remainingChance + "chances remaining! Keep Going!" );
                    if (n != theWord.length()) {
                        feedback.setText("It's not finished yet. Keep going!");
                    }
                } else {
                    remainingChance--;
                    done = true;
                    if (remainingChance >= 0) {
                        feedback.setText("Seems like that's not the correct one. You still have " + remainingChance + " chances remaining! Have another try!");
                    } else {
                        feedback.setText("Seems like that's not the correct one. Unfortunately you have no more remaining chances! Be careful!");
                    }
                }
            }
        }
        if (n == theWord.length()) {
            feedback.setText("Congratulations! The guesser wins!");
        }
        if (remainingChance < 0) {
            feedback.setText("You lose! The word setter wins!");
        }
    }

    public static boolean guess(String word, String guess, int num) {
        if (guess.equals(word.substring(num, num + 1))) {
            return true;
        }
        return false;
    }
}
