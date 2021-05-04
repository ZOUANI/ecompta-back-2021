package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.EtatDeclaration;
import stage.sir.gestioncomptabilite.dao.EtatDeclarationDao;

import java.util.List;

@Service
public class EtatDeclarationService {
    public EtatDeclaration findByRef(String ref) {
        return etatDeclarationDao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return etatDeclarationDao.deleteByRef(ref);
    }

    public List<EtatDeclaration> findAll() {
        return etatDeclarationDao.findAll();
    }

    @Autowired
    EtatDeclarationDao etatDeclarationDao;
}
