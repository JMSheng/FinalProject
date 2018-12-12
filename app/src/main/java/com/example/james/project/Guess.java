package com.example.james.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class Guess extends AppCompatActivity {
    static EditText guess;
    static String guessAsString;
    static TextView feedback;
    static String word;
    static int remainingChance;
    static int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        n = 1;
        word = Enter.theWord;
        remainingChance = Enter.remainingChance;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);

        final Button check = (Button) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess = findViewById(R.id.guess);
                guessAsString = guess.getText().toString();
                feedback = findViewById(R.id.feedback);
                feedback.setText("You have " + remainingChance + " chances!");
                if (guessAsString != null && guessAsString.length() != 0 && remainingChance >= 0 && n  - 1 < word.length()) {
                    Guess.guessTheWord(word);
                }
                if (guessAsString == null || guessAsString.length() == 0) {
                    feedback = findViewById(R.id.feedback);
                    feedback.setText("Please enter a character！");
                }
                if (n - 1 == word.length()) {
                    feedback.setText("Congratulations! The guesser wins!");
                    check.setText("RESTART");
                    check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(v.getContext(),Enter.class);
                            startActivity(i);
                        }
                    });
                }
                if (remainingChance < 0) {
                    feedback.setText("You lose! The word setter wins!");
                    check.setText("RESTART");
                    check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(v.getContext(),Enter.class);
                            startActivity(i);
                        }
                    });
                }
            }
        });
    }
    public static void guessTheWord(String theWord) {
        boolean done = true;
        if (done) {
            done = false;
            if (Guess.guess(theWord, guessAsString, n)) {
                n++;
                done = true;
                if (n - 1 != theWord.length()) {
                    feedback.setText("It's correct! You have " + remainingChance + " chances remaining! It's not finished yet. Keep going!");
                }
            } else {
                remainingChance--;
                done = true;
                if (remainingChance >= 0) {
                    feedback.setText("Seems like that's not the correct one." +
                            "You still have " + remainingChance + " chances remaining! " +
                            "Have another try!");
                } else {
                    feedback.setText("Seems like that's not the correct one. " +
                            "Unfortunately you have no more remaining chances! " +
                            "Be careful!");
                }
            }
        }
    }

    public static boolean guess(String word, String guess, int num) {
        if (guess.equals(word.substring(0, num))) {
            return true;
        }
        return false;
    }
}
