package com.findhub.finhubbackend.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSkillUpdateModel {
    private int accountId;
    private int skillId;
    private int level;
}
