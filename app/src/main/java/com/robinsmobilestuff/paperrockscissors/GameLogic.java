package com.robinsmobilestuff.paperrockscissors;

/**
 * Created by Robin on 9/29/2017.
 */

public class GameLogic {
    public static final int PAPER = 10;
    public static final int ROCK = 20;
    public static final int SCISSORS = 30;

    public static final int PLAYER_ONE_WINS = 1;
    public static final int PLAYER_TWO_WINS = 2;
    public static final int DRAW = 3;

    public static final int PAPER_COVERS_ROCK = 40;
    public static final int SCISSORS_CUT_PAPER = 50;
    public static final int ROCK_BREAKS_SCISSORS = 60;
    public static final int TWO_PAPERS = 70;
    public static final int TWO_SCISSORS = 80;
    public static final int TWO_ROCKS = 90;


    public int Message;
    public int Picture;

    public GameLogic(int player1selection, int player2selection){


        switch(player1selection) {
            case PAPER:
                switch (player2selection) {
                    case PAPER: {
                        Message = DRAW;
                        Picture = TWO_PAPERS;
                    }
                    break;
                    case ROCK: {
                        Message = PLAYER_ONE_WINS;
                        Picture = PAPER_COVERS_ROCK;
                    }
                    break;

                    default: {
                        Message = PLAYER_TWO_WINS;
                        Picture = SCISSORS_CUT_PAPER;
                    }
                }//end switch
                break;

            case ROCK:
                switch (player2selection) {
                    case PAPER: {
                        Message = PLAYER_TWO_WINS;
                        Picture = PAPER_COVERS_ROCK;
                    }
                    break;


                    case ROCK: {
                        Message = DRAW;
                        Picture = TWO_ROCKS;
                    }
                    break;

                    default: {
                        Message = PLAYER_ONE_WINS;
                        Picture = ROCK_BREAKS_SCISSORS;
                    }

                }//end switch
                break;
            default:
                switch (player2selection) {
                    case PAPER: {
                        Message = PLAYER_ONE_WINS;
                        Picture = SCISSORS_CUT_PAPER;
                    }
                    break;

                    case ROCK: {
                        Message = PLAYER_TWO_WINS;
                        Picture = ROCK_BREAKS_SCISSORS;
                    }
                    break;

                    default: {
                        Message = DRAW;
                        Picture = TWO_SCISSORS;
                    }

                }//end switch
        }//end switch
    }

}
