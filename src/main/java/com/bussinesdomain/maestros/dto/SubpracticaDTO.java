package com.bussinesdomain.maestros.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubpracticaDTO {

    @Column(name = "id_subpractica")
    private Long idSubpractica;

    @Column(name = "description_subpractica")
    private String descriptionSubpractica;


}
