package com.bussinesdomain.maestros.procesos.impl;

import com.bussinesdomain.maestros.dto.PeruTotalDto;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import com.bussinesdomain.maestros.models.RegionEntity;
import com.bussinesdomain.maestros.procesos.ILoadData;

import com.bussinesdomain.maestros.services.ICollaboratorService;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;

import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class LoadDataImpl implements ILoadData {

    private final IFunctionalLeaderService functionalLeaderService;

    private final ICollaboratorService collaboratorService;

    @Override
    public List<PeruTotalDto> loadPeruTotalFromXls() {

        List<PeruTotalDto> peruTotalDtoList = new ArrayList<>();

        String excelFilePath = "d:\\PeruTotal2024.xlsx";
        try (InputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(1); // Obtener la primera hoja
            //obtener el valor de las 2 primeras columnas
            
            Integer rowStart = 1;
            for (Row row : sheet) {
                if (row.getRowNum() < rowStart) {
                    continue;
                }
                PeruTotalDto peruTotalDto = new PeruTotalDto();
                for (int i = 0; i < 19; i++) {
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
                            peruTotalDto = setPeruTotalDTO(i, dato, peruTotalDto);
                        }
                        

                    
                }
                peruTotalDtoList.add(peruTotalDto);

            }
            



        } catch (IOException e) {
            e.printStackTrace();
        }   
        return peruTotalDtoList;     

    }

    
    public PeruTotalDto setPeruTotalDTO(Integer col, String dato, PeruTotalDto peruTotalDto) {
       

        switch (col) {
            case 0:
                peruTotalDto.setCodigo(dato);
                break;
            case 1:
                peruTotalDto.setNombre(dato);
                break;
            case 2:
                peruTotalDto.setProyecto(dato);
                break;
            case 3:
                peruTotalDto.setFIngreso(dato);
                break;
            case 4:
                peruTotalDto.setFSalida(dato);
                break;
            case 5:
                peruTotalDto.setRol(dato);
                break;
            case 6:
                peruTotalDto.setRf(dato);
                break;
            case 7:
                peruTotalDto.setEmail(dato);
                break;
            case 8:
                peruTotalDto.setEmpresa(dato);
                break;
            case 9:
                peruTotalDto.setPais(dato);
                break;
            case 10:
                peruTotalDto.setProvincia(dato);
                break;
            case 11:
                peruTotalDto.setCentroTrabajo(dato);
                break;
            case 12:
                peruTotalDto.setIdioma(dato);
                break;
            case 13:
                peruTotalDto.setFuncionPrincipal(dato);
                break;
            case 14: 
                peruTotalDto.setPerfilProfesional(dato);
                break;
            case 15:
                peruTotalDto.setPerfilProfesionalSecundario(dato);
                break;
            case 16:
                peruTotalDto.setPFuncional(dato);
                break;
            case 17:
                peruTotalDto.setPTecnologica(dato);
                break;
            case 18:
                peruTotalDto.setProductosYSoluciones(dato);
                break;
            case 19:
                peruTotalDto.setFuncionSecundaria(dato);
                break;

            case 20:
                peruTotalDto.setDetalleTitulacion(dato);
                break;
            case 21:
                peruTotalDto.setPFuncional(dato);
                break;                
            case 22:
                //peruTotalDto.setPFuncional(dato);
                break;                

            case 23:
                peruTotalDto.setPTecnologica(dato);
                break;                
            case 24:
                peruTotalDto.setTitulacion(dato);
                break;                
            case 25:
                peruTotalDto.setDetalleTitulacion(dato);
                break;                
            case 26:
                peruTotalDto.setUgr(dato);
                break;
            case 27:
                peruTotalDto.setUnidadDeGestion(dato);
                break;
            case 28:
                peruTotalDto.setUnidadOrganizativa(dato);
                break;
            case 29:
                peruTotalDto.setModeloDeCarrera(dato);
                break;
            case 30:
                peruTotalDto.setPlanDeCarrera(dato);
                break;
            case 31:
                peruTotalDto.setFase(dato);
                break;
            case 32:
                peruTotalDto.setGrado(dato);
                break;
            case 33:
                peruTotalDto.setRol(dato);
                break;
            case 34:
                peruTotalDto.setRolCategoriaFacturable(dato);
                break;
            case 35:
                peruTotalDto.setTasa(dato);
                break;
            case 36:
                peruTotalDto.setMoneda(dato);
                break;
            case 37:
                peruTotalDto.setExperiencia(dato);
                break;
            case 38:
                peruTotalDto.setFechaUltimaModificacion(dato);
                break;
            case 39:
                peruTotalDto.setPorcentajeCumplimiento(dato);
                break;
            case 40:
                peruTotalDto.setCFuncionales(dato);
                break;
            case 41:
                peruTotalDto.setCTecnologicos(dato);
                break;
            case 42:
                peruTotalDto.setCOffering(dato);
                break;
            case 43:
                peruTotalDto.setCertificacion(dato);
                break;

        
            default:
                break;
        }

        return peruTotalDto;

    }


    @Override
    public List<Map<String, Object>> getFunctionalLeaderFromPeruTotalDTO(List<PeruTotalDto> peruTotalDtos) {

        List<String> uniqueRfList = peruTotalDtos.stream()
            .map(PeruTotalDto::getRf)
            .distinct()
            .collect(Collectors.toList());

        List<Map<String, Object>> collaboratorList = uniqueRfList.stream()
            .map(rf -> {

                Optional<PeruTotalDto> pt =  peruTotalDtos.stream().filter(p -> p.getNombre().trim().equals(rf.trim())).findFirst(); 
                if(!pt.isPresent() ){
                    return null;
                }
                if(pt.isEmpty()){
                    return null;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("codigo", pt.get().getCodigo());
                map.put("nombre", pt.get().getNombre() );
                return map;


            })
            .collect(Collectors.toList());

        return   collaboratorList.stream().filter(nullMap -> nullMap != null).toList() ;

        //return collaboratorList;

    }

    @Override
    public void updateFunctionalLeaderInDataBase(List<Map<String, Object>> functionalLeaderList) {

        try {

            for (Map<String, Object> map : functionalLeaderList) {
                
                FunctionalLeaderEntity functionalLeader = new FunctionalLeaderEntity();
                Long idFuncionalLeader = Long.valueOf( (String) map.get("codigo") );
                //System.out.println("idFuncionalLeader: " + idFuncionalLeader);

                functionalLeader.setIdFunctionalLeader( idFuncionalLeader );
                functionalLeader.setNames( (String) map.get("nombre") );
                functionalLeader.setRegistrationStatus("A");

                functionalLeaderService.create(functionalLeader);
                functionalLeader = null;
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar los datos del líder funcional " + e.getMessage());
        }

       
    }

    @Override
    public void updateCollaboratorInDataBase(List<PeruTotalDto> peruTotalDtos) {

        List<CollaboratorEntity> collaboratorList = new ArrayList<>();

        Integer cnt = 0;

        RegionEntity region = RegionEntity.builder().idRegion(13L). build();
       
        for (PeruTotalDto peruTotalDto : peruTotalDtos) {

            CollaboratorEntity collaborator = new CollaboratorEntity();

            String name = peruTotalDto.getNombre();

            collaborator.setIdCollaborator( Long.valueOf( peruTotalDto.getCodigo()) );
            collaborator.setLastName(name.substring(0, name.indexOf(",")));
            collaborator.setNames(name.substring(name.indexOf(",") + 1));
            collaborator.setEmail(peruTotalDto.getEmail());
            collaborator.setRegistrationStatus("A");
            collaborator.setRegion( region );
            collaborator.setRf(peruTotalDto.getRf());
            
            collaboratorList.add(collaborator);

            //collaboratorService.create(collaborator);
            collaborator = null;
            if(cnt == 20){
                collaboratorService.createAll(collaboratorList);
                collaboratorList.clear();
                cnt = 0;
            }
            cnt++;
        }

        if(collaboratorList.size() > 0){
            collaboratorService.createAll(collaboratorList);
            collaboratorList = null;
        }
    
    }
}
