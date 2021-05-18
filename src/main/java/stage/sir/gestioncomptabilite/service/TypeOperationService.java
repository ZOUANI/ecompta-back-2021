package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.TypeOperation;
import stage.sir.gestioncomptabilite.dao.TypeOperationDao;

import java.util.List;

@Service
public class TypeOperationService {
    @Autowired
    private TypeOperationDao typeOperationDao;
    @Autowired
    private EtapeService etapeService;

    public TypeOperation findByLibelle(String libelle) {
        return typeOperationDao.findByLibelle(libelle);
    }
    @Transactional
    public int deleteByLibelle(String libelle) {
        return typeOperationDao.deleteByLibelle(libelle);
    }

    public List<TypeOperation> findAll() {
        return typeOperationDao.findAll();
    }

    public int save(TypeOperation typeOperation){
        if (typeOperationDao.findByLibelle(typeOperation.getLibelle()) != null) return -1;
        typeOperationDao.save(typeOperation);
        etapeService.save(typeOperation,typeOperation.getEtapes());

        return 1;
    }
}
