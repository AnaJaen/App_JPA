package fachlogik;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;


@Entity
@DiscriminatorValue(value = "CT" ) //Especifica el modo en que la identidad se puede inicializar 
									//atributo como automática, manual o valor tomado de la tabla de secuencias.
@Access(AccessType.FIELD)
@Table(name="u_computerTomographie")
public class ComputerTomographie extends Untersuchung
{
	@Column
	private static BigDecimal PreisProMinute = new BigDecimal("4.0");
	@Column
	private static BigDecimal PreisProSerie = new BigDecimal("2.0");
	private int anzahlSerien;

	public ComputerTomographie()
	{
		super();
		this.setBezeichnung("CT");
	}
	
	public int getAnzahlSerien()
	{
		return anzahlSerien;
	}

	public void setAnzahlSerien(int anzahlSerien)
	{
		this.anzahlSerien = anzahlSerien;
	}

	public static BigDecimal getPreisProMinute()
	{
		return PreisProMinute;
	}

	public static BigDecimal getPreisProSerie()
	{
		return PreisProSerie;
	}
}
