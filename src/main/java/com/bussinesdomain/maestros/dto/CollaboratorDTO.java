package com.bussinesdomain.maestros.dto;

import com.bussinesdomain.maestros.constants.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CollaboratorDTO {

    @EqualsAndHashCode.Include
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idCollaborator;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String lastName;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String names;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String email;

    private Integer state;


    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idLeader;


    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idRol;


    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idRegion;


    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idFunctionalLeader;
    @NotNull(message=ValidationMessage.CAN_T_BE_NULL)
    private Long idStatusCollaborator;


}
