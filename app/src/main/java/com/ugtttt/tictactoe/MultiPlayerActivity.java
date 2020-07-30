package com.ugtttt.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MultiPlayerActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;


    int gameState;
    private Integer i;
    TextView tvPlayer1, tvPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.intrestial_add));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(MultiPlayerActivity.this, OnlineGameActivity.class);
                startActivity(intent);
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });


        tvPlayer1 = (TextView) findViewById(R.id.tvPlayer1);
        tvPlayer2 = (TextView) findViewById(R.id.tvPlayer2);



        gameState = 1; //1-can play , 2- GameOver , 3- draw

    }

    public void GameBoardClick(View view){
        ImageView selectedImage = (ImageView) view;

        int selectedBlock = 0;
        switch (selectedImage.getId()){
            case R.id.iv_11: selectedBlock = 1; break;
            case R.id.iv_12: selectedBlock = 2; break;
            case R.id.iv_13: selectedBlock = 3; break;

            case R.id.iv_21: selectedBlock = 4; break;
            case R.id.iv_22: selectedBlock = 5; break;
            case R.id.iv_23: selectedBlock = 6; break;

            case R.id.iv_31: selectedBlock = 7; break;
            case R.id.iv_32: selectedBlock = 8; break;
            case R.id.iv_33: selectedBlock = 9; break;


        }
        PlayGame (selectedBlock,selectedImage);
    }
    int activePlayer =1;
    ArrayList<Integer> player1 = new ArrayList<>();
    ArrayList<Integer> player2 = new ArrayList<>();


    private void PlayGame(int selectedBlock, ImageView selectedImage) {
        if (gameState == 1){
            if (activePlayer == 1){
                selectedImage.setImageResource(R.drawable.ttt_x);
                player1.add(selectedBlock);
                activePlayer =2;


            }else if (activePlayer == 2){
                selectedImage.setImageResource(R.drawable.ttt_o);
                player2.add(selectedBlock);
                activePlayer =1;

            }
            selectedImage.setEnabled(false);
            CheckWinner();
        }
    }


    private void showAlert(String Title) {
        AlertDialog.Builder b = new AlertDialog.Builder(this, R.style.TransparentDialog);
        b.setTitle(Title)
                .setMessage("Start a New Game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Resetgame();
                    }
                })
                .setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
    public void Resetgame(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            gameState = 1;
            activePlayer = 1;
            player1.clear();
            player2.clear();

            ImageView iv;
            iv = (ImageView) findViewById(R.id.iv_11);
            iv.setImageResource(0);
            iv.setEnabled(true);
            iv = (ImageView) findViewById(R.id.iv_12);
            iv.setImageResource(0);
            iv.setEnabled(true);
            iv = (ImageView) findViewById(R.id.iv_13);
            iv.setImageResource(0);
            iv.setEnabled(true);

            iv = (ImageView) findViewById(R.id.iv_21);
            iv.setImageResource(0);
            iv.setEnabled(true);
            iv = (ImageView) findViewById(R.id.iv_22);
            iv.setImageResource(0);
            iv.setEnabled(true);
            iv = (ImageView) findViewById(R.id.iv_23);
            iv.setImageResource(0);
            iv.setEnabled(true);

            iv = (ImageView) findViewById(R.id.iv_31);
            iv.setImageResource(0);
            iv.setEnabled(true);
            iv = (ImageView) findViewById(R.id.iv_32);
            iv.setImageResource(0);
            iv.setEnabled(true);
            iv = (ImageView) findViewById(R.id.iv_33);
            iv.setImageResource(0);
            iv.setEnabled(true);
        }



    }

    private void CheckWinner() {
        int winner = 0;
        /***********Checking winner player1 *************/

        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){ winner = 1;}
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){ winner = 1;}
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){ winner = 1;}

        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){ winner = 1;}
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){ winner = 1;}
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){ winner = 1;}

        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){ winner = 1;}
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){ winner = 1;}


        /***********Checking winner player2 *************/

        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){ winner = 2;}
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){ winner = 2;}
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){ winner = 2;}

        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){ winner = 2;}
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){ winner = 2;}
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){ winner = 2;}

        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){ winner = 2;}
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){ winner = 2;}


        if (winner != 0 && gameState == 1){
            if (winner == 1){
                showAlert("Player1 won th Game ");

            }else if (winner == 2){
                showAlert("Player 2 is winner");
            }

            gameState = 2; //GameOver
        }
    }

    public void BacktoMainmenu(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
            finish();
        }
    }
}
