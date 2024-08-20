package com.bussinesdomain.maestros.procesos.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bussinesdomain.maestros.dto.CommunityFromXlsDTO;
import com.bussinesdomain.maestros.dto.PeruTotalDto;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import com.bussinesdomain.maestros.models.PracticeEntity;
import com.bussinesdomain.maestros.procesos.ILoadDataCommunity;
import com.bussinesdomain.maestros.services.ICollaboratorService;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;
import com.bussinesdomain.maestros.services.IPracticeService;

import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.*;

import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class LoadDataCommunityImpl implements ILoadDataCommunity {

    private final ICollaboratorService collaboratorService;
    private final IPracticeService communityService;
    private final IFunctionalLeaderService functionalLeaderService;

    @Override
    public List<CommunityFromXlsDTO> loadDataFromXls() {


        List<CommunityFromXlsDTO> communityFromXls = new ArrayList<>();

        String excelFilePath = "d:\\comunidaJavaMigra.xlsx";
        try (InputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Obtener la primera hoja

            
            Integer rowStart = 1;
            for (Row row : sheet) {
                if (row.getRowNum() < rowStart) {
                    continue;
                }
                CommunityFromXlsDTO communityFromXlsDTO = new CommunityFromXlsDTO();
                for (int i = 0; i < 4; i++) {
                    Cell cell = row.getCell(i);
                 
                        //CellType typeCell = cell.getCellType();
                        String dato = "";
                        if(cell != null){
                         
                            switch (cell.getCellType()) { 
                                
                                case STRING:

                                    dato = cell.getStringCellValue();
                                    break;
                                case NUMERIC:

                                    dato = String.valueOf(cell.getNumericCellValue());
                                    dato = dato.substring(0, dato.indexOf("."));
                                    break;
                                case BOOLEAN:

                                    dato = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                default:
                                    break;
                            }
                            communityFromXlsDTO = setCommunityFromXlsDTO(i, dato, communityFromXlsDTO);
                        }
                        

                    
                }
                communityFromXls.add(communityFromXlsDTO);

            }
            



        } catch (IOException e) {
            e.printStackTrace();
        }   
        return communityFromXls;     



    }

    public CommunityFromXlsDTO setCommunityFromXlsDTO(Integer col, String dato, CommunityFromXlsDTO communityFromXlsDTO) {

        switch (col) {
            case 0:
                communityFromXlsDTO.setIdCommunity(dato);
                break;
            case 1:
                communityFromXlsDTO.setIdCollaborator(dato);
                break;
            case 2:
                communityFromXlsDTO.setRoll(dato);
                break;
        
            default:
                break;
        }

        return communityFromXlsDTO;

    }    

    @Override
    public void updatePracticeInCollaborator(List<CommunityFromXlsDTO> communityFromXlsDTOs, Long idPractice, List<PeruTotalDto> peruTotalDtos) {


        PracticeEntity practiceEntity = communityService.readById(idPractice).get();

        //cargamos los responsables funcionales 
        List<FunctionalLeaderEntity> lstFunctionalLeader = functionalLeaderService.getAll();


        communityFromXlsDTOs.forEach(communityFromXlsDTO -> {

            Long idCollaborator = Long.parseLong(communityFromXlsDTO.getIdCollaborator());
            boolean existe = collaboratorService.exists(idCollaborator);

            if(existe){
                CollaboratorEntity collaboratorEntity = collaboratorService.readById(idCollaborator).get();
                String sIdCollaborator = idCollaborator.toString();
                PeruTotalDto peruTotalDto = peruTotalDtos.stream().filter(peruTotal -> peruTotal.getCodigo().equals(sIdCollaborator)).findFirst().orElse(null);  
                

                FunctionalLeaderEntity functionalLeaderEntity = new FunctionalLeaderEntity(); 

                if(peruTotalDto != null){
                    String rf = peruTotalDto.getRf();

                    if(rf != null && !rf.isEmpty()){
                       functionalLeaderEntity = lstFunctionalLeader.stream().filter(functionalLeader -> functionalLeader.getNames().equals(rf)).findFirst().orElse(null);
                    }
                }

                if (collaboratorEntity != null) {
                    collaboratorEntity.setFunctionalLeader(functionalLeaderEntity);
                    collaboratorEntity.setPractice(practiceEntity);
                    collaboratorService.create(collaboratorEntity);
                }
            }


        });

    }



}
