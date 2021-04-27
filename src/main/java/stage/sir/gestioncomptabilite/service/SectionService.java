package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Section;
import stage.sir.gestioncomptabilite.dao.SectionDao;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    SectionDao sectionDao;

    public List<Section> findByClassComptableRef(String ref) {
        return sectionDao.findByClassComptableRef(ref);
    }

    public Section findByRef(String ref) {
        return sectionDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return sectionDao.deleteByRef(ref);
    }

    public List<Section> findAll() {
        return sectionDao.findAll();
    }
}
