package com.findhub.finhubbackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;

public interface ProjectDeliverableRepository
        extends Repo<ProjectDeliverable> {
    List<ProjectDeliverable> findAllByProjectId(int id);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE Project_Output
            SET
                Value = :value ,
                Description = :description ,
                DeliverableTypeId = :typeId ,
                Status = :status
            WHERE Id = :id
            """, nativeQuery = true)
    void updateById(
        @Param("id") int id,
        @Param("typeId") int DeliverableTypeId,
        @Param("value") String value,
        @Param("status") int status,
        @Param("description") String description
    );
}
