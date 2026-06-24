package com.cal.pvp.calculator_pvp_api.Models;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Move {
    @JsonProperty("numbers")
    public List<Integer> numbers;
    
    @JsonProperty("gameID")
    public int gameID;
    
    @JsonProperty("email")
    public String email;

    public List<Integer> getNumbers() {
        return numbers == null ? Collections.emptyList() : numbers;
    }
    
    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Move [numbers=" + (numbers != null ? numbers.toString() : "N/A") + ", gameID=" + gameID + ", email=" + email + "]";
    }


    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public Move() {
        
    }

}
