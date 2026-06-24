package com.cal.pvp.calculator_pvp_api.Models;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String player1; 
    private String player2;
    private boolean finished;
    private boolean hasSpace;
    private int id;
    private boolean startBattle;

    private int moveCount;
    private final ArrayList<List<Integer>> playerMoves;

    public Game( String player1 ) {
        this.player1 = player1;
        this.hasSpace = true;
        this.finished = false;
        this.player2 = "";
        this.startBattle = false;
        this.moveCount = 0;
        this.playerMoves = new ArrayList<>();
    }

    public String getPlayer1() {
        return player1;
    }
    
    public void setPlayer1( String player1 ) {
        this.player1 = player1;
    }
    
    public String getPlayer2() {
        return player2;
    }
    
    public void setPlayer2( String player2 ) {
        this.player2 = player2;
    }
    
    public boolean isFinished() {
        return finished;
    }
    
    public void setFinished( boolean finished ) {
        this.finished = finished;
    }
    
    public boolean HasSpace() {
        return hasSpace;
    }
    
    public void setHasSpace( boolean hasSpace ) {
        this.hasSpace = hasSpace;
    }
    
    public void setStartBattle( boolean startBattle ) {
        this.startBattle = startBattle;
    }
    
    public boolean getStartBattle() {
        return this.startBattle;
    }

    public int getID() {
        return this.id;
    }

    public void setID( int id ) {
        this.id = id;
    }       


    public void increaseMoveCount() {
        this.moveCount += 1;
    }

    public void resetMoveCount() {
        this.moveCount = 0;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public void setMoveCount(int cnt) {
        this.moveCount = cnt;
    }

    public void addMove(List<Integer> moves) {
        this.playerMoves.add(moves);
    }

    public ArrayList<List<Integer>> getMoveList() {
        return this.playerMoves;
    }

    public void resetMoves() {
        this.playerMoves.clear();
    }

}