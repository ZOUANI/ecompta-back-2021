package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Cpc;
import stage.sir.gestioncomptabilite.bean.CpcFacture;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.dao.CpcDao;
import stage.sir.gestioncomptabilite.dao.CpcFactureDao;
import stage.sir.gestioncomptabilite.dao.FactureDao;
import stage.sir.gestioncomptabilite.vo.CpcVo;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CpcService extends AbstractFacade<Facture>{
    @Autowired
    EntityManager entityManager;
    @Autowired
    CpcDao cpcDao;
    @Autowired
    FactureService factureService;
    @Autowired
    FactureDao factureDao;
    @Autowired
    CpcFactureService cpcFactureService;
    public List<Cpc> findAll() {
        return cpcDao.findAll();
    }


   public List<Cpc> findCpc(CpcVo cpcVo){
        String query= "SELECT  c FROM Cpc c where 1=1 ";
        if (cpcVo.getTotalProdExVo()!=null && !cpcVo.getTotalProdExVo().isNaN()){
            query+=" and c.totalProdEx= '"+ cpcVo.getTotalProdExVo()+"'";
        }
        if (cpcVo.getDateMinVo() !=null && cpcVo.getDateMaxVo()!=null){
            query+=addConstraintMinMaxDate("c","dateMinCpc",cpcVo.getDateMinVo(),cpcVo.getDateMaxVo());
            query+= addConstraintMinMaxDate("c","dateMaxCpc",cpcVo.getDateMinVo(),cpcVo.getDateMaxVo());
        }
       if (cpcVo.getTotalChargExVo()!=null && !cpcVo.getTotalChargExVo().isNaN()){
           query+=" and c.totalChargEx= '"+ cpcVo.getTotalChargExVo()+"'";
       }
       if (cpcVo.getTotalChargFinVo()!=null && !cpcVo.getTotalChargFinVo().isNaN()){
           query+=" and c.totalChargFin= '"+ cpcVo.getTotalChargFinVo()+"'";
       }
       if (cpcVo.getTotalProdFinVo()!=null && !cpcVo.getTotalProdFinVo().isNaN()){
           query+=" and c.totalProdFin= '"+ cpcVo.getTotalProdFinVo()+"'";
       }
       if (cpcVo.getTotalProdNCourVo()!=null && !cpcVo.getTotalProdNCourVo().isNaN()){
           query+=" and c.totalProdNCour= '"+ cpcVo.getTotalProdNCourVo()+"'";
       }
       if (cpcVo.getTotalChargNCourVo()!=null && !cpcVo.getTotalChargNCourVo().isNaN()){
           query+=" and c.totalChargNCour= '"+ cpcVo.getTotalChargNCourVo()+"'";
       }
return entityManager.createQuery(query).getResultList();
    }
    public int save(Cpc cpc){
        this.cpcDao.save(cpc);
       List<CpcFacture> cpcFactures = cpc.getCpcFactures();
       if(cpcFactures!=null){
        for (CpcFacture cpcFacture: cpcFactures) {
            this.factureDao.save(cpcFacture.getFacture());
            cpcFacture.setCpc(cpc);
            this.cpcFactureService.save(cpcFacture);
        }}
        return 1;
    }
    @Transactional
    public int delete(Long id){
        int a;
        a =this.cpcFactureService.deleteCpcFacturesByCpcId(id);
          this.cpcDao.deleteById(id);
          return a;
    }
}
