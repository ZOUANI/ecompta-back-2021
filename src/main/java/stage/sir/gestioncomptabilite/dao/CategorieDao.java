package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Categorie;

import java.util.List;
@Repository
public interface CategorieDao extends JpaRepository<Categorie,Long > {
    List<Categorie> findAll();
    List<Categorie> findBySectionRef(String ref);
    Categorie findByRef(String ref);
    int deleteByRef(String ref);
}
