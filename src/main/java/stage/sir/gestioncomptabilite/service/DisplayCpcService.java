package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.dao.FactureDao;
import stage.sir.gestioncomptabilite.vo.CalCpcVo;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class DisplayCpcService extends AbstractFacade<Facture>{


    @Autowired
    private FactureDao factureDao;
    @Autowired
    protected EntityManager entityManager;

    public List<Facture> bringFact(String type, CalCpcVo calCpcVo) {
        String query= "select f from Facture f where f.compteComptable.sousClasseComptable.libelle='"+type+"'";
        if (calCpcVo.getDatemin()!=null && calCpcVo.getDatemax()!=null){
            query+=addConstraintMinMaxDate("f","dateOperation",calCpcVo.getDatemin(),calCpcVo.getDatemax());

        }
        return entityManager.createQuery(query).getResultList();
    }
    public List<Facture> saerchCpc(CalCpcVo calCpcVo, String operation){
        String query= "select f from Facture f where f.compteComptable.sousClasseComptable.libelle='"+operation+"'";
        if (calCpcVo.getDatemin()!=null && calCpcVo.getDatemax()!=null){
            query+=addConstraintMinMaxDate("f","dateOperation",calCpcVo.getDatemin(),calCpcVo.getDatemax());

        }
        return entityManager.createQuery(query).getResultList();
    }
    public Double calculEx(CalCpcVo calCpcVo, String operation){
        List<Facture> lst= this.saerchCpc(calCpcVo,operation);
        Double sum=0.0;
        for (Facture fact: lst) {
            sum+=fact.getMontantHorsTaxe();
        }
        return sum;
    }

}
