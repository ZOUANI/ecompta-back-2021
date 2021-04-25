package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Categorie;
import stage.sir.gestioncomptabilite.bean.Section;
import stage.sir.gestioncomptabilite.dao.CategorieDao;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    CategorieDao categorieDao;
    @Autowired
    SectionService sectionService;

    public List<Categorie> findBySectionRef(String ref) {
        return categorieDao.findBySectionRef(ref);
    }

    public Categorie findByRef(String ref) {
        return categorieDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return categorieDao.deleteByRef(ref);
    }

    public List<Categorie> findAll() {
        return categorieDao.findAll();
    }

    public int save(Categorie categorie){
        Section section = sectionService.findByRef(categorie.getSection().getRef());
        categorie.setSection(section);
        if(findByRef(categorie.getRef()) != null){
            return -1;
        }
        else if(section == null){
            return -2;
        }
        else{
            categorieDao.save(categorie);
            return 1;
        }
    }
}
