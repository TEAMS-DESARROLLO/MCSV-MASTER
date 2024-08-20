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
public class CollaboratorTechnologyRequestDTO {

    @EqualsAndHashCode.Include
    private Long idCollaboratorTechnology;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaborator;
    private String collaboratorNames;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCatalogTechnology;
    private String descriptionCatalogTechnology;
    
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Integer collaboratorRank;

    private Integer evaluatorRank;
}
