package com.mesnu.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.mesnu.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>  {

    Team findByTeamName(String teamName);

}
