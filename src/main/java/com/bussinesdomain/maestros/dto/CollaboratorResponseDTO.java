package com.bussinesdomain.maestros.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CollaboratorResponseDTO {

    @EqualsAndHashCode.Include

    private Long idCollaborator;

    private String lastName;
    private String names;
    private String email;

    private Integer state;

    private Long idLeader;
    private String leaderNames;


    private Long idRol;
    private String rolDescription;

    private Long idRegion;
    private String regionDescription;

    private Long idFunctionalLeader;
    private String functionalLeaderNames;
    
    private Long idStatusCollaborator;
    private String descriptionStatusCollaborator;

    private Long idPractice;
    private String practiceDescription;

}
