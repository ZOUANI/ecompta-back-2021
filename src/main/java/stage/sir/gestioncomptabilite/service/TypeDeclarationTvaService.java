package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.TypeDeclarationTva;
import stage.sir.gestioncomptabilite.dao.TypeDeclarationTvaDao;

import java.util.List;

@Service
public class TypeDeclarationTvaService {

    public TypeDeclarationTva findByRef(String ref) {
        return typeDeclarationTvaDao.findByRef(ref);
    }

    public TypeDeclarationTva findByLibelle(String libelle) {
        return typeDeclarationTvaDao.findByLibelle(libelle);
    }

    @Transactional
    public int deleteByRef(String ref) {
        int resultdeclarationtva = declarationTvaService.deleteByTypeDeclarationTvaRef(ref);
        int resulttypedeclarationtva = typeDeclarationTvaDao.deleteByRef(ref);
        return resultdeclarationtva + resulttypedeclarationtva;
    }

    public List<TypeDeclarationTva> findAll() {
        return typeDeclarationTvaDao.findAll();
    }

    public int save(TypeDeclarationTva typeDeclarationTva){
        if (findByRef(typeDeclarationTva.getRef())!=null){
            return -1;
        }else {
            typeDeclarationTvaDao.save(typeDeclarationTva);
            return 1;
        }
    }

    @Autowired
    TypeDeclarationTvaDao typeDeclarationTvaDao;
    @Autowired
    DeclarationTvaService declarationTvaService;
}
