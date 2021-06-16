package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.bean.Cpc;

import java.util.List;

@Repository
public interface CpcDao extends JpaRepository<Cpc,Long > {
    void deleteById(Long id);
}
