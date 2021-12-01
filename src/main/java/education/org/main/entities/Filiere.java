package education.org.main.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="filiere")
public class Filiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "titre") 
	private String titre;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinTable(
		        name = "associer_filiere_matiere", 
		        joinColumns = {@JoinColumn(name="id_filiere")}, 
		        inverseJoinColumns = {@JoinColumn(name="id_matiere")}
		    )
	private List<Matiere> matieres;
	
	
	@ManyToMany(mappedBy = "filieres", fetch = FetchType.LAZY)
	private List<NiveauScolaire> niveauScolaires;

	public Filiere(String titre, String description, List<Matiere> matieres,
			List<NiveauScolaire> niveauScolaires) {
		super();
		this.titre = titre;
		this.description = description; 
		this.matieres = matieres;
		this.niveauScolaires = niveauScolaires;
	} 
	
	public Filiere() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Matiere> getMatieres() {
		return matieres; 
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	@JsonIgnore
	public List<NiveauScolaire> getNiveauScolaires() {
		return niveauScolaires;
	}

	public void setNiveauScolaires(List<NiveauScolaire> niveauScolaires) {
		this.niveauScolaires = niveauScolaires;
	}

	} 
