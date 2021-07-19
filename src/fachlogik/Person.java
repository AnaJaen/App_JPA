package fachlogik;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.FIELD)
@Table(name="u_person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //superclass (clase padre)
@NamedQuery(name="FindAllPersons", query="SELECT ps FROM Person ps")
public class Person implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int per_id;
	private long svnr;
    //private LocalDate geburtsdatum;
	// Implementation SQLite:
    private String geburtsdatum;
    @Transient
	private StringProperty vorname;
    @Transient
	private StringProperty nachname;
    @Enumerated(EnumType.STRING)
	private Geschlecht geschlecht;
	private String adresse;

    public Person() {
        this.vorname = new SimpleStringProperty();
        this.nachname = new SimpleStringProperty();
    }

    public Person(long svnr, String geburtsdatum, String vorname, String nachname, Geschlecht geschlecht, String adresse) {
    	this.svnr = svnr;
    	//this.geburtsdatum = LocalDate.parse(geburtsdatum, DateTimeFormatter.ISO_LOCAL_DATE);
    	// Implementation SQLite:
    	this.geburtsdatum = geburtsdatum;
        this.vorname = new SimpleStringProperty(vorname);
        this.nachname = new SimpleStringProperty(nachname);
    	this.geschlecht = geschlecht;
        this.adresse = adresse;
    }
	
	@Override
	public String toString()
	{
		return this.getNachname()+" "+this.getVorname();
	}

	public int getPer_id()
	{
		return per_id;
	}

	public void setPer_id(int per_id)
	{
		this.per_id = per_id;
	}

	public StringProperty getVornameProperty()
	{
		return vorname;
	}

	public StringProperty getNachnameProperty()
	{
		return nachname;
	}

	public long getSvnr()
	{
		return svnr;
	}

	public LocalDate getGeburtsdatum()
	{
		//return geburtsdatum;
		// Implementation SQLite:
		return LocalDate.parse(geburtsdatum, DateTimeFormatter.ISO_LOCAL_DATE);
	}

	@Access(AccessType.PROPERTY)
	public String getVorname()
	{
		return vorname.get();
	}

	@Access(AccessType.PROPERTY)
	public String getNachname()
	{
		return nachname.get();
	}

	public Geschlecht getGeschlecht()
	{
		return geschlecht;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setSvnr(long svnr)
	{
		this.svnr = svnr;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum)
	{
		//this.geburtsdatum = geburtsdatum;
		// Implementation SQLite:
		this.geburtsdatum = geburtsdatum.toString();
	}

	public void setVorname(String vorname)
	{
		this.vorname.set(vorname);
	}

	public void setNachname(String nachname)
	{
		this.nachname.set(nachname);
	}

	public void setGeschlecht(Geschlecht geschlecht)
	{
		this.geschlecht = geschlecht;
	}

	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}
}
