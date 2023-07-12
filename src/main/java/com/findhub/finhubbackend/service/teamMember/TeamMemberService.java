package com.findhub.finhubbackend.service.teamMember;

import java.util.List;

import com.findhub.finhubbackend.entity.teamMember.TeamMember;
import com.findhub.finhubbackend.entity.teamMember.TeamMemberStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface TeamMemberService
    extends Service<TeamMember, TeamMemberStatus>{

    // public boolean isExistedInTeam(int memberId, int teamId);

    public List<TeamMember> findAllByMemberId(int id);

    public List<TeamMember> findAllByTeamId(int id);

}
