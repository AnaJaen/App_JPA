package fachlogik;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "EN")
@Table(name="u_endoskopie")
@Access(AccessType.FIELD)
public class Endoskopie extends Untersuchung
{
	@Column
	private static BigDecimal PreisProMinute = new BigDecimal("3.0");
	@Column
	private static BigDecimal Fixkosten = new BigDecimal("100.0");
	
	public Endoskopie()
	{
		super();
		this.setBezeichnung("EN");
	}
	
	public static BigDecimal getPreisProMinute()
	{
		return PreisProMinute;
	}
	
	public static BigDecimal getFixkosten()
	{
		return Fixkosten;
	}
}
