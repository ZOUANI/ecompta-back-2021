package stage.sir.gestioncomptabilite.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable;
import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DeclarationIRDao;
import stage.sir.gestioncomptabilite.dao.DeclarationIREmployeDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.DeclarationIrVo;
import stage.sir.gestioncomptabilite.vo.DeclarationIsVo;

@Service
public class DeclarationIRService {
	@Autowired
	DeclarationIREmployeDao declarationIREmployeDao;
	@Autowired
	DeclarationIRDao declarationIRDao;
	public List<DeclarationIR> findBySocieteIceAndAnnee(String ice,Integer annee) {
		return declarationIRDao.findBySocieteIceAndAnnee(ice,annee);
	}

	@Autowired
	DeclarationIREmployeService declarationIREmployeService;

	@Autowired
	SocieteService societeService;
	@Autowired
    EntityManager entityManager;
	
	public List<DeclarationIR> search(DeclarationIrVo declarationIrVo){
        String query = "SELECT d FROM DeclarationIR d WHERE 1=1";
      /*  if(StringUtil.isNotEmpty(declarationIrVo.getRef())) {
            query+= " AND d.ref LIKE '%"+ declarationIrVo.getRef()+ "%'";
        }*/
        if(StringUtil.isNotEmpty(declarationIrVo.getAnnee())) {
            query+= " AND d.annee = '"+ declarationIrVo.getAnnee()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIrVo.getMoisMin())) {
            query+= " AND d.mois >= '"+ declarationIrVo.getMoisMin()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIrVo.getMoisMax())) {
            query+= " AND d.mois <= '"+ declarationIrVo.getMoisMax()+ "'";
        }
        return entityManager.createQuery(query).getResultList();
    }
	
	
	
	
	
	public DeclarationIR findByRef(String ref) {
		return declarationIRDao.findByRef(ref);
	}
	public DeclarationIR findByAnnee(Integer annee) {
		return declarationIRDao.findByAnnee(annee);
	}
	@Transactional
	public int deleteByMois(Integer mois) {
		return declarationIRDao.deleteByMois(mois);
	}
	
	public void prepareDeclaration(DeclarationIR declarationIR) {
		List<DeclarationIREmploye> DeclarationIREmploye = declarationIREmployeService.findByDeclarationIREmployes(declarationIR);
				
		
		declarationIR.setDeclarationsIREmployes(DeclarationIREmploye);

	}
	
	
	

	public List<DeclarationIR> findByMoisAndAnnee(Integer mois, Integer annee) {
		return declarationIRDao.findByMoisAndAnnee(mois, annee);
	}
	
	
	
	
	
	
/*	public DeclarationIR createDeclarationIr(DeclarationIR declarationIR) {
		Object[] res = save(declarationIR, true);
		if (Integer.parseInt(res[0].toString()) > 0) {
			return (DeclarationIR) res[1];
		} else {
			return null;
		}

	}*/
	@Transactional
	public int deleteByRef(String ref) {
		//List<DeclarationIREmploye> declarations=declarationIREmployeService.findByDeclarationIRRef(ref);
		int decIRsEmp=declarationIREmployeDao.deleteByDeclarationIRRef(ref);
		int decIR=declarationIRDao.deleteByRef(ref);
		
		//return declarationIRDao.deleteByRef(ref);
		return decIRsEmp + decIR;
	}





	public List<DeclarationIREmploye> createDeclarationIr(DeclarationIR declarationIR) {
		
		
		Object[] res = save(declarationIR, true,false);
		if (Integer.parseInt(res[0].toString()) > 0) {
			DeclarationIR dec=  (DeclarationIR) res[1];
			return (List<DeclarationIREmploye>) dec.getDeclarationsIREmployes();
		} else {
			return null;
		}

	}
	
	public int saveModification(DeclarationIR declarationIR) {
		Object[] saveModi = save(declarationIR, false,true);
		return 1;
	}
	
	public int save(DeclarationIR declarationIR) {
		return (Integer) save(declarationIR,false,false)[0];
	}

	private Object[] save(DeclarationIR declarationIR, boolean simuler,boolean saveModification) {
		if (declarationIRDao.findByRef(declarationIR.getRef())!=null) {
			return new Object[] { -1, null };
			
		}else {
			/*System.out.println("*****************");
			System.out.println(declarationIR);
			System.out.println(declarationIR.getSociete());*/
			//Societe societe = societeService.findByIce("2");
			
			//declarationIR.setSociete(societe);
			if (saveModification ==false) {
				
			
			prepareDeclaration(declarationIR);
			}
			double total = declarationIREmployeService.calculTotal(declarationIR.getDeclarationsIREmployes());
			declarationIR.setTotal(total);
			
			if (simuler == false) {
				declarationIRDao.save(declarationIR);
				declarationIREmployeService.save(declarationIR);
			}
			return new Object[] { 1, declarationIR };
		}
		}
	

	public List<DeclarationIR> findAll() {
		return declarationIRDao.findAll();
	}

	public Optional<DeclarationIR> findById(Long id) {
		return declarationIRDao.findById(id);
	}
	



}
