package com.gamesys.timetravel.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeTravelError {
    private String code;
    private String reason;
    private String message;
    private String status;
}
