package fachlogik;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "PT")
@Table(name="u_patient")
@NamedQuery(name ="Patient.findAllForPatient", query="SELECT pt FROM Patient pt")
public class Patient extends Person
{
	private String krankenkasse;
	
	@OneToMany (mappedBy = "patient") //bi-directional many-to-one association to Untersuchung
	private List<Untersuchung> untersuchungen;


	public Patient() {
        super();
    }

    public Patient(long svnr, String geburtsdatum, String vorname, String nachname, Geschlecht geschlecht, String adresse, String krankenkasse) {
    	super(svnr, geburtsdatum, vorname, nachname, geschlecht, adresse);
    	this.krankenkasse = krankenkasse;
    }
	
	public String getKrankenkasse()
	{
		return krankenkasse;
	}

	public void setKrankenkasse(String krankenkasse)
	{
		this.krankenkasse = krankenkasse;
	}
	
	@Override
	public String toString() {
		return this.getNachname()+" "+this.getVorname();
	}
}
