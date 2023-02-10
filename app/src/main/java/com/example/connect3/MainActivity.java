package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    byte activePlayer = 0;
    //0: Yellow, 1: Red, 2: Empty
    byte[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    byte[][] winningPositions = {{0, 1, 2}, {0, 4, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {3, 4, 5}, {6, 7, 8}};
    boolean activeGame = true;
    TextView winnerResult;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        winnerResult = (TextView) findViewById(R.id.winnertxt);
        playAgain = (Button) findViewById(R.id.playAgainBtn);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && activeGame) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }
            for (byte[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner;
                    activeGame = false;
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    winnerResult.setText(winner + " Win");
                    winnerResult.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
        }
    }

    public void playAgain(View view) {
        winnerResult.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        activeGame = true;
        activePlayer = 0;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageDrawable(null);
        }

    }
}