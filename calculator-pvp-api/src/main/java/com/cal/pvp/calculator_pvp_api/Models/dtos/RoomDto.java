package com.cal.pvp.calculator_pvp_api.Models.dtos;

import com.cal.pvp.calculator_pvp_api.Models.Game;
import com.cal.pvp.calculator_pvp_api.Models.Room;

public class RoomDto {
    private static final Room room = Room.getInstance();

    public static boolean AddRoom(Game game) {
        return room.addRoom(game);
    }

    public static Game addPlayer(String username) {
        return room.addPlayer(username);
    }

    public static Game getGameByID(int id) {
        return room.GetGameById(id);
    }

    public static void endGame(int id) {
        room.endGame(id);
    }
}
