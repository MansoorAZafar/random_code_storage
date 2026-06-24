package com.cal.pvp.calculator_pvp_api.Models;

import java.util.LinkedList;

public class Room {

    private static volatile Room instance;
    private static LinkedList<Game> rooms;
    private static int size;
    private int gameID = Integer.MIN_VALUE;
    
    private Room() {
        rooms = new LinkedList<>();
        size = 0;
    }
    
    public static Room getInstance() {
        Room helper = instance;
        if( helper == null ) {
            synchronized( Room.class ) {
                helper = instance;
                if( helper == null ) {
                    instance = helper = new Room();
                }
            }
        }
        return helper;
    }

    public boolean addRoom(Game game) {
        if(instance != null) 
            return rooms.add(game);
        return false;
    }

    public Game addPlayer( String username ) {
        if( size == 0 ) {
            rooms.add(new Game(username));
            size += 1;
        } else {
            Game latestRoom = rooms.getLast();
            if(latestRoom.HasSpace() == true) {
                // Add a player to an already existing room
                latestRoom.setPlayer2(username);
                latestRoom.setHasSpace(false);
                
                //Tell the thread to wake the f back up
                synchronized(latestRoom) {
                    latestRoom.notify();
                }

            } else {
                
                this.gameID += 1;
                if(this.gameID == Integer.MAX_VALUE) 
                    this.gameID = Integer.MIN_VALUE;

                Game game = new Game(username);
                size += 1;
                game.setID(gameID);
                rooms.add(game);
            }
        }
        


        return rooms.getLast();
    }

    public Game GetGameById(int id) {

        Game game = rooms.stream()
                    .filter(n -> n.getID() == id)
                    .findFirst()
                    .orElse(null);
    
        game.increaseMoveCount();
        if(game.getMoveCount() >= 2) {
            // If both players are in the game, continue it
            synchronized(game) {
                game.notify();
            }
        }
        return game;
    }

    public void endGame(int id) {
        rooms.remove(this.GetGameById(id));
    }
}
