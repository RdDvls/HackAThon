package com.tiy.hack;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by RdDvls on 9/30/16.
 */
public interface EventRepository extends CrudRepository <EventItem, Integer>{
    List<EventItem> findByUser(User user);

    List<EventItem> findById(EventItem eventItem);
}
