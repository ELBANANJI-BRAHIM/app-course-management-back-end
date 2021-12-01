package education.org.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import education.org.main.dao.UtilisateurRepository;
import education.org.main.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import education.org.main.dao.FiliereRepository;
import education.org.main.dao.MatiereRepository;
import education.org.main.dao.NiveauScolaireRepository;
import education.org.main.dao.PromotionRepository;
import education.org.main.services.EtudiantService;
import education.org.main.services.FiliereService;
import education.org.main.services.RoleService;
import education.org.main.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@EnableConfigurationProperties(EducationMainApplication.PrefixlessProperties.class)
@SpringBootApplication
public class EducationMainApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;
	 
	@Autowired
	PromotionRepository promotionRepository;

	@Autowired
	FiliereRepository filiereRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	FiliereService filiereService;
	
	@Autowired
	NiveauScolaireRepository niveauScolaireRepository;
	
	@Autowired
	MatiereRepository matiereRepository;

	public static void main(String[] args) {
		SpringApplication.run(EducationMainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//-------------------------------------------------------------/ Entities /------------------------------------------------------------------------------------------------------
		
		/*
		 * 
		 * 
		
		//############################## Rules ################################################################################
		Role USER = new Role("ROLE_USER", "can read course");
		Role ADMIN_MANAGER = new Role("ROLE_ADMIN_MANAGER", "can manage operation");
		Role ADMIN_PROFFESSEUR = new Role("ROLE_ADMIN_PROFFESSEUR", "can manage courses");
		Role ADMIN_GENERAL = new Role("ROLE_ADMIN_GENERAL", "manage all application");
		Role ANONYMOUS = new Role("ROLE_ANONYMOUS", "can only acces to landin page");
		Role CUSTOMER_USER = new Role("ROLE_CUSTOMER_USER", "private access");
					// save roles
					roleService.save(USER);
					roleService.save(ADMIN_MANAGER);
					roleService.save(ADMIN_PROFFESSEUR);
					roleService.save(ADMIN_GENERAL);
					roleService.save(ANONYMOUS);
					roleService.save(CUSTOMER_USER);
					
		//############################## Utilisateur ################################################################################
		Utilisateur BrahimUser = new Utilisateur("Brahim","1234", 1,new ArrayList<>() ,"elbanaji.brahim@gmail.com", "0661123254");
		Utilisateur HamzaUser = new Utilisateur("Hamza","1234", 1,new ArrayList<>() ,"hamza.galouchhe@gmail.com", "0645121254");
		Utilisateur YassinUser = new Utilisateur("Yassine","1234", 1,new ArrayList<>() ,"yassine.gallouche@gmail.com", "0660324324");
					// save roles
					utilisateurService.save(BrahimUser);
					utilisateurService.save(HamzaUser);
					utilisateurService.save(YassinUser);
		
		//############################## Etudiant ################################################################################
		Etudiant Brahim = new Etudiant("EL BANAJI", "Brahim",new Utilisateur(), new ArrayList<>(), new ArrayList<>());
		Etudiant Hamza = new Etudiant("EL BANAJI", "Hamza",new Utilisateur(), new ArrayList<>(), new ArrayList<>());
		Etudiant yassin = new Etudiant("EL BANAJI", "yassin",new Utilisateur(), new ArrayList<>(), new ArrayList<>());
					//save etudiants
					etudiantService.save(Brahim);
					etudiantService.save(Hamza);
					etudiantService.save(yassin);
					
		//############################## Filieres ########################################
		Filiere science_physique = new Filiere("sience physique", "tous les cours filiere Science physique[pc, math, svt]", new ArrayList<>(), new ArrayList<>());
		Filiere science_svt = new Filiere("sience de la vie et terre", "tous les cours du filiere Science SVT[pc, math, svt]", new ArrayList<>(), new ArrayList<>() );
		Filiere science_economique = new Filiere("Science Economie", "tous les cours du filiere economique[eco, pc, math, svt]", new ArrayList<>(), new ArrayList<>());
		Filiere science_math = new Filiere("sience Math", "tous les cours du filiere science math [pc, math avancer, svt]", new ArrayList<>(), new ArrayList<>());
		
					//Save Filiere
					filiereRepository.save(science_physique);
					filiereRepository.save(science_svt);
					filiereRepository.save(science_economique);
					filiereRepository.save(science_math);
					
		//############################## Metiere ########################################
		Matiere MATH = new Matiere("MATH", "tous les lessons du matiere mathematique ...", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		Matiere PHYSIQUE = new Matiere("PHYSIQUE", "tous les lessons du matiere Physique ...", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		Matiere SVT = new Matiere("SVT", "tous les lessons du matiere svt ...", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		Matiere ECONOMIE = new Matiere("SVT", "tous les lessons du matiere svt ...", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
					//sace Matiere 
					matiereRepository.save(MATH);
					matiereRepository.save(PHYSIQUE);
					matiereRepository.save(SVT);
					matiereRepository.save(ECONOMIE);
					
		//############################## NiveauScolaire ########################################
		NiveauScolaire Bac_2 = new NiveauScolaire("2° Bacalauriat", "the last years in lycee hign scool ...", new ArrayList<>());
		NiveauScolaire Bac_1 = new NiveauScolaire("1° Bacalauriat", "the last years in lycee hign scool ...", new ArrayList<>());
					//Save 
					niveauScolaireRepository.save(Bac_2);
					niveauScolaireRepository.save(Bac_1);
					
//-------------------------------------------------------------/ Mapping Relationship /------------------------------------------------------------------------------------------------------

		//############################## Promotion -> Etudiant ################################################################################
		LocalDate today= LocalDate.now();
		LocalDate plus90days = today.plusDays(90);
		Promotion Semestre1 = new Promotion("1° Semestre" ,"3 mois " , today, plus90days, 300.0, new ArrayList<>());
		Promotion Semestre2 = new Promotion("2° Semestre" ,"6 mois " , today, plus90days, 600.0, new ArrayList<>());
					//Save Promotion
					promotionRepository.save(Semestre1);
					promotionRepository.save(Semestre2);

		//############################## Role->Utlisateur ################################################################################
		utilisateurService.addRoleToUser(BrahimUser, USER);
		utilisateurService.addRoleToUser(HamzaUser, ADMIN_GENERAL);
		utilisateurService.addRoleToUser(HamzaUser, ADMIN_MANAGER);
		utilisateurService.addRoleToUser(HamzaUser, ADMIN_PROFFESSEUR);
		utilisateurService.addRoleToUser(HamzaUser, USER);
		utilisateurService.addRoleToUser(YassinUser, USER);
		
		//################################# User -> Etudiant ###########################################################
		etudiantService.addUserAssociationToEtudiant(Brahim, BrahimUser);
		etudiantService.addUserAssociationToEtudiant(Hamza, HamzaUser);
		etudiantService.addUserAssociationToEtudiant(yassin, YassinUser);
		
		//################################# Promotion -> Etudiant ###########################################################
					etudiantService.addPromotionToEtudiant(Brahim, Semestre1); etudiantService.addPromotionToEtudiant(Brahim, Semestre2);
					etudiantService.addPromotionToEtudiant(Hamza,Semestre1);
					etudiantService.addPromotionToEtudiant(yassin, Semestre2);
					
		//################################# Niveau Scolaire -> Etudiant ###########################################################
					etudiantService.addNiveauScolaireToEtudiant(Brahim, Bac_2);
					etudiantService.addNiveauScolaireToEtudiant(Hamza, Bac_2);
					etudiantService.addNiveauScolaireToEtudiant(Hamza, Bac_1);
					etudiantService.addNiveauScolaireToEtudiant(yassin, Bac_1);
					etudiantService.addNiveauScolaireToEtudiant(yassin, Bac_2);

		//############################## Nivea Scolaire -> Filiere ########################################
		filiereService.addNiveauScolaireToFiliere(science_math, Bac_1);
		filiereService.addNiveauScolaireToFiliere(science_physique, Bac_1);
		filiereService.addNiveauScolaireToFiliere(science_economique, Bac_1);
		filiereService.addNiveauScolaireToFiliere(science_svt, Bac_1);

		filiereService.addNiveauScolaireToFiliere(science_math, Bac_2);
		filiereService.addNiveauScolaireToFiliere(science_physique, Bac_2);
		filiereService.addNiveauScolaireToFiliere(science_economique, Bac_2);
		filiereService.addNiveauScolaireToFiliere(science_svt, Bac_2);
		
		//############################## Metiere -> Filiere ########################################
		filiereService.addMatiereToFiliere(science_physique, PHYSIQUE);
		filiereService.addMatiereToFiliere(science_physique, MATH);
		filiereService.addMatiereToFiliere(science_physique, SVT);
		
		filiereService.addMatiereToFiliere(science_svt, PHYSIQUE); 
		filiereService.addMatiereToFiliere(science_svt, MATH);
		filiereService.addMatiereToFiliere(science_svt, SVT);
		
		filiereService.addMatiereToFiliere(science_economique, ECONOMIE);
		filiereService.addMatiereToFiliere(science_economique, PHYSIQUE);
		filiereService.addMatiereToFiliere(science_economique, MATH);
		filiereService.addMatiereToFiliere(science_economique, SVT);
		
		filiereService.addMatiereToFiliere(science_math, PHYSIQUE);
		filiereService.addMatiereToFiliere(science_math, MATH);
		filiereService.addMatiereToFiliere(science_math, SVT);
		
		*
		*
		*/
		
	}
}


