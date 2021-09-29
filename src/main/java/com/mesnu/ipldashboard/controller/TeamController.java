package com.mesnu.ipldashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mesnu.ipldashboard.model.Team;
import com.mesnu.ipldashboard.repository.MatchRepository;
import com.mesnu.ipldashboard.repository.TeamRepository;

@RestController
public class TeamController {
	
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	
	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}



	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName){
		
		Team team = this.teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName));
		
		return team;
	}
	
}
