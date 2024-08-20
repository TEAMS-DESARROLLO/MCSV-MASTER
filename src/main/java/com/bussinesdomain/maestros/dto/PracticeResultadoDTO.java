package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeResultadoDTO {

    private Long practiceId;
    private String practiceDescription;
    private Long specializationId; 
    private Long specializationPracticeId; 
    private String specializationDescription;
    private Long tecnologyId;
    private Long tecnologyIdForSpecialization;
    private String tecnlogoryName;

}
