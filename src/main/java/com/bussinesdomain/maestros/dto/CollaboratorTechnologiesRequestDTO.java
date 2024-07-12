package com.bussinesdomain.maestros.dto;

import com.bussinesdomain.maestros.constants.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CollaboratorTechnologiesRequestDTO {
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaborator;
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    private List<Long> lstIdTechnologies;
}
