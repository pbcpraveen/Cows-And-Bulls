package com.example.cowsandbulls;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    EditText entered_word, g, g_next;
    String word, guess;
    TextView invalid, selected, br, cr, b, c, invalid_enter;
    Button submit, enter, giveup;
    int guess_count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        entered_word = (EditText) findViewById(R.id.word);
        enter = (Button) findViewById(R.id.enter);
        submit = (Button) findViewById(R.id.submit);
        giveup = (Button) findViewById(R.id.giveup);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2();
            }
        });

        giveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveUp();
            }
        });

    }

    public boolean isValid(String text) {
        /*Write the dictionary logic here*/
        if(text.length() != 4)
            return  false;
        return true;
    }

    public void player1() {
        word = entered_word.getText().toString();

        if(isValid(word)) {
            hideKeyboard(this);
            word = word.toLowerCase();
            selected = (TextView) findViewById(R.id.selected);
            b = (TextView) findViewById(R.id.bulls);
            c = (TextView) findViewById(R.id.cows);
            g = (EditText) findViewById(R.id.guess1);

            entered_word.setVisibility(View.INVISIBLE);
            enter.setVisibility(View.INVISIBLE);
            selected.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
            c.setVisibility(View.VISIBLE);
            g.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
            giveup.setVisibility(View.VISIBLE);
            invalid_enter = (TextView) findViewById(R.id.invalid_enter);
            invalid_enter.setVisibility(View.INVISIBLE);

        }
        else {
            invalid_enter = (TextView) findViewById(R.id.invalid_enter);
            invalid_enter.setVisibility(View.VISIBLE);
        }
    }

    public void findBullsAndCows(String guess) {
        /*
        Write the main logic here
        */
        guess = guess.toLowerCase();
        ArrayList<Integer> traversed = new ArrayList<Integer>();
        int i, j, bulls = 0, cows = 0;

        for(i = 0; i < 4; i++) {
            for(j = 0; j < 4; j++) {
                System.out.println("GUESS: " + guess.charAt(i) + i + " WORD: " + word.charAt(j) + j);
                if(guess.charAt(i) == word.charAt(j)) {
                    System.out.println("TRAVERSED IS " + traversed.indexOf(j));
                    if(traversed.indexOf(j) < 0) {
                        traversed.add(j);
                        if(i == j)
                            bulls++;
                        else
                            cows++;
                        if(i == 3)
                            break;
                        else
                            i++;
                        j = -1;
                    }
                }
            }
        }

        if(guess_count == 1) {br = (TextView) findViewById(R.id.b1); cr = (TextView) findViewById(R.id.c1); g_next = (EditText) findViewById(R.id.guess2);}
        else if(guess_count == 2) {br = (TextView) findViewById(R.id.b2); cr = (TextView) findViewById(R.id.c2); g_next = (EditText) findViewById(R.id.guess3);}
        else if(guess_count == 3) {br = (TextView) findViewById(R.id.b3); cr = (TextView) findViewById(R.id.c3); g_next = (EditText) findViewById(R.id.guess4);}
        else if(guess_count == 4) {br = (TextView) findViewById(R.id.b4); cr = (TextView) findViewById(R.id.c4); g_next = (EditText) findViewById(R.id.guess5);}
        else if(guess_count == 5) {br = (TextView) findViewById(R.id.b5); cr = (TextView) findViewById(R.id.c5); g_next = (EditText) findViewById(R.id.guess6);}
        else if(guess_count == 6) {br = (TextView) findViewById(R.id.b6); cr = (TextView) findViewById(R.id.c6); g_next = (EditText) findViewById(R.id.guess7);}
        else if(guess_count == 7) {br = (TextView) findViewById(R.id.b7); cr = (TextView) findViewById(R.id.c7); g_next = (EditText) findViewById(R.id.guess8);}
        else if(guess_count == 8) {br = (TextView) findViewById(R.id.b8); cr = (TextView) findViewById(R.id.c8); g_next = (EditText) findViewById(R.id.guess9);}
        else if(guess_count == 9) {br = (TextView) findViewById(R.id.b9); cr = (TextView) findViewById(R.id.c9); g_next = (EditText) findViewById(R.id.guess10);}
        else if(guess_count == 10) {br = (TextView) findViewById(R.id.b10); cr = (TextView) findViewById(R.id.c10);}


        br.setText(Integer.toString(bulls));
        cr.setText(Integer.toString(cows));
        g.setInputType(InputType.TYPE_NULL);
        br.setVisibility(View.VISIBLE);
        cr.setVisibility(View.VISIBLE);

        if(bulls != 4) guess_count++;

        if(bulls == 4) {
            String guesses;
            if (guess_count == 1)
                guesses = "1 GUESS!";
            else
                guesses = Integer.toString(guess_count) + " GUESSES!";
            Toast.makeText(this, "WORD FOUND IN " + guesses, Toast.LENGTH_LONG).show();
            g.setInputType(InputType.TYPE_NULL);
            submit.setVisibility(View.INVISIBLE);
            giveup.setVisibility(View.INVISIBLE);
        }
        else if(guess_count > 10)
            Toast.makeText(this, "GAME OVER! WORD IS " + word, Toast.LENGTH_LONG).show();
        else {
            g_next.setVisibility(View.VISIBLE);
            g.setFocusable(false);
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) submit.getLayoutParams();
            rlp.addRule(RelativeLayout.BELOW, g.getId());
            rlp.addRule(RelativeLayout.LEFT_OF, g_next.getId());
            rlp.setMargins(0, 5, 90, 0);
        }
    }

    public void player2() {
        if(guess_count == 1) g = (EditText) findViewById(R.id.guess1);
        else if(guess_count == 2) g = (EditText) findViewById(R.id.guess2);
        else if(guess_count == 3) g = (EditText) findViewById(R.id.guess3);
        else if(guess_count == 4) g = (EditText) findViewById(R.id.guess4);
        else if(guess_count == 5) g = (EditText) findViewById(R.id.guess5);
        else if(guess_count == 6) g = (EditText) findViewById(R.id.guess6);
        else if(guess_count == 7) g = (EditText) findViewById(R.id.guess7);
        else if(guess_count == 8) g = (EditText) findViewById(R.id.guess8);
        else if(guess_count == 9) g = (EditText) findViewById(R.id.guess9);
        else if(guess_count == 10) g = (EditText) findViewById(R.id.guess10);

        guess = g.getText().toString();

        if(isValid(guess)) {
            hideKeyboard(this);
            invalid = (TextView) findViewById(R.id.invalid);
            invalid.setVisibility(View.INVISIBLE);
            findBullsAndCows(guess);
        }
        else {
            invalid = (TextView) findViewById(R.id.invalid);
            invalid.setVisibility(View.VISIBLE);
        }
    }

    public void giveUp() {
        Toast.makeText(getApplicationContext(), "GAVE UP! WORD IS " + word, Toast.LENGTH_LONG).show();
        g.setInputType(InputType.TYPE_NULL);
        submit.setVisibility(View.INVISIBLE);
        hideKeyboard(this);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
