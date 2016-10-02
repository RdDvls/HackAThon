package com.tiy.hack;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by RdDvls on 10/2/16.
 */
public interface FriendRepository extends CrudRepository<Friend,Integer> {
    public Iterable<Friend>findAllByUserId(int userID);
}
