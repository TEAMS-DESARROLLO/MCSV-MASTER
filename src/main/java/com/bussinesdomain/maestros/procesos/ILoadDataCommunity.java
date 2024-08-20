package com.bussinesdomain.maestros.procesos;

import java.util.List;

import com.bussinesdomain.maestros.dto.CommunityFromXlsDTO;

public interface ILoadDataCommunity {

    List<CommunityFromXlsDTO> loadDataFromXls();

    void updateMasterData(List<CommunityFromXlsDTO> communityFromXlsDTOs, Long idCommunity);

}
