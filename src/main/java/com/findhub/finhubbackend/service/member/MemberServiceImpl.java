package com.findhub.finhubbackend.service.member;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.member.MemberStatus;
import com.findhub.finhubbackend.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repo;

    @Override
    public Member add(Member entity) {
        return (entity != null) ? repo.save(entity) : null;
    }

    @Override
    public boolean changeStatus(int id, int status) {
        Optional<Member> entity = repo.findById(id);
        return changeStatus(entity.isPresent() ? entity.get() : null, status);
    }

    @Override
    public boolean changeStatus(int id, MemberStatus status) {
        return false;
    }

    @Override
    public boolean changeStatus(Member entity, int status) {
        return false;
    }

    @Override
    public boolean changeStatus(Member entity, MemberStatus status) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Member entity) {
        return false;
    }

    @Override
    public List<Member> findAllByIdLike(int id) {
        return null;
    }

    @Override
    public List<Member> findAllByStatus(int status) {
        return null;
    }

    @Override
    public List<Member> findAllByStatus(MemberStatus status) {
        return null;
    }

    @Override
    public Member findById(int id) {
        return null;
    }

    @Override
    public List<Member> getAll() {
        return null;
    }

    @Override
    public void save(Member entity) {

    }

    @Override
    public Member update(int id, Member entity) {
        return null;
    }

    @Override
    public Member update(Member oldT, Member newT) {
        return null;
    }

}
