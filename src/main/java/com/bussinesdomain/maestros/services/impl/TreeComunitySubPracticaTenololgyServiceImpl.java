package com.bussinesdomain.maestros.services.impl;



import java.util.*;

import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.dto.CommunityResultadoDTO;
import com.bussinesdomain.maestros.dto.CommunityTreeDTO;

import com.bussinesdomain.maestros.dto.SubpracticaTreeDTO;
import com.bussinesdomain.maestros.dto.TechnologyResponseDTO;
import com.bussinesdomain.maestros.dto.TreeNodesDataDTO;
import com.bussinesdomain.maestros.services.ITreeComunitySubPracticaTenololgyService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreeComunitySubPracticaTenololgyServiceImpl implements ITreeComunitySubPracticaTenololgyService {
    
    private final EntityManager entityManager;
    
    @Override
    public List<TreeNodesDataDTO> getTreeComunitySubPracticaTenololgy() {
        
        String sql = "SELECT new com.bussinesdomain.maestros.dto.CommunityResultadoDTO(c.idCommunity, c.description, " +
                " sp.idSubpractica, sp.comunidadEntity.idCommunity, sp.descriptionSubpractica, t.idTechnology, t.subpracticaEntity.idSubpractica, t.name) " + 
                "FROM CommunityEntity c " + 
                "LEFT JOIN SubpracticaEntity sp on sp.comunidadEntity.idCommunity = c.idCommunity " + 
                "LEFT JOIN TechnologyEntity t  on t.subpracticaEntity.idSubpractica = sp.idSubpractica ";

        Query querySelect = entityManager.createQuery(sql);
        @SuppressWarnings("unchecked")
        List<CommunityResultadoDTO> lst = querySelect.getResultList();

        List<TreeNodesDataDTO> lstTree = this.buildTree(lst);

        return lstTree;

    }

    public List<TreeNodesDataDTO> buildTree(List<CommunityResultadoDTO> lst) {

        List<Map<String,String>> lstMap = this.getCommunities(lst);


        List<TreeNodesDataDTO> lstTreeNodesData = new ArrayList<>();

        lstMap.stream().forEach( p-> {

            
            TreeNodesDataDTO treeNodesDataDTO = TreeNodesDataDTO.builder().key(p.get("id"))
                .label( p.get("description")).data("Community")
                .icon("pi pi-fw pi-inbox")
                .build();


            List<Map<String,String>> lstSubPracticas = this.getSubpracticatesByCommunity(lst, Long.parseLong(p.get("id")));

            treeNodesDataDTO.setChildren(
                lstSubPracticas.stream().map( sp -> {
                    TreeNodesDataDTO treeNodesDataDTO2 = TreeNodesDataDTO.builder().key(sp.get("id")).label( sp.get("description")).data("subpractica")
                    .icon("pi pi-fw pi-cog").build();

                    List<Map<String,String>> lstTechnologys = this.getTechnologyBySubpractica(lst, Long.parseLong(sp.get("id")));

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

    public List<Map<String,String>> getCommunities(List<CommunityResultadoDTO> lst){
        List<Map<String,String>> lstMap = new ArrayList<>();
        

        lst.stream().map(CommunityResultadoDTO::getComunityId ) .distinct()
            .forEach( p -> {
                Map<String,String> map = new HashMap<>();        
                CommunityResultadoDTO community = lst.stream().filter( x -> x.getComunityId().equals(p)).findFirst().get();

                map.put("id", community.getComunityId().toString());
                map.put("description", community.getComunityDescription());

                lstMap.add(map);
                
            });
        
        return lstMap;

    }

    public List<Map<String,String>> getSubpracticatesByCommunity(List<CommunityResultadoDTO> lst, Long idCommunity){
        List<Map<String,String>> lstMap = new ArrayList<>();

        lst.stream().filter(p-> p.getSubPracticaCommunityId()!=null). filter( f-> f.getSubPracticaCommunityId().equals(idCommunity) ).map(CommunityResultadoDTO::getSubPracticaId) .distinct()
            .forEach( p -> {
                Map<String,String> map = new HashMap<>();        
                CommunityResultadoDTO community = lst.stream().filter( x -> x.getSubPracticaId().equals(p) && x.getSubPracticaId() != null ).findFirst().get();
                if(community.getSubPracticaId() != null){
                    map.put("id", community.getSubPracticaId().toString() );
                    map.put("description", community.getSubPracticaDescription());
                    lstMap.add(map);
                }
            });
        
        return lstMap;
    } 
    
    public List<Map<String,String>> getTechnologyBySubpractica(List<CommunityResultadoDTO> lst, Long idSubpracticate){
        List<Map<String,String>> lstMap = new ArrayList<>();

        lst.stream().filter(p-> p.getTecnologyIdForSubpractica() !=null ).filter( f -> f.getTecnologyIdForSubpractica().equals(idSubpracticate))
            .forEach( p -> {
                Map<String,String> map = new HashMap<>();        

                map.put("id", p.getTecnologyId().toString() );
                map.put("description", p.getTecnlogoryName());
                lstMap.add(map);

            });
        
        return lstMap;
    }     




}
