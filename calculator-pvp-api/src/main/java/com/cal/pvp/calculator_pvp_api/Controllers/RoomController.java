package com.cal.pvp.calculator_pvp_api.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cal.pvp.calculator_pvp_api.Models.Game;
import com.cal.pvp.calculator_pvp_api.Models.Move;
import com.cal.pvp.calculator_pvp_api.Models.User;
import com.cal.pvp.calculator_pvp_api.Models.dtos.RoomDto;



// Make sure this isn't the swagger import
// I legit spent like 4 hrs trying to debug to figure out
// oh its the wrong IMPORT 
// ITS SWAGGER NOT SPRING
// gg
@RestController
public class RoomController {
    
    @PostMapping("/joinRoom")
    public ResponseEntity<Integer> joinRoom(@RequestBody User user) {
        Game gameRef = RoomDto.addPlayer(user.getEmail());
        
        synchronized(gameRef) {
            if(gameRef.HasSpace()) {
                try {
                    // Wait until another player joins
                    gameRef.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    return ResponseEntity.badRequest().body(-1);
                }
            }
        }
        // while(gameRef.HasSpace() == true) {} // wait for other player to join
        
        // Return the Game ID
        return ResponseEntity.ok(gameRef.getID());
    }


    @PostMapping("battle")
    public int battle(@RequestBody Move chosenMove) {       
        final Game gameRef = RoomDto.getGameByID(chosenMove.getGameID());
        final List<Integer> yourMoves = chosenMove.getNumbers();
        gameRef.addMove(yourMoves);

        synchronized(gameRef) {
            if(gameRef.getMoveCount() < 2) {
                try {
                    // Wait for the other play to make their move too
                    gameRef.wait();
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
            }
        }
        final List<Integer> winner_moves = getWinnerMoves(gameRef.getMoveList());
        //Reset the count for the next battle
        gameRef.resetMoveCount();
        
        if(winner_moves.equals(yourMoves)) {
            gameRef.resetMoves();
            return 1;
        }
        gameRef.resetMoves();
        return 0;
    }

    @GetMapping("/DeleteGame/{id}")
    public void endGame(@PathVariable("id") int id) {
        RoomDto.endGame(id);
    }
    

    private List<Integer> getWinnerMoves(ArrayList<List<Integer>> moves) {
        return moves.stream()
             .max(Comparator.comparingInt(
                l -> l.stream()
                      .mapToInt(Integer::intValue)
                      .sum()  
             ))
             .orElse(Collections.emptyList());
    }

}
