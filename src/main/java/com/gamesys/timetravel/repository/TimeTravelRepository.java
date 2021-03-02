package com.gamesys.timetravel.repository;

import com.gamesys.timetravel.domain.timetravel.TimeTravelMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTravelRepository {
    public List<TimeTravelMessage> getTimeTravelMessages();

    public boolean writeTimeTravelMessage(TimeTravelMessage message);
}
