package fachlogik;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "RT")
@Table(name="u_roentgen")
public class Roentgen extends Untersuchung
{
	@Column
	private static BigDecimal PreisProMinute = new BigDecimal("2.0");
	@Column
	private static BigDecimal PreisProBild = new BigDecimal("5.0");
	private int anzahlBilder;

	public Roentgen()
	{
		super();
		this.setBezeichnung("RT");
	}

	public int getAnzahlBilder()
	{
		return anzahlBilder;
	}

	public void setAnzahlBilder(int anzahlBilder)
	{
		this.anzahlBilder = anzahlBilder;
	}

	public static BigDecimal getPreisProMinute()
	{
		return PreisProMinute;
	}

	public static BigDecimal getPreisProBild()
	{
		return PreisProBild;
	}
}
