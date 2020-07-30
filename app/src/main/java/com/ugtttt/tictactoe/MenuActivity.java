package com.ugtttt.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MenuActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    MediaPlayer mysong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


       // mysong = MediaPlayer.create(MenuActivity.this,R.raw.mytune);
       // mysong.start();



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
                Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
                startActivity(intent);
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });



    }



    public void startGame_SinglePlayer(View view){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        }
    }

    public void EndGame(View view) { if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();
    } else {
        Intent a = new Intent(Intent.ACTION_SEND);

        //this is to get the app link in the playstore without launching your app.
        final String appPackageName = getApplicationContext().getPackageName();
        String strAppLink = "";

        try {
            strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
        } catch (android.content.ActivityNotFoundException anfe) {
            strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
        }
        // this is the sharing part
        a.setType("text/link");
        String shareBody = "Hey! Download this app and play game online with your friends on fingertips" +
                "" +
                "\n" + "" + strAppLink;
        String shareSub = "APP NAME/TITLE";
        a.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        a.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(a, "Share Using"));
    }

    }

    public void ShowAboutNote(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);}
    }

    public void StartGameOnline(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
        Intent intent = new Intent(getApplicationContext(), OnlineLoginActivity.class);
        startActivity(intent);
        }
    }

    public void startGame_MultiPlayer(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent intent = new Intent(getApplicationContext(), MultiPlayerActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() { if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();
    } else {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                }).show();
    }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mysong.release();
        finish();
    }

}
