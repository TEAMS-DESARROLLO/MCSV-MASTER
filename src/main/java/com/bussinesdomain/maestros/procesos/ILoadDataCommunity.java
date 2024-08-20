package com.bussinesdomain.maestros.procesos;

import java.util.List;

import com.bussinesdomain.maestros.dto.CommunityFromXlsDTO;
import com.bussinesdomain.maestros.dto.PeruTotalDto;

public interface ILoadDataCommunity {

    List<CommunityFromXlsDTO> loadDataFromXls();

    void updatePracticeInCollaborator(List<CommunityFromXlsDTO> communityFromXlsDTOs, Long idCommunity, List<PeruTotalDto> peruTotalDto);

}
