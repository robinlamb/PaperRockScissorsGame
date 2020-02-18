//Robin Lamb
//Paper Rock Scissors

//SelectionActivity class to make a selection of paper, rock, or scissors

package com.robinsmobilestuff.paperrockscissors;

import android.app.Activity;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import android.animation.Animator;
import android.animation.AnimatorInflater;




public class SelectionActivity extends Activity {

    public int intMessage;
    public int intPicture;
    public static boolean IsFirstTurn = true;
    private static int intFirstPlayersChoice;
    private int intSecondPlayersChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_layout_land);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //Find views from XML
        final ImageView imgPaper = (ImageView)findViewById(R.id.iv_paper);
        final ImageView imgRock = (ImageView)findViewById(R.id.iv_rock);
        final ImageView imgScissors = (ImageView)findViewById(R.id.iv_scissors);
        final TextView tvPlayerTurn = (TextView)findViewById(R.id.player_turn);

        //Animator to animate selection
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.selection_animator);


        if ((!MainActivity.IsOnePlayerGame) && (IsFirstTurn)) {
            tvPlayerTurn.setText(R.string.player_one_turn);
            
            //Play text animation from XML file
            tvPlayerTurn.startAnimation(AnimationUtils.loadAnimation(SelectionActivity.this, R.anim.anim_flashing_text));

        }

        else if (!MainActivity.IsOnePlayerGame){
            tvPlayerTurn.setText(R.string.player_two_turn);
            
            //Play text animation from XML file
            tvPlayerTurn.startAnimation(AnimationUtils.loadAnimation(SelectionActivity.this, R.anim.anim_flashing_text));
        }
        else {
            tvPlayerTurn.setText(null);
        }

        imgPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animate the paper image to be larger if it is selected
                animator.setTarget(imgPaper);
                animator.start();



                if (IsFirstTurn){
                    intFirstPlayersChoice = GameLogic.PAPER;
                    if (MainActivity.IsOnePlayerGame){
                        
                        intSecondPlayersChoice = GenerateComputerChoice();
                        
                        DeclareWinner(intFirstPlayersChoice, intSecondPlayersChoice);
                    }
                    if(!MainActivity.IsOnePlayerGame){
                        //If this is a two player game, and the first player's turn give 
                        //the second player a choice
                        IsFirstTurn = false;
                        ChangePlayer(tvPlayerTurn);

                    }
                }
                else {
                    intSecondPlayersChoice = GameLogic.PAPER;
                    DeclareWinner(intFirstPlayersChoice, intSecondPlayersChoice);
                }

            }
        });


        imgRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make rock image animated to become larger when it is selected
                animator.setTarget(imgRock);
                animator.start();

                if (IsFirstTurn){
                    intFirstPlayersChoice = GameLogic.ROCK;
                    if (MainActivity.IsOnePlayerGame){
                        
                        intSecondPlayersChoice = GenerateComputerChoice();
                        DeclareWinner(intFirstPlayersChoice, intSecondPlayersChoice);
                    }

                    if(!MainActivity.IsOnePlayerGame){
                        //If this is a two player game, and the first player's turn give the
                        //second player a choice
                        IsFirstTurn = false;
                        ChangePlayer(tvPlayerTurn);
                    }
                }
                else {
                    //If this is the second player's choice, store the selection as their
                    //choice
                    intSecondPlayersChoice = GameLogic.ROCK;
                    DeclareWinner(intFirstPlayersChoice, intSecondPlayersChoice);
                }

            }
        });

        imgScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Animate scissors picture to get larger if it is selected
                animator.setTarget(imgScissors);
                animator.start();


                if (IsFirstTurn){
                    intFirstPlayersChoice = GameLogic.SCISSORS;
                    if (MainActivity.IsOnePlayerGame){
                        intSecondPlayersChoice = GenerateComputerChoice();
                        DeclareWinner(intFirstPlayersChoice, intSecondPlayersChoice);
                    }

                    if(!MainActivity.IsOnePlayerGame){
                        //If this is a two player game and the first player's choice,
                        //give the second player a turn
                        IsFirstTurn = false;
                        ChangePlayer(tvPlayerTurn);
                    }
                }
                else {
                    //If this is the second players turn, store the selection as their choice
                    intSecondPlayersChoice = GameLogic.SCISSORS;
                    DeclareWinner(intFirstPlayersChoice, intSecondPlayersChoice);
                }


            }
        });

    }

    public int GenerateComputerChoice(){
        Random random = new Random();
        int i = random.nextInt(3);
        if (i == 0){
            return GameLogic.PAPER;
        }
        else if (i == 1)
            return GameLogic.ROCK;
        else
            return GameLogic.SCISSORS;
    }

    public void DeclareWinner(int firstChoice, int secondChoice){
        GameLogic newGame =  new GameLogic(firstChoice, secondChoice);
        intMessage = newGame.Message;
        intPicture = newGame.Picture;
        IsFirstTurn = true;

        Intent intent = new Intent(SelectionActivity.this, OutcomeActivity.class);
        intent.putExtra("message", intMessage);
        intent.putExtra("picture", intPicture);
        intent.putExtra("firstplayer", intFirstPlayersChoice);
        intent.putExtra("secondplayer", intSecondPlayersChoice);
        SelectionActivity.this.startActivity(intent);

        SelectionActivity.this.finish();



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

    //Display text on the screen to show this is the second player's turn
    public void ChangePlayer(final TextView textView){


        //Animate the text with the text anim file in XML
        textView.startAnimation(AnimationUtils.loadAnimation(SelectionActivity.this, R.anim.anim_fade_out));
        textView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText(R.string.player_two_turn);
                textView.startAnimation(AnimationUtils.loadAnimation(SelectionActivity.this, R.anim.anim_flashing_text));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }




}
