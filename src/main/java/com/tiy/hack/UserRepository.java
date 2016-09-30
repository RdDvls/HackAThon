package com.tiy.hack;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by RdDvls on 9/30/16.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findFirstByEmail (String email);
}
