package education.org.main.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import education.org.main.dao.FiliereRepository;
import education.org.main.dao.MatiereRepository;
import education.org.main.dao.NiveauScolaireRepository;
import education.org.main.entities.Filiere;
import education.org.main.entities.Matiere;
import education.org.main.entities.NiveauScolaire;

@Service
public class FiliereService {
	
	@Autowired
	private FiliereRepository filiereRepository;
	
	@Autowired
	private MatiereRepository matiereRepository;
	
	@Autowired
	NiveauScolaireRepository niveauScolaireRepository;
	
	@Transactional
	public void addMatiereToFiliere(Filiere filiere, Matiere matiere)
	{
		Filiere filiereDAO = filiereRepository.findById(filiere.getId()).get();
		Matiere matiereDAO = matiereRepository.findById(matiere.getId()).get();
		if(filiereDAO != null && matiereDAO != null)
		{
			filiereDAO.getMatieres().add(matiereDAO);
			matiereDAO.getFilieres().add(filiereDAO);
			
		}else {throw new UsernameNotFoundException("filiere or matiere or both are null ...");}
	}
	
	@Transactional
	public void removeMatiereToFiliere(Filiere filiere, Matiere matiere)
	{
		Filiere filiereDAO = filiereRepository.findById(filiere.getId()).get();
		Matiere matiereDAO = matiereRepository.findById(matiere.getId()).get();
		if(filiereDAO != null && matiereDAO != null)
		{
			filiereDAO.getMatieres().remove(matiereDAO);
			matiereDAO.getFilieres().remove(filiereDAO);
			
		}else {throw new UsernameNotFoundException("filiere or matiere or both are null ...");}
	}
	
	// Mapping Filiere -> Niveau Scolaire
	
	@Transactional
	public void addNiveauScolaireToFiliere(Filiere filiere, NiveauScolaire niveauScolaire)
	{
		Filiere filiereDAO = filiereRepository.findById(filiere.getId()).get();
		NiveauScolaire niveauScolaireDAO = niveauScolaireRepository.findById(niveauScolaire.getId()).get();
		if(filiereDAO != null && niveauScolaireDAO != null)
		{
			filiereDAO.getNiveauScolaires().add(niveauScolaireDAO);
			niveauScolaireDAO.getFilieres().add(filiereDAO);
			
		}else {throw new UsernameNotFoundException("filiere or matiere or both are null ...");}
	}
	 
	@Transactional 
	public void removeNiveauScolaireToFiliere(Filiere filiere, NiveauScolaire niveauScolaire)
	{
		Filiere filiereDAO = filiereRepository.findById(filiere.getId()).get();
		NiveauScolaire niveauScolaireDAO = niveauScolaireRepository.findById(niveauScolaire.getId()).get();
		if(filiereDAO != null && niveauScolaireDAO != null)
		{
			filiereDAO.getNiveauScolaires().remove(niveauScolaireDAO);
			niveauScolaireDAO.getFilieres().remove(filiereDAO);
			
		}else {throw new UsernameNotFoundException("Filiere or NiveauScolaires or both are null ...");}
	}
	 
	public  Filiere save(Filiere Filiere) {
		return filiereRepository.save(Filiere);
	}
	 
	public Filiere findById(Long id) {
		return filiereRepository.findById(id).get();
	}
	 
	public void deleteById(Long id) {
		filiereRepository.deleteById(id);
		
	}
	 
	public void delete(Filiere Filiere) {
		filiereRepository.delete(Filiere);
		
	}
	 
	public void deleteAllById(Iterable<? extends Long> ids) {
		filiereRepository.deleteAllById(ids);
	}
	 	 
	public void deleteAll() {
		filiereRepository.deleteAll();			
	}
	 
	public List<Filiere> findAll() {
		
		return filiereRepository.findAll();
	}

	public List<Filiere> findAll(Sort sort) {
		return filiereRepository.findAll();
	} 
	
}
