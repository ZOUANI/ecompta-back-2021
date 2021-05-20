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
        int res1 = etapeService.deleteByTypeOperationLibelle(libelle);
        int res2 = typeOperationDao.deleteByLibelle(libelle);
        return res1 + res2;
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
