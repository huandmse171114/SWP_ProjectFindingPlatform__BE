package com.findhub.finhubbackend.service.team;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.TeamDTO;
import com.findhub.finhubbackend.entity.team.Team;
import com.findhub.finhubbackend.entity.team.TeamStatus;
import com.findhub.finhubbackend.repository.TeamRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class TeamServiceImpl extends ServiceImpl<Team, TeamRepository, TeamStatus>
        implements TeamService {

    @Override
    public List<Team> findAllByBalance(float balance) {
        return repo.findAllByBalance(balance);
    }

    @Override
    public List<TeamDTO> getAllByNameContaining(String name) {
        return repo.getAllByNameContaining(name);
    }

    @Override
    public List<TeamDTO> getAllByIdStartingWithOrNameContaining(String keyword) {
        int id = Integer.parseInt(keyword);
        return repo.getAllByIdStartingWithOrNameContaining(id, keyword);
    }

    // @Override
    // public List<Team> findAllByNameContaining(String name) {
    // return repo.findAllByNameContaining(name);
    // }

    @Override
    public List<Team> findAllById(int id) {
        return repo.findAllById(id);
    }

    @Override
    public List<Team> findAllByBalanceBetween(float start, float end) {
        return repo.findAllByBalanceBetween(start, end);
    }

}
