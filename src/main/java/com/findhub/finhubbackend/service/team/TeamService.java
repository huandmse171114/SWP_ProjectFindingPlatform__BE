package com.findhub.finhubbackend.service.team;

import java.util.List;

import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface TeamService extends Service<Team, TeamStatus> {
    public List<Team> findAllById(int id);
    // balance: không cần tìm
    public List<Team> findAllByBalance(float balance);

    public List<Team> findAllByBalanceBetween(float start, float end);

    
}
