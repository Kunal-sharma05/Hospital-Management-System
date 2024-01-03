package mainmod;
import dao.*;
import entity.*;
import myexceptions.PatientNumberNotFoundException;
import util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class MainModule {

    public static void main(String[] args) {
        IHospitalService hospitalService = new HospitalServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== Hospital Management System ====");
            System.out.println("1. Get Appointment by ID");
            System.out.println("2. Get Appointments for Patient");
            System.out.println("3. Get Appointments for Doctor");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    getAppointmentById(hospitalService, scanner);
                    break;
                case 2:
                    getAppointmentsForPatient(hospitalService, scanner);
                    break;
                case 3:
                    getAppointmentsForDoctor(hospitalService, scanner);
                    break;
                case 4:
                    scheduleAppointment(hospitalService, scanner);
                    break;
                case 5:
                    updateAppointment(hospitalService, scanner);
                    break;
                case 6:
                    cancelAppointment(hospitalService, scanner);
                    break;
                case 0:
                    System.out.println("Exiting the Hospital Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void getAppointmentById(IHospitalService hospitalService, Scanner scanner) {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        Appointment appointment = hospitalService.getAppointmentById(appointmentId);
    
        if (appointment != null) {
            System.out.println("Appointment details: " + appointment);
        } else {
            System.out.println("Appointment not found!");
        }
    }

    private static void getAppointmentsForPatient(IHospitalService hospitalService, Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        List<Appointment> patientAppointments;
		try {
			patientAppointments = hospitalService.getAppointmentsForPatient(patientId);
			if (!patientAppointments.isEmpty()) {
	            System.out.println("Appointments for the patient: " + patientAppointments);
	        } else {
	            System.out.println("No appointments found for the patient!");
	        }
		} catch (PatientNumberNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private static void getAppointmentsForDoctor(IHospitalService hospitalService, Scanner scanner) {
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        List<Appointment> doctorAppointments = hospitalService.getAppointmentsForDoctor(doctorId);
        if (!doctorAppointments.isEmpty()) {
            System.out.println("Appointments for the doctor: " + doctorAppointments);
        } else {
            System.out.println("No appointments found for the doctor!");
        }
    }

    private static void scheduleAppointment(IHospitalService hospitalService, Scanner scanner) {
        // Collect appointment details from the user
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDateStr = scanner.next();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine(); // Consume the newline character
        description = scanner.nextLine(); // Read the description

        // Convert the appointmentDateStr to java.util.Date
        java.util.Date appointmentDate = null; // Default value if parsing fails

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            appointmentDate = dateFormat.parse(appointmentDateStr);
        } catch (ParseException e) {
            System.out.println("Error parsing appointment date. Please enter a valid date format (YYYY-MM-DD).");
            return; // Exit the method if parsing fails
        }

        // Create an Appointment object
        Appointment appointment = new Appointment(0, patientId, doctorId, appointmentDate, description);

        // Call the service to schedule the appointment
        boolean result = hospitalService.scheduleAppointment(appointment);
        if (result) {
            System.out.println("Appointment scheduled successfully!");
        } else {
            System.out.println("Failed to schedule appointment. Please try again.");
        }
    }


    private static void updateAppointment(IHospitalService hospitalService, Scanner scanner) {
        // Collect appointment details from the user
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDateStr = scanner.next();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine(); // Consume the newline character
        description = scanner.nextLine(); // Read the description

        // Convert the appointmentDateStr to java.util.Date
        java.util.Date appointmentDate = null; // Default value if parsing fails

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            appointmentDate = dateFormat.parse(appointmentDateStr);
        } catch (ParseException e) {
            System.out.println("Error parsing appointment date. Please enter a valid date format (YYYY-MM-DD).");
            return; // Exit the method if parsing fails
        }

        // Create an Appointment object
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, description);

        // Call the service to update the appointment
        boolean result = hospitalService.updateAppointment(appointment);
        if (result) {
            System.out.println("Appointment updated successfully!");
        } else {
            System.out.println("Failed to update appointment. Please try again.");
        }
    }


    private static void cancelAppointment(IHospitalService hospitalService, Scanner scanner) {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();

        // Call the service to cancel the appointment
        boolean result = hospitalService.cancelAppointment(appointmentId);
        if (result) {
            System.out.println("Appointment canceled successfully!");
        } else {
            System.out.println("Failed to cancel appointment. Please try again.");
        }
    }
}

