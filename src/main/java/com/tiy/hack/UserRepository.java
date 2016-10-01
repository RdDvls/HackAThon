package com.tiy.hack;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by RdDvls on 9/30/16.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findFirstByEmail (String email);
    User findFirstByEmailAndPassword (String email, String password);
//    User findByemailAndpassword(String email, String password);
}
