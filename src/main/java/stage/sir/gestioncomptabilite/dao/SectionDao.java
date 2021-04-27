package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Section;

import java.util.List;
@Repository
public interface SectionDao extends JpaRepository<Section,Long > {
    List<Section> findAll();
    List<Section> findByClassComptableRef(String ref);
    Section findByRef(String ref);
    int deleteByRef(String ref);
}
