package com.bussinesdomain.maestros.dto;

import com.bussinesdomain.maestros.constants.ValidationMessage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CollaboratorTechnologyDTO {

    @EqualsAndHashCode.Include
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaboratorTechnology;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaborator;
    private String collaboratorNames;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idTechnology;
    private String technologyName;
}
