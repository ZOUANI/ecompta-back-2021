package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.Categorie;
import stage.sir.gestioncomptabilite.dao.CategorieDao;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    CategorieDao categorieDao;

    public List<Categorie> findBySectionRef(String ref) {
        return categorieDao.findBySectionRef(ref);
    }

    public List<Categorie> findAll() {
        return categorieDao.findAll();
    }
}
