package main;

import java.io.IOException;
import java.time.LocalDateTime;

import fachlogik.*;
//import fachlogik.MagnetResonanz;
//import fachlogik.Patient;
//import fachlogik.Person;
//import fachlogik.Personal;
//import fachlogik.Ultraschall;
//import fachlogik.Untersuchung;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ExaminationEditDialogController;
import view.ExaminationOverviewController;
import view.PersonEditDialogController;

import persistence.*;

public class MainApp extends Application {

    public Stage primaryStage;
    private ExaminationOverviewController examinationsController;
    private ObservableList<Patient> patientData = FXCollections.observableArrayList();
    private ObservableList<Personal> personData = FXCollections.observableArrayList();
    private ObservableList<Untersuchung> examinationData = FXCollections.observableArrayList();
    private ObservableList<Personal> oldPersonData = FXCollections.observableArrayList();
    private ObservableList<Untersuchung> actualExaminationData = FXCollections.observableArrayList();

    private Connection_PersistenceManager cpm; 
    
    public Connection_PersistenceManager getCpm()
    {
    	return cpm;
    }
    
	public MainApp() {
		
		Connection_PersistenceManager cpm = new Connection_PersistenceManager();
		boolean db = false;
		
		if (db) 
		{
			Patient p1 = new Patient(1234, "1980-01-01", "Thomas", "Maier", Geschlecht.MAENNLICH, "1010 Wien, Stephansplatz 13", "WGKK");
			cpm.newPatient(p1);
			patientData.add(p1);
			Patient p2 = new Patient(4321, "1980-01-01", "Margit", "Schmidt", Geschlecht.WEIBLICH, "1050 Wien, Spengergasse 27", "BVA");
			cpm.newPatient(p2);
			patientData.add(p2);
			Patient p3 = new Patient(2345, "2015-01-01", "Cris", "Mustermann", Geschlecht.MAENNLICH, "1080 Wien, Josefstadt 3", "WGKK");
			cpm.newPatient(p3);
			patientData.add(p3);
			Patient p4 = new Patient(3456, "2007-01-01", "Mona", "Musterfrau", Geschlecht.WEIBLICH, "1230 Wien, Liesing 30", "NOKK");
			cpm.newPatient(p4);
			patientData.add(p4);
			
			
			Personal ps1 = new Personal(5678, "1980-01-01", "Hans", "Lang", Geschlecht.MAENNLICH, "1010 Wien, Stephansplatz 1", 0);
			cpm.newPerson(ps1);
			personData.add(ps1);
			Personal ps2 = new Personal(8765, "1980-01-01", "Ruth", "Kurz", Geschlecht.WEIBLICH, "1050 Wien, Spengergasse 20", 0);
			cpm.newPerson(ps2);
			personData.add(ps2);
			Personal ps3 = new Personal(9102, "1990-04-01", "Lina", "Mittel", Geschlecht.WEIBLICH, "1080, Josefstadt 1", 0);
			cpm.newPerson(ps3);
		
		
		 
		/*
		// test data
	    patientData.add(new Patient(1234, "01.01.1980", "Thomas", "Maier", Geschlecht.MAENNLICH, "1010 Wien, Stephansplatz 13", "WGKK"));
	    patientData.add(new Patient(4321, "01.01.1980", "Margit", "Schmidt", Geschlecht.WEIBLICH, "1050 Wien, Spengergasse 27", "BVA"));
	    patientData.add(new Patient(2345, "01.01.2015", "Cris", "Mustermann", Geschlecht.MAENNLICH, "1080 Wien, Josefstadt 3", "WGKK"));
	    patientData.add(new Patient(2345, "01.01.2007", "Mona", "Musterfrau", Geschlecht.WEIBLICH, "1230 Wien, Liesing 30", "NOKK"));
	    personData.add(new Personal(5678, "01.01.1980", "Hans", "Lang", Geschlecht.MAENNLICH, "1010 Wien, Stephansplatz 1", 0));
	    personData.add(new Personal(8765, "01.01.1980", "Ruth", "Kurz", Geschlecht.WEIBLICH, "1050 Wien, Spengergasse 20", 0));

		
		*/
		
	    Ultraschall u = new Ultraschall();
		u.setPatient(patientData.get(0));
		u.setBeginn(LocalDateTime.of(2017,11,21,7,15).toString());
		u.setEnde(LocalDateTime.of(2017,11,21,7,25).toString());
		cpm.newExamination(u);
		examinationData.add(u);
		
		Ultraschall u1 = new Ultraschall();
		u1.setPatient(patientData.get(3));
		u1.setBeginn(LocalDateTime.of(2019,06,21,17,15).toString());
		u1.setEnde(LocalDateTime.of(2019,06,21,17,25).toString());
		cpm.newExamination(u1);
		examinationData.add(u1);
		
		

		MagnetResonanz m1 = new MagnetResonanz();
		m1.setPatient(patientData.get(1));
		m1.setKm("Artirem Injektionslösung");
		m1.setMengeKM("3");
		m1.setBeginn(LocalDateTime.of(2017,11,21,10,23).toString());
		m1.setEnde(LocalDateTime.of(2017,11,21,10,51).toString());
		cpm.newExamination(m1);
		examinationData.add(m1);
		
		MagnetResonanz m2 = new MagnetResonanz();
		m2.setPatient(patientData.get(2));
		m2.setKm("Artirem Injektionslösung");
		m2.setMengeKM("3");
		m2.setBeginn(LocalDateTime.of(2018,10,29,17,03).toString());
		m2.setEnde(LocalDateTime.of(2018,10,29,17,23).toString());
		cpm.newExamination(m2);
		examinationData.add(m2);
		}
		else
		{
		
		patientData.addAll(cpm.allPatients());
		//personData.addAll(cpm.allPersons()); // Fehler bei der Ausführung - error al correr la aplicación. 
												//Methodeninhalt in Connection_PersistenceManager.java kommentiert -> return null
		//mögliche Lösung zum Fehler
		personData = FXCollections.observableArrayList();
		//examinationData.addAll(cpm.allExaminations());
		examinationData = FXCollections.observableArrayList();
		//oldPersonData.addAll(cpm.allOldPersons());
		oldPersonData = FXCollections.observableArrayList();
		//actualExaminationData.addAll(cpm.allActualExaminations());
		actualExaminationData = FXCollections.observableArrayList();
		
		
		}
		
		
	}
	
	public ObservableList<Patient> getPatientData() {
		
		return patientData;
	}

	public ObservableList<Personal> getPersonData() {
	    return personData;
	}

	public ObservableList<Untersuchung> getExaminationData() {
	    return examinationData;
	}
	
	public ObservableList<Personal> getOldPersondata() {
		return oldPersonData;
	}
	
	public ObservableList<Untersuchung> getActualExaminationData() {
		return actualExaminationData;
	}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UntersuchungsApp");

        showExaminationOverview();
    }

    @Override
    public void stop(){
    	this.primaryStage.close();
    }

    public void showExaminationOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ExaminationOverview.fxml"));
            TabPane examinationOverview = (TabPane) loader.load();

            examinationsController = loader.getController();
            examinationsController.setMainApp(this);

            primaryStage.setScene(new Scene(examinationOverview));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showPersonEditDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Person editieren");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showExaminationEditDialog(Untersuchung exam) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ExaminationEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Untersuchung editieren");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ExaminationEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setExamination(exam);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

	public ExaminationOverviewController getExaminationsController()
	{
		return examinationsController;
	}

	
}