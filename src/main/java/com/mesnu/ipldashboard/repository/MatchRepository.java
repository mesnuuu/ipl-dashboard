package com.mesnu.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mesnu.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

	// get all matches where team1 == teamName1 or team2 == teamName2
	// getting all matches of particular team order by date descending
	
	List<Match> getByTeam1OrTeam2OrderByDateDesc (String teamName1, String teamName2, Pageable pageable);

	@Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesByTeamBetweenDates(
        @Param("teamName") String teamName, 
        @Param("dateStart") LocalDate dateStart, 
        @Param("dateEnd") LocalDate dateEnd
    );
	
	default List<Match> findLatestMatchByTeam(String teamName, int count){
		
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0,count));
	}

}
