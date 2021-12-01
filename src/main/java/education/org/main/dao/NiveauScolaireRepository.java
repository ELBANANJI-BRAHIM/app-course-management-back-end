package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import education.org.main.entities.NiveauScolaire;

@Repository
public interface NiveauScolaireRepository extends JpaRepository<NiveauScolaire, Long> {

}
