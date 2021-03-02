package com.gamesys.timetravel.repository;

import com.gamesys.timetravel.domain.exception.TimeTravelException;
import com.gamesys.timetravel.domain.timetravel.TimeTravelMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeTravelFileRepository implements TimeTravelRepository {
    // storing file to a root folder
    private String filePath = "timeTravel.json";

    // Reading file to get stored records and convert them to objects
    public List<TimeTravelMessage> getTimeTravelMessages() {
        ArrayList<TimeTravelMessage> messages = new ArrayList<>();
        Gson gson = new Gson();
        Type timeTravelMessageListType = new TypeToken<ArrayList<TimeTravelMessage>>() {
        }.getType();
        try {
            Reader reader = new FileReader(filePath);
            messages = gson.fromJson(reader, timeTravelMessageListType);
            reader.close();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
        return (messages != null) ? messages : new ArrayList<>();
    }

    // Writing a record to a file
    public boolean writeTimeTravelMessage(TimeTravelMessage message) {
        List<TimeTravelMessage> messages = this.getTimeTravelMessages();
        messages.add(message);
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter(filePath);
            gson.toJson(messages, writer);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.fillInStackTrace());
            throw TimeTravelException.createTimeTravelException(TimeTravelException.ErrorType.INTERNAL,
                    "Either file doesn't exist or access rights issues", "Error writing to a file");
        }
        return true;
    }
}
