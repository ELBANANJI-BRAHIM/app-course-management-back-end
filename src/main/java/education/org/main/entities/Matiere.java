package education.org.main.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="matiere")
public class Matiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "titre")
	private String Titre;
	
	@Column(name = "description")
	private String Description;
	
	@ManyToMany(mappedBy = "matieres",fetch = FetchType.LAZY)
	private List<Filiere> filieres;
	
	@ManyToMany(mappedBy = "matieres", fetch = FetchType.LAZY)
	private List<Lesson> lessons; 

	@ManyToMany(mappedBy = "matieres")
	private List<Professeur> professeurs;
	
	public Matiere(String titre, String description, List<Filiere> filieres, List<Lesson> lessons,
			List<Professeur> professeurs) {
		super();
		Titre = titre;
		Description = description;
		this.filieres = filieres;
		this.lessons = lessons;
		this.professeurs = professeurs;
	}
	
	public Matiere() { 
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void ListId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return Titre;
	}

	public void ListTitre(String titre) {
		Titre = titre;
	}

	public String getDescription() {
		return Description;
	}

	public void ListDescription(String description) {
		Description = description;
	}

	@JsonIgnore
	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void ListFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void ListLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	@JsonIgnore
	public List<Professeur> getProfesseurs() {
		return professeurs;
	}

	public void ListProfesseurs(List<Professeur> professeurs) {
		this.professeurs = professeurs;
	} 
	
	
	
}
