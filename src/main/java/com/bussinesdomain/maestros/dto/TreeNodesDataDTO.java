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
public class TreeNodesDataDTO {

    private String key;
    private String label;
    private String data;
    private String icon;
    private List<TreeNodesDataDTO> children ;

}
