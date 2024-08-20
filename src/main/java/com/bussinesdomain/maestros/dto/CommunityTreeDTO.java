package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityTreeDTO {

    private Long idCommunity;
    private String description;

    private List<SubpracticaTreeDTO> subpracticas;


}
