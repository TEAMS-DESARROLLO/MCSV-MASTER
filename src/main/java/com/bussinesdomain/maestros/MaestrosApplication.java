package com.bussinesdomain.maestros;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.bussinesdomain.maestros.procesos.ILoadData;
import com.bussinesdomain.maestros.procesos.ILoadDataCommunity;


@SpringBootApplication
//@EnableDiscoveryClient
public class MaestrosApplication  implements CommandLineRunner { 

	@Autowired
	private ILoadData loadData;

	@Autowired
	private ILoadDataCommunity loadDataCommunity;


	public static void main(String[] args) {
		SpringApplication.run(MaestrosApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

	 	// List<PeruTotalDto> peruTotal = loadData.loadPeruTotalFromXls();

		// List<Map<String, Object>> functionalLeaderList = loadData.getFunctionalLeaderFromPeruTotalDTO(peruTotal) ;

		// loadData.updateFunctionalLeaderInDataBase(functionalLeaderList); 
		// loadData.updateCollaboratorInDataBase(peruTotal);
		// System.out.println("Carga de datos finalizada");

		// List<CommunityFromXlsDTO> lstCommunity = loadDataCommunity.loadDataFromXls();
		// loadDataCommunity.updatePracticeInCollaborator(lstCommunity,30L, peruTotal);
		// System.out.println("Carga de datos finalizada");
		

	}

}
