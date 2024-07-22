package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityResultadoDTO {

    private Long comunityId;
    private String comunityDescription;
    private Long subPracticaId; 
    private Long subPracticaCommunityId; 
    private String subPracticaDescription;
    private Long tecnologyId;
    private Long tecnologyIdForSubpractica;
    private String tecnlogoryName;

}
