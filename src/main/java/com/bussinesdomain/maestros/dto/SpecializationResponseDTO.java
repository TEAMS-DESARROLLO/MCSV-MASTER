package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



import com.bussinesdomain.maestros.constants.ValidationMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data   
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationResponseDTO {

    @EqualsAndHashCode.Include
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idSpecialization;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String descriptionSpecialization;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idPractice;
    private String practiceDescription;

}
