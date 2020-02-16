package com.robinsmobilestuff.paperrockscissors;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.ActionBar;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static boolean IsOnePlayerGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.button_animator);



        final TextView btnOnePlayerGame = (TextView) findViewById(R.id.one_player_button);
        final TextView btnTwoPlayerGame = (TextView) findViewById(R.id.two_player_button);

        btnOnePlayerGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.setTarget(btnOnePlayerGame);
                animator.start();
                IsOnePlayerGame = true;
                StartNewGame();
                SelectionActivity.IsFirstTurn = true;
            }
        });

       btnTwoPlayerGame.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               animator.setTarget(btnTwoPlayerGame);
               animator.start();
               IsOnePlayerGame = false;
               StartNewGame();
               SelectionActivity.IsFirstTurn = true;
           }
       });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sound_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id==R.id.sound_menu_sound) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void StartNewGame() {
        Intent intent = new Intent(MainActivity.this, SelectionActivity.class);
        MainActivity.this.startActivity(intent);

    }
}