package com.findhub.finhubbackend.service.team;

import java.util.List;

import com.findhub.finhubbackend.dto.TeamDTO;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface TeamService
        extends Service<Team, TeamStatus> {

    // balance: không cần tìm
    public List<Team> findAllByBalance(float balance);

    public List<Team> findAllByBalanceBetween(float start, float end);

    // public List<Team> findAllByNameContaining(String name);

    public List<TeamDTO> getAllByNameContaining(String name);

    public List<TeamDTO> getAllByIdStartingWithOrNameContaining(String input);

    public boolean isExistedInTeam(int memberId, int teamId);
}
