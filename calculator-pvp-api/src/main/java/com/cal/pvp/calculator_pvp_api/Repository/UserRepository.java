package com.cal.pvp.calculator_pvp_api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cal.pvp.calculator_pvp_api.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
        /**
     * Finds User by email and password
     *
     * @param email  The email of the user. 
     * @param pass   The password of the user.
     * @return The matching user.
     */
    Optional<User> findByEmailAndPass(String email, String pass);

   /**
     * Finds User by email
     *
     * @param email  The email of the user. 
     * @return The matching user.
     */
    Optional<User> findByEmail(String username);
}
