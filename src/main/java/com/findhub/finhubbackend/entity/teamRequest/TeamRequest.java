package com.findhub.finhubbackend.entity.teamRequest;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.findhub.finhubbackend.entity.entity.MyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(
    name = "Team_Request"
    // ,
    // uniqueConstraints = @UniqueConstraint(
    //     columnNames = {
    //         "SenderId",
    //         "ReceiverId"
    //     }
    // )
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class TeamRequest extends MyEntity{
    @Id
    @Column(
        name = "Id",
        nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
		name = "SenderId",
		nullable = false
	)
	private int senderId;

    @Column(
		name = "ReceiverId",
		nullable = false
	)
	private int receiverId;

    @Column(
		name = "TeamId",
		nullable = false
	)
	private int teamId;

    @Nationalized
    @Column(
		name = "Message",
		nullable = false
	)
	private String message;

    @Default
	@Column(
		name = "CreateDate",
		nullable = true
	)
	private Date createDate = new Date(System.currentTimeMillis());


    @Default
    @Column(
        name = "Status",
        nullable = false
    )
    private int status = TeamRequestStatus.REQUESTING.getValue();
}
