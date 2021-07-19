package liveproject.webreport.match;

import static liveproject.webreport.config.TestWebConfig.SEASON_STR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import liveproject.webreport.config.TestWebConfig;

@SpringBootTest
@ContextConfiguration(classes = { TestWebConfig.class })
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class MatchControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private MatchController controller;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void shouldReturnAllSeasons() throws Exception {
		mockMvc.perform(get("/season-report/" + SEASON_STR))
				.andExpect(status().isOk());
	}	
	
	@Test
	public void shouldReturnSeasonReport() throws Exception {
		mockMvc.perform(get("/season-report/" + SEASON_STR))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldReturnMatchesReport() throws Exception {
		mockMvc.perform(get("/matches-report/" + SEASON_STR))
				.andExpect(status().isOk());
	}	
	
	@Test
	public void addMatchDetailsToSeason() throws Exception {
		mockMvc.perform(post("/match/" + SEASON_STR).content("[]")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
