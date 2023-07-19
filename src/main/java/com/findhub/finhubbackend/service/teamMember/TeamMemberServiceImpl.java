package com.findhub.finhubbackend.service.teamMember;

import java.util.List;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.teamMember.TeamMember;
import com.findhub.finhubbackend.entity.teamMember.TeamMemberStatus;
import com.findhub.finhubbackend.repository.TeamMemberRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class TeamMemberServiceImpl
    extends ServiceImpl<TeamMember, TeamMemberRepository, TeamMemberStatus>
    implements TeamMemberService {

    @Override
    public List<TeamMember> findAllByMemberId(int id) {
        return repo.findAllByMemberId(id);
    }

    @Override
    public List<TeamMember> findAllByTeamId(int id) {
        return repo.findAllByTeamId(id);
    }

	@Override
	public List<TeamMember> findAllByMemberIdAndRole(int id, int value) {
		return repo.findAllByMemberIdAndRole(id, value);
	}

	@Override
	public List<TeamMember> findAllByTeamIdAndRole(int id, int value) {
		return repo.findAllByTeamIdAndRole(id, value);
	}

	@Override
	public List<TeamMember> findAllByRole(int value) {
		return repo.findAllByRole(value);
	}

}
