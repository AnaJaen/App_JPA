package fachlogik;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
//import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "PS")
@Access(AccessType.FIELD)
@Table(name="u_personal")
@NamedQuery(name ="findAllForPersonal", query = "SELECT ps FROM Personal ps")
/**@NamedQueries({
	NamedQuery(name ="findAllForPersonal", query = "SELECT ps FROM Personal ps"),
	@NamedQuery(name ="findAllOlder18ForPersonal", query = "SELECT ps FROM Personal ps where (Current_date - :ps.geburtsdatum) >= 18")
})*/
public class Personal extends Person
{
	private long personalnummer;
	
	@ManyToMany	(mappedBy="personal") //bi-directional many-to-many association to Untersuchung
	private List<Untersuchung> untersuchungen;
	
    public Personal() {
        super();
    }

    public Personal(long svnr, String geburtsdatum, String vorname, String nachname, Geschlecht geschlecht, String adresse, long personalnummer) {
    	super(svnr, geburtsdatum, vorname, nachname, geschlecht, adresse);
    	this.personalnummer = personalnummer;
    }
	
	public long getPersonalnummer()
	{
		return personalnummer;
	}

	public void setPersonalnummer(long personalnummer)
	{
		this.personalnummer = personalnummer;
	}
	
}
