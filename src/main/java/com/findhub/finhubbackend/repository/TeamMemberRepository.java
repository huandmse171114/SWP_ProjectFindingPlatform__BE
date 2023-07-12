package com.findhub.finhubbackend.repository;

import java.util.List;

import com.findhub.finhubbackend.entity.teamMember.TeamMember;


public interface TeamMemberRepository
        extends Repo<TeamMember>{

    List<TeamMember> findAllByMemberId(int id);

    List<TeamMember> findAllByTeamId(int id);
}
