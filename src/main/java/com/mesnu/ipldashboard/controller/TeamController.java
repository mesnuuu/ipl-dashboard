package com.mesnu.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mesnu.ipldashboard.model.Match;
import com.mesnu.ipldashboard.model.Team;
import com.mesnu.ipldashboard.repository.MatchRepository;
import com.mesnu.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
	
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	
	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}

	// get all teams
	
	@GetMapping("/teams")
	public Iterable<Team> getAllTeam(){
		
		return this.teamRepository.findAll();
								
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName){
		
		Team team = this.teamRepository.findByTeamName(teamName);
		
		team.setMatches(matchRepository.findLatestMatchByTeam(teamName, 4));
		
		return team;
	}
	
    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
    	
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        
        return this.matchRepository.getMatchesByTeamBetweenDates(
            teamName,
            startDate,
            endDate
            );
    }
	
}
