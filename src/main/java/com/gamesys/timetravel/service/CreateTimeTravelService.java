package com.gamesys.timetravel.service;

import com.gamesys.timetravel.domain.exception.TimeTravelException;
import com.gamesys.timetravel.domain.exception.TimeTravelException.ErrorType;
import com.gamesys.timetravel.domain.timetravel.TimeTravelMessage;
import com.gamesys.timetravel.domain.timetravel.TimeTravelResponse;
import com.gamesys.timetravel.repository.TimeTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateTimeTravelService {

    private TimeTravelRepository timeTravelRepository;

    @Autowired
    public CreateTimeTravelService(TimeTravelRepository timeTravelRepository) {
        this.timeTravelRepository = timeTravelRepository;
    }

    public TimeTravelResponse create(TimeTravelMessage timeTravelMessage) {
        TimeTravelResponse timeTravelResponse = new TimeTravelResponse();
        List<TimeTravelMessage> messages = timeTravelRepository.getTimeTravelMessages();
        boolean recordExist = ifRecordExist(messages, timeTravelMessage);
        // If record exist in the file then throw an exception
        if (recordExist) {
            throw TimeTravelException.createTimeTravelException(ErrorType.VALIDATION,
                    "Travel to the same place and date twice. You don't want to create a paradox by" +
                            "someone meeting themselves!", "Record already exist");
        }
        timeTravelRepository.writeTimeTravelMessage(timeTravelMessage);
        timeTravelResponse.setResponseMessage("Record has been created");
        return timeTravelResponse;
    }

    boolean ifRecordExist(List<TimeTravelMessage> messages, TimeTravelMessage timeTravelMessage) {
        boolean recordExist = false;
        for (TimeTravelMessage ttm : messages)
            // Check if record already exist
            if (ttm.getPgi().equals(timeTravelMessage.getPgi()) &&
                    ttm.getPlace().equals(timeTravelMessage.getPlace()) &&
                    ttm.getDate().equals(timeTravelMessage.getDate())) {
                recordExist = true;
                break;
            }
        return recordExist;
    }
}
