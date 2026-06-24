package com.cal.pvp.calculator_pvp_api.Models;

import org.springframework.stereotype.Component;

import com.cal.pvp.calculator_pvp_api.Repository.UserRepository;

@Component
public class RepositoryDTO {
   
    @SuppressWarnings("unused")
    public static UserRepository userRepository;

    public RepositoryDTO(UserRepository userRepo)
    {
        userRepository = userRepo;
    }
}
