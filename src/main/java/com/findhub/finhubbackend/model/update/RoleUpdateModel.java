package com.findhub.finhubbackend.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleUpdateModel {
    private int accountId;
    private int roleId;
}
