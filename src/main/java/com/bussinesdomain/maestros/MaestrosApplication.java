package com.bussinesdomain.maestros;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bussinesdomain.maestros.dto.CommunityFromXlsDTO;
import com.bussinesdomain.maestros.dto.PeruTotalDto;
import com.bussinesdomain.maestros.procesos.ILoadData;
import com.bussinesdomain.maestros.procesos.ILoadDataCommunity;


@SpringBootApplication
//@EnableDiscoveryClient
public class MaestrosApplication  implements CommandLineRunner { 

	// @Autowired
	// private ILoadData loadData;

	// @Autowired
	// private ILoadDataCommunity loadDataCommunity;


	public static void main(String[] args) {
		SpringApplication.run(MaestrosApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

	 	//List<PeruTotalDto> peruTotal = loadData.loadPeruTotal();

		//List<Map<String, Object>> functionalLeaderList = loadData.getFunctionalLeader(peruTotal);

		//loadData.updateFunctionalLeader(functionalLeaderList);
		//loadData.updateCollaborator(peruTotal);
		System.out.println("Carga de datos finalizada");

		// List<CommunityFromXlsDTO> lstCommunity = loadDataCommunity.loadDataFromXls();
		// loadDataCommunity.updateMasterData(lstCommunity,1L);
		// System.out.println("Carga de datos finalizada");
		

	}

}
