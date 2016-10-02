package com.tiy.hack;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by RdDvls on 10/2/16.
 */
public interface UserEventRepository extends CrudRepository<UserEvent, Integer> {
    public Iterable<UserEvent> findAllByEventid(int eventId);
    public Iterable<UserEvent> findAllByEventID(int eventId);
}
