package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.dto.MemberDTO;
import com.findhub.finhubbackend.entity.member.Member;

// @Repository
public interface MemberRepository extends Repo<Member> {

    /**
     * tìm chính xác name
     */
    Optional<Member> findByName(String name);

    /**
     * tìm tất cả Member có chính xác
     */
    List<Member> findAllByNameContaining(String name);

    Optional<Member> findByEmail(String email);

    List<Member> findAllByEmailContaining(String email);

    Optional<Member> findByPhone(String phone);

    List<Member> findAllByPhoneLike(String phone);

    // balance: không cần tìm
    List<Member> findAllByBalance(float balance);

    List<Member> findAllByBalanceBetween(float start, float end);

    List<Member> findAllByMajorId(int id);

    List<Member> findAllByStatus(int status);

    @Query(value = """
            SELECT *
            FROM
                Member
            WHERE
                Name LIKE %:name%
            OR
                Email LIKE %:email%
            OR
                Phone LIKE %:phone%
            OR
                Id LIKE :id%
			ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<MemberDTO> getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(
            @Param("id") int id,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("name") String name);

    @Query(value = """
            SELECT *
            FROM
                Member
            WHERE
                Name LIKE %:name%
			ORDER BY
                Id ASC
            """, nativeQuery = true)
            List<MemberDTO> getAllByNameContaining(@Param("name") String name);

    @Query(value = """
            SELECT
                m.Id,
                m.Phone,
                m.Email,
                m.Name,
                m.Description,
                m.Balance,
                m.DOB,
                tm.Role
            FROM
                Team_Member tm
            LEFT JOIN
                Member m ON tm.MemberId = m.Id
            WHERE
                tm.TeamId = :id
            ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<MemberDTO> getAllByTeamId(@Param("id") int id);

    boolean existsByEmail(String email);
}
