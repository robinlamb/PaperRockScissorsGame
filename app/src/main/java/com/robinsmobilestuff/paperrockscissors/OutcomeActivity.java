package com.robinsmobilestuff.paperrockscissors;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.media.MediaPlayer;





public class OutcomeActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener{


    private int intPicture;
    private int intMessage;
    private int intFirstSelection;
    private int intSecondSelection;

    private MediaPlayer newMediaPlayer;
    private boolean MediaPlayerIsRunning = false;
    public boolean AudioOn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_layout);
        ImageView gameOver2 = (ImageView) findViewById(R.id.game_over_image2);
        gameOver2.setVisibility(View.GONE);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        readSharedPreferences();


        Intent intent = getIntent();
        intPicture = intent.getExtras().getInt("picture");
        intMessage = intent.getExtras().getInt("message");
        intFirstSelection = intent.getExtras().getInt("firstplayer");
        intSecondSelection = intent.getExtras().getInt("secondplayer");

        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.button_animator);


        ImageView gameOver1 = (ImageView) findViewById(R.id.game_over_image1);

        final TextView tvMessage = (TextView) findViewById(R.id.game_over_label);
        final TextView btnPlayAgain = (TextView) findViewById(R.id.play_again_button);
        TextView tvPlayer1Selection = (TextView) findViewById(R.id.player_one_selected_item);
        TextView tvPlayer2Selection = (TextView) findViewById(R.id.player_two_selected_item);


        switch (intPicture) {
            case GameLogic.PAPER_COVERS_ROCK: {
                gameOver1.setImageResource(R.drawable.paper_covers_rock);

                if (AudioOn) {
                    newMediaPlayer = MediaPlayer.create(this, R.raw.paper);
                    newMediaPlayer.setVolume(0.5f, 0.5f);
                    newMediaPlayer.setLooping(false);
                    newMediaPlayer.start();
                    MediaPlayerIsRunning = true;
                }


            }
            break;
            case GameLogic.SCISSORS_CUT_PAPER: {
                gameOver1.setImageResource(R.drawable.scissors_cut_paper);
               if (AudioOn) {
                   newMediaPlayer = MediaPlayer.create(this, R.raw.scissors);
                   newMediaPlayer.setVolume(0.5f, 0.5f);
                   newMediaPlayer.setLooping(false);
                   newMediaPlayer.start();
                   MediaPlayerIsRunning = true;
               }


            }
            break;
            case GameLogic.ROCK_BREAKS_SCISSORS: {
                gameOver1.setImageResource(R.drawable.rock_breaks_scissors);
                if (AudioOn) {
                    newMediaPlayer = MediaPlayer.create(this, R.raw.rock);
                    newMediaPlayer.setVolume(0.5f, 0.5f);
                    newMediaPlayer.setLooping(false);
                    newMediaPlayer.start();
                    MediaPlayerIsRunning = true;
                }


            }
            break;
            case GameLogic.TWO_PAPERS: {
                gameOver1.setImageResource(R.drawable.paper);
                gameOver2.setVisibility(View.VISIBLE);
                gameOver2.setImageResource(R.drawable.paper);
                if (AudioOn) {
                    newMediaPlayer = MediaPlayer.create(this, R.raw.draw);
                    newMediaPlayer.setVolume(0.5f, 0.5f);
                    newMediaPlayer.setLooping(false);
                    newMediaPlayer.start();
                    MediaPlayerIsRunning = true;
                }
                break;

            }
            case GameLogic.TWO_ROCKS: {
                gameOver1.setImageResource(R.drawable.rock);
                gameOver2.setVisibility(View.VISIBLE);
                gameOver2.setImageResource(R.drawable.rock);

                if (AudioOn) {
                    newMediaPlayer = MediaPlayer.create(this, R.raw.draw);
                    newMediaPlayer.setVolume(0.5f, 0.5f);
                    newMediaPlayer.setLooping(false);
                    newMediaPlayer.start();
                    MediaPlayerIsRunning = true;
                }
                break;
            }

            default: {
                gameOver1.setImageResource(R.drawable.scissors);
                gameOver2.setVisibility(View.VISIBLE);
                gameOver2.setImageResource(R.drawable.scissors);
                if (AudioOn) {
                    newMediaPlayer = MediaPlayer.create(this, R.raw.draw);
                    newMediaPlayer.setVolume(0.5f, 0.5f);
                    newMediaPlayer.setLooping(false);
                    newMediaPlayer.start();
                    MediaPlayerIsRunning = true;
                }
                break;
            }

        }//end switch

        switch (intMessage) {
            case GameLogic.PLAYER_ONE_WINS: {
                if (MainActivity.IsOnePlayerGame)
                    tvMessage.setText(R.string.you_win);
                else tvMessage.setText(R.string.player_one_wins);
            }
            break;

            case GameLogic.PLAYER_TWO_WINS: {
                if (MainActivity.IsOnePlayerGame)
                    tvMessage.setText(R.string.you_lose);
                else tvMessage.setText(R.string.player_two_wins);

            }
            break;

            default:
                tvMessage.setText(R.string.draw);
        }//end switch
        tvMessage.startAnimation(AnimationUtils.loadAnimation(OutcomeActivity.this, R.anim.anim_flashing_text));
        switch (intFirstSelection) {
            case GameLogic.PAPER: {
                tvPlayer1Selection.setText(R.string.player_one_paper);

            }
            break;
            case GameLogic.ROCK: {
                tvPlayer1Selection.setText(R.string.player_one_rock);

            }
            break;
            default: {
                tvPlayer1Selection.setText(R.string.player_one_scissors);

            }
        }//End switch


        switch (intSecondSelection) {
            case GameLogic.PAPER: {
                if (MainActivity.IsOnePlayerGame)
                    tvPlayer2Selection.setText(R.string.computer_paper);
                else
                    tvPlayer2Selection.setText(R.string.player_two_paper);

            }
            break;
            case GameLogic.ROCK: {
                if (MainActivity.IsOnePlayerGame)
                    tvPlayer2Selection.setText(R.string.computer_rock);
                else
                    tvPlayer2Selection.setText(R.string.player_two_rock);

            }
            break;
            default: {
                if (MainActivity.IsOnePlayerGame)
                    tvPlayer2Selection.setText(R.string.computer_scissors);
                else
                    tvPlayer2Selection.setText(R.string.player_two_scissors);

            }
        }//End switch


        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animator.setTarget(btnPlayAgain);
                animator.start();


                Intent intent = new Intent(OutcomeActivity.this, SelectionActivity.class);
                OutcomeActivity.this.startActivity(intent);

                OutcomeActivity.this.finish();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (MediaPlayerIsRunning) {
            newMediaPlayer.stop();
            newMediaPlayer.reset();
            newMediaPlayer.release();
        }
        MediaPlayerIsRunning = false;
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.preference_audio_on_key))) {
            AudioOn = sharedPreferences.getBoolean(key, getResources().getBoolean(R.bool.preference_audio_on));
        }
        if(!AudioOn){
            if(MediaPlayerIsRunning) {
                newMediaPlayer.stop();
                newMediaPlayer.reset();
                newMediaPlayer.release();
                MediaPlayerIsRunning = false;
            }
        }
    }

    private void readSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        AudioOn = sharedPreferences.getBoolean(getString(R.string.preference_audio_on_key),getResources().getBoolean(R.bool.preference_audio_on));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        if(!AudioOn){
            if(MediaPlayerIsRunning) {
                newMediaPlayer.stop();
                newMediaPlayer.reset();
                newMediaPlayer.release();
                MediaPlayerIsRunning = false;
            }
        }


    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}