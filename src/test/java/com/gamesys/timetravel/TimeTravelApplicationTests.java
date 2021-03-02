package com.gamesys.timetravel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesys.timetravel.controller.TimeTravelController;
import com.gamesys.timetravel.domain.timetravel.TimeTravelMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TimeTravelController.class)
@Import(TestConfig.class)
class TimeTravelApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void test_post_valid_request() throws Exception {
		LocalDate localDate = LocalDate.parse("2021-03-01");
		TimeTravelMessage timeTravelMessage = new TimeTravelMessage("S1212", "UK", localDate);
		mockMvc.perform(post("/timeTravel/timeTravelMessage")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(timeTravelMessage)))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void test_post_invalid_request_pgi_length() throws Exception {
		LocalDate localDate = LocalDate.parse("2021-03-01");
		TimeTravelMessage timeTravelMessage = new TimeTravelMessage("s12", "UK", localDate);
		mockMvc.perform(post("/timeTravel/timeTravelMessage")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(timeTravelMessage)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	@Test
	public void test_post_invalid_request_pgi_start_with_num() throws Exception {
		LocalDate localDate = LocalDate.parse("2021-03-01");
		TimeTravelMessage timeTravelMessage = new TimeTravelMessage("1sasqwqw", "UK", localDate);
		mockMvc.perform(post("/timeTravel/timeTravelMessage")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(timeTravelMessage)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}


}
