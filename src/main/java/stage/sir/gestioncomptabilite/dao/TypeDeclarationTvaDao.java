package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.TypeDeclarationTva;

@Repository
public interface TypeDeclarationTvaDao extends JpaRepository<TypeDeclarationTva, Long> {
    TypeDeclarationTva findByRef(String ref);
    int deleteByRef(String ref);

    TypeDeclarationTva findByLibelle(String libelle);
}
