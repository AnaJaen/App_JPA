package fachlogik;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.*;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="art")
@Access(AccessType.FIELD)
@Table(name="u_untersuchung")
@NamedQuery(name="findAllForUntersuchung", query = "SELECT u FROM Untersuchung u")
/**
@NamedQueries ({
	@NamedQuery(name="findAllForUntersuchung", query = "SELECT u FROM Untersuchung u"),
	@NamedQuery(name="findUForThisYear", query = "SELECT u FROM Untersuchung u WHERE OPERATOR(('Extract', 'Current_date, '@year') - ('Extract, u.ende, '@year')) >= 18")
}) */

public class Untersuchung implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Uid")
	private int uid;
	
	@Transient
	private StringProperty bezeichnung;
	@Transient
    private ObjectProperty<LocalDateTime> beginn;
	@Transient
    private ObjectProperty<LocalDateTime> ende;
	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="untersuchung_patient") //name="class_attribut" 
	private Patient patient;
	@Transient
	private StringProperty vorname;
	@Transient
	private StringProperty nachname;
	private String kontrastmittel;
	private BigDecimal mengeKM;
	
	@ManyToMany
	@JoinTable(name="u_untersuchung_personal",
	joinColumns= {@JoinColumn(name="Uid")},
	inverseJoinColumns= {@JoinColumn(name="personal")}) 
	private List<Personal> personal;
	
	
	public Untersuchung()
	{
		beginn = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
		ende = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
		vorname = new SimpleStringProperty();
		nachname = new SimpleStringProperty();
	}

	public BigDecimal dauer() 
	{
		return new BigDecimal(Duration.between(beginn.get(), ende.get()).toMinutes());
	}

//	public LocalDateTime getBeginn()
//	{
//		return beginn.get();
//	}
//
//	public LocalDateTime getEnde()
//	{
//		return ende.get();
//	}
//
//	public void setBeginn(LocalDateTime beginn)
//	{
//		this.beginn.set(beginn);
//	}
//
//	public void setEnde(LocalDateTime ende)
//	{
//		this.ende.set(ende);
//	}

	// Implementation SQLite:
	@Access(AccessType.PROPERTY)
	public String getBeginn()
	{
		return beginn.get().toString();
	}
	@Access(AccessType.PROPERTY)
	public String getEnde()
	{
		return ende.get().toString()	;
	}
	
	public void setBeginn(String beginn)
	{
		this.beginn.set(LocalDateTime.parse(beginn, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	public void setEnde(String ende)
	{
		this.ende.set(LocalDateTime.parse(ende, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	public ObjectProperty<LocalDateTime> getBeginnProperty()
	{
		return beginn;
	}

	public ObjectProperty<LocalDateTime> getEndeProperty()
	{
		return ende;
	}

	public void setKm(String km)
	{
		this.kontrastmittel = km;
	}

	public Patient getPatient()
	{
		return patient;
	}

	public void setPatient(Patient patient)
	{
		this.patient = patient;
		vorname.set(patient.getVorname());
		nachname.set(patient.getNachname());
	}

	
	public StringProperty getVornameProperty() {
		return vorname;
	}
	
	public StringProperty getNachnameProperty() {
		return nachname;
	}

	public BigDecimal getMengeKM()
	{
		return mengeKM;
	}

	public void setMengeKM(String mengeKM)
	{
		this.mengeKM = new BigDecimal(mengeKM);
	}

	public String getKm()
	{
		return kontrastmittel;
	}
	@Access(AccessType.PROPERTY)
	public String getBezeichnung()
	{
		return bezeichnung.get();
	}

	public StringProperty getBezeichnungProperty()
	{
		return bezeichnung;
	}

	public void setBezeichnung(String name)
	{
		this.bezeichnung = new SimpleStringProperty(name);
	}
	
	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}


	public List<Personal> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Personal> personal) {
		this.personal = personal;
	}
}
