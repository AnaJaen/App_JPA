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
@DiscriminatorValue(value="MR")
@Table(name="u_magnetResonanz")
@Access(AccessType.FIELD)
public class MagnetResonanz extends Untersuchung
{
	@Column
	private static BigDecimal PreisProMinute = new BigDecimal("7.0");
	@Column
	private static BigDecimal PreisProSerie = new BigDecimal("2.0");
	@Column
	private static BigDecimal PreisNachbearbeitung = new BigDecimal("2.0");
	private int anzahlSerien;
	private int dauerNachbearbeitung;

	public MagnetResonanz()
	{
		super();
		this.setBezeichnung("MR");
	}
	
	public int getAnzahlSerien()
	{
		return anzahlSerien;
	}

	public void setAnzahlSerien(int anzahlSerien)
	{
		this.anzahlSerien = anzahlSerien;
	}

	public int getDauerNachbearbeitung()
	{
		return dauerNachbearbeitung;
	}

	public void setDauerNachbearbeitung(int dauerNachbearbeitung)
	{
		this.dauerNachbearbeitung = dauerNachbearbeitung;
	}

	public static BigDecimal getPreisProMinute()
	{
		return PreisProMinute;
	}

	public static BigDecimal getPreisProSerie()
	{
		return PreisProSerie;
	}

	public static BigDecimal getPreisNachbearbeitung()
	{
		return PreisNachbearbeitung;
	}
}
