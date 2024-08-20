package com.bussinesdomain.maestros.procesos;

import com.bussinesdomain.maestros.dto.PeruTotalDto;
import java.util.List;
import java.util.Map;


public interface ILoadData {

    List<PeruTotalDto> loadPeruTotalFromXls();

    void updateFunctionalLeaderInDataBase(List<Map<String, Object>> functionalLeaderList);

    void updateCollaboratorInDataBase(List<PeruTotalDto> peruTotals);

    List<Map<String, Object>> getFunctionalLeaderFromPeruTotalDTO(List<PeruTotalDto> peruTotalDtos);

}
