package com.bussinesdomain.maestros.services.impl;



import java.util.*;

import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.dto.PracticeResultadoDTO;
import com.bussinesdomain.maestros.dto.TreeNodesDataDTO;
import com.bussinesdomain.maestros.services.ITreePracticeSpacializationTenololgyService;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreePracticeSpecializationTenololgyServiceImpl implements ITreePracticeSpacializationTenololgyService {
    
    private final EntityManager entityManager;
    
    @Override
    public List<TreeNodesDataDTO> getTreePracticeSpecializationTenololgy() {
        
        String sql = "SELECT new com.bussinesdomain.maestros.dto.PracticeResultadoDTO(c.idPractice, c.description, " +
                " sp.idSpecialization, sp.practiceEntity.idPractice, sp.descriptionSpecialization, t.idTechnology, t.subpracticaEntity.idSpecialization, t.name) " + 
                "FROM PracticeEntity c " + 
                "LEFT JOIN SpecializationEntity sp on sp.practiceEntity.idPractice = c.idPractice " + 
                "LEFT JOIN TechnologyEntity t  on t.subpracticaEntity.idSpecialization = sp.idSpecialization ";

        Query querySelect = entityManager.createQuery(sql);
        @SuppressWarnings("unchecked")
        List<PracticeResultadoDTO> lst = querySelect.getResultList();

        List<TreeNodesDataDTO> lstTree = this.buildTree(lst);

        return lstTree;

    }

    public List<TreeNodesDataDTO> buildTree(List<PracticeResultadoDTO> lst) {

        List<Map<String,String>> lstMap = this.getCommunities(lst);


        List<TreeNodesDataDTO> lstTreeNodesData = new ArrayList<>();

        lstMap.stream().forEach( p-> {

            
            TreeNodesDataDTO treeNodesDataDTO = TreeNodesDataDTO.builder().key(p.get("id"))
                .label( p.get("description")).data("Practice")
                .icon("pi pi-fw pi-inbox")
                .build();


            List<Map<String,String>> lstSpecializations = this.getSpecializationtesByPractice(lst, Long.parseLong(p.get("id")));

            treeNodesDataDTO.setChildren(
                lstSpecializations.stream().map( sp -> {
                    TreeNodesDataDTO treeNodesDataDTO2 = TreeNodesDataDTO.builder().key(sp.get("id")).label( sp.get("description")).data("subpractica")
                    .icon("pi pi-fw pi-cog").build();

                    List<Map<String,String>> lstTechnologys = this.getTechnologyBySpecialization(lst, Long.parseLong(sp.get("id")));

                    treeNodesDataDTO2.setChildren(
                        lstTechnologys.stream().map( t -> {
                            TreeNodesDataDTO treeNodesDataDTO3 = TreeNodesDataDTO.builder().key(t.get("id")).label( t.get("description"))
                                .icon("pi pi-fw pi-file")
                                .data("technology").build();
                            return treeNodesDataDTO3;
                        }).toList()
                    );

                    return treeNodesDataDTO2;
                }
            ).toList());
            

            lstTreeNodesData.add(treeNodesDataDTO);
        });

        return lstTreeNodesData;
        
    }    

    public List<Map<String,String>> getCommunities(List<PracticeResultadoDTO> lst){
        List<Map<String,String>> lstMap = new ArrayList<>();
        

        lst.stream().map(PracticeResultadoDTO::getPracticeId ) .distinct()
            .forEach( p -> {
                Map<String,String> map = new HashMap<>();        
                PracticeResultadoDTO practice = lst.stream().filter( x -> x.getPracticeId().equals(p)).findFirst().get();

                map.put("id", practice.getPracticeId().toString());
                map.put("description", practice.getPracticeDescription());

                lstMap.add(map);
                
            });
        
        return lstMap;

    }

    public List<Map<String,String>> getSpecializationtesByPractice(List<PracticeResultadoDTO> lst, Long idPractice){
        List<Map<String,String>> lstMap = new ArrayList<>();

        lst.stream().filter(p-> p.getSpecializationPracticeId()!=null). filter( f-> f.getSpecializationPracticeId().equals(idPractice) ).map(PracticeResultadoDTO::getSpecializationId) .distinct()
            .forEach( p -> {
                Map<String,String> map = new HashMap<>();        
                PracticeResultadoDTO practice = lst.stream().filter( x -> x.getSpecializationId().equals(p) && x.getSpecializationId() != null ).findFirst().get();
                if(practice.getSpecializationId() != null){
                    map.put("id", practice.getSpecializationId().toString() );
                    map.put("description", practice.getSpecializationDescription());
                    lstMap.add(map);
                }
            });
        
        return lstMap;
    } 
    
    public List<Map<String,String>> getTechnologyBySpecialization(List<PracticeResultadoDTO> lst, Long idSpecializationte){
        List<Map<String,String>> lstMap = new ArrayList<>();

        lst.stream().filter(p-> p.getTecnologyIdForSpecialization() !=null ).filter( f -> f.getTecnologyIdForSpecialization().equals(idSpecializationte))
            .forEach( p -> {
                Map<String,String> map = new HashMap<>();        

                map.put("id", p.getTecnologyId().toString() );
                map.put("description", p.getTecnlogoryName());
                lstMap.add(map);

            });
        
        return lstMap;
    }     




}
