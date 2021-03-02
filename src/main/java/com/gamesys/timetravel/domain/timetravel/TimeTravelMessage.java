package com.gamesys.timetravel.domain.timetravel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gamesys.timetravel.validation.ValidTimeTravelMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@ValidTimeTravelMessage
public class TimeTravelMessage {
    private String pgi;
    private String place;
    private LocalDate date;
}
