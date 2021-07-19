package fachlogik;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "US")
@Table(name="u_ultraschall")
@Access(AccessType.FIELD)
public class Ultraschall extends Untersuchung
{
	@Column
	private static BigDecimal PreisProMinute = new BigDecimal("2.0");

	public Ultraschall()
	{
		super();
		this.setBezeichnung("US");
	}

	public static BigDecimal getPreisProMinute()
	{
		return PreisProMinute;
	}
}
