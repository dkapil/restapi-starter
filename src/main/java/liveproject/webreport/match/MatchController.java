package liveproject.webreport.match;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import liveproject.webreport.season.Season;

@RestController
public class MatchController {

	private MatchService matchService;

	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping("/season")
	public List<String> getAllSeasons() {
		return matchService.getAllSeasons();
	}

	@GetMapping("/season-report/{season}")
	public Season getSeasonStatistics(@PathVariable("season") String seasonID) {
		return matchService.aggregateSeason(seasonID);
	}

	@GetMapping("/matches-report/{season}")
	public Set<Match> getAllMatchResultsForSeason(@PathVariable("season") String seasonID) {
		return matchService.getAllBySeasonSorted(seasonID);
	}

	@PostMapping("/match/{season}")
	public Map<String, Integer> addMatchResultsToSeason(@PathVariable("season") String seasonID,
			@RequestBody List<Match> matches) {
		return matchService.saveAll(seasonID, matches);
	}

}
