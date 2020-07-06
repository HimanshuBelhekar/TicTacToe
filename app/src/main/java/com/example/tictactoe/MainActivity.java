package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0:circle, 1:cross, 2:empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;
    int roundCount = 0;
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        roundCount++;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setScaleX(0);
            counter.setScaleY(0);
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.circle);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.cross);
            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //Someone Wins!
                    gameActive = false;

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    int winner = gameState[winningPosition[0]];
                    String message;
                    if (winner == 0) {
                        message = "Circle Wins!";
                        winnerTextView.setTextColor(Color. rgb(0,0,200));
                    } else {
                        message = "Cross Wins!";
                        winnerTextView.setTextColor(Color. rgb(200,0,0));
                    }

                    winnerTextView.setText(message);
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }

            if(roundCount==9 && gameActive){
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                String message = "Match Tied";

                winnerTextView.setText(message);
                winnerTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }


            counter.animate().scaleX(0.9f).scaleY(0.9f).setDuration(400);
        }
    }

    public void playAgain(View view){
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        //GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        GridLayout myGrid = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<myGrid.getChildCount(); i++){
            ImageView counter = (ImageView) myGrid.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
        roundCount = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}