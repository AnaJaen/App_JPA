package persistence;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import java.sql.*; 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fachlogik.Patient;
import fachlogik.Personal;
import fachlogik.Untersuchung;


public class Connection_PersistenceManager implements IExaminationDao
{
	private Connection con;
	private final static Logger LOGGER = Logger.getLogger(Connection_PersistenceManager.class.getName());
	
	//Verbindung mit der DB
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	private EntityManager em = emf.createEntityManager();
	

	@Override
	public void close() throws IOException
	{
		
	}

	@Override
	public List<Untersuchung> allExaminations()
	{
		
		TypedQuery<Untersuchung> query = em.createNamedQuery("Untersuchung.findAllForUntersuchung", Untersuchung.class);
		return query.getResultList();
		//return null;
	}

	@Override
	public List<Untersuchung> allActualExaminations()
	{
		/**
		TypedQuery<Untersuchung> query = em.createNamedQuery("Untersuchung.findUForThisYear", Untersuchung.class);
		return query.getResultList();
		*/
		return null;
	}

	@Override
	public void newExamination(Untersuchung u)
	{
		//Begin the transaction.
		em.getTransaction().begin();
		// Add a new Untersuchung
		em.persist(u);
		//update the collection in memory of Untersuchungen
		em.getTransaction().commit();
	}

	@Override
	public void editExamination(Untersuchung u)
	{
		//Begin the transaction. 
		em.getTransaction().begin();
		// Add a new Untersuchung
		em.persist(u);
		// Commit and update the collection in memory of Untersuchungen
		em.getTransaction().commit();
		
	}

	@Override
	public void deleteExamination(Untersuchung u)
	{
		// Delete a new Untersuchung
		//update the collection in memory of Untersuchungen
		em.getTransaction().begin();
		em.remove(em.find(Untersuchung.class, u.getUid())); //1. die untersuchung suchen; 2. Die Untersuchung löschen
		em.getTransaction().commit();
		
	}

	@Override
	public List<Patient> allPatients()
	{
		TypedQuery<Patient> query = em.createNamedQuery("Patient.findAllForPatient", Patient.class);
		return query.getResultList();
	}

	@Override
	public void newPatient(Patient p)
	{
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}

	@Override
	public void editPatient(Patient p)
	{
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}

	@Override
	public void deletePatient(Patient p)
	{
		em.getTransaction().begin();
		em.remove(em.find(Patient.class, p.getPer_id()));
		em.getTransaction().commit();
		
	}

	
	@Override
	public List<Personal> allPersons()
	{
		TypedQuery<Personal> query = em.createNamedQuery("Personal.findAllForPersonal", Personal.class);
		return query.getResultList() ;
	 	//error al correr la aplicación junto con MainApp.java:130
		 
		//return null;
	}
	
	@Override
	public List<Personal> allOldPersons()
	{
		/**
		TypedQuery<Personal> query = em.createNamedQuery("Personal.findAllOlder18ForPersonal", Personal.class);
		return query.getResultList();
		*/
		return null;
	}

	@Override
	public void newPerson(Personal p)
	{
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}

	@Override
	public void editPerson(Personal p)
	{
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}

	@Override
	public void deletePerson(Personal p)
	{
		em.getTransaction().begin();
		em.remove(em.find(Personal.class, p.getPer_id()));
		em.getTransaction().commit();
		
	}

	

	
}
