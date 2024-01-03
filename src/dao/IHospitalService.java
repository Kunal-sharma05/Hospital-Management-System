package dao;

import java.util.List;
import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;

public interface IHospitalService {
	  Appointment getAppointmentById(int appointmentId);

	  List<Appointment> getAppointmentsForPatient(int patientId)throws PatientNumberNotFoundException;

	  List<Appointment> getAppointmentsForDoctor(int doctorId);

	  boolean scheduleAppointment(Appointment appointment);

	  boolean updateAppointment(Appointment appointment);
	  
	  boolean cancelAppointment(int appointmentId);

}
