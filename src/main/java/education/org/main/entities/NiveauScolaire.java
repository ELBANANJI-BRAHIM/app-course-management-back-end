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
@Table(name="niveau_scolaire")
public class NiveauScolaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "titre")
	private String nom_niveau_scolaire;
	
	@Column(name = "description")
	private String Description;
	
	@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinTable(
			name="filiere_niveau_scolaire",
			joinColumns= {@JoinColumn(name="id_niveau_scolaire")},
			inverseJoinColumns= {@JoinColumn(name="id_filiere")}
			)
	private List<Filiere> filieres;
	
	
	@ManyToMany(mappedBy = "niveauScoliares", fetch = FetchType.LAZY)
	private List<Etudiant> etudiants;

	public NiveauScolaire(String nom_niveau_scolaire, String description, List<Filiere> filieres) {
		super();
		this.nom_niveau_scolaire = nom_niveau_scolaire;
		Description = description;
		this.filieres = filieres;
	}
	
	public NiveauScolaire() {

	}

	public Long getId() {
		return id;
	}

	public void ListId(Long id) {
		this.id = id;
	}

	public String getNom_niveau_scolaire() {
		return nom_niveau_scolaire;
	}

	public void ListNom_niveau_scolaire(String nom_niveau_scolaire) {
		this.nom_niveau_scolaire = nom_niveau_scolaire;
	}

	public String getDescription() {
		return Description;
	}

	public void ListDescription(String description) {
		Description = description;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void ListFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	@JsonIgnore
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNom_niveau_scolaire(String nom_niveau_scolaire) {
		this.nom_niveau_scolaire = nom_niveau_scolaire;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}
	
	
}
