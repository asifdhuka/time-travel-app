package com.gamesys.timetravel.controller;

import com.gamesys.timetravel.domain.timetravel.TimeTravelMessage;
import com.gamesys.timetravel.domain.timetravel.TimeTravelResponse;
import com.gamesys.timetravel.service.CreateTimeTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/timeTravel")
public class TimeTravelController {
    private final CreateTimeTravelService createTimeTravelService;

    @Autowired
    public TimeTravelController(CreateTimeTravelService createTimeTravelService) {
        this.createTimeTravelService = createTimeTravelService;
    }

    // @Valid will validate the request before even reaching to controller
    @PostMapping(value = "/timeTravelMessage")
    public ResponseEntity<TimeTravelResponse> timeTravelMessage(@RequestBody @Valid TimeTravelMessage timeTravelMessage) {
        TimeTravelResponse timeTravelResponse = createTimeTravelService.create(timeTravelMessage);
        return ResponseEntity.ok(timeTravelResponse);
    }
}
