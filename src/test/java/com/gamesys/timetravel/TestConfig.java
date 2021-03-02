package com.gamesys.timetravel;

import com.gamesys.timetravel.repository.TimeTravelFileRepository;
import com.gamesys.timetravel.repository.TimeTravelRepository;
import com.gamesys.timetravel.service.CreateTimeTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @MockBean
    TimeTravelFileRepository timeTravelFileRepository;


    @Bean
    public CreateTimeTravelService createTimeTravelService() {
        return new CreateTimeTravelService(timeTravelFileRepository);
    }
}
