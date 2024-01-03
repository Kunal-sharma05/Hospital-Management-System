package dao;

import entity.Appointment;
import util.DBConnection;
import myexceptions.PatientNumberNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements IHospitalService {

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointment WHERE appointmentId = ?")) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToAppointment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
        Connection connection = DBConnection.getConnection();
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointment WHERE patientId = ?")) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            	while (resultSet.next()) {
                appointments.add(mapResultSetToAppointment(resultSet));
            }
            else
            {
            	throw new PatientNumberNotFoundException("Pateint Number not found"+ patientId);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw new PatientNumberNotFoundException("error retrieving Details");
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        Connection connection = DBConnection.getConnection();
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointment WHERE doctorId = ?")) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                appointments.add(mapResultSetToAppointment(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, appointment.getAppointmentId());
            statement.setInt(2, appointment.getPatientId());
            statement.setInt(3, appointment.getDoctorId());
            statement.setDate(4, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            statement.setString(5, appointment.getDescription());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return false;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?")) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            statement.setString(4, appointment.getDescription());
            statement.setInt(5, appointment.getAppointmentId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return false;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Appointment WHERE appointmentId = ?")) {
            statement.setInt(1, appointmentId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return false;
    }

    private Appointment mapResultSetToAppointment(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(resultSet.getInt("appointmentId"));
        appointment.setPatientId(resultSet.getInt("patientId"));
        appointment.setDoctorId(resultSet.getInt("doctorId"));
        appointment.setAppointmentDate(resultSet.getDate("appointmentDate"));
        appointment.setDescription(resultSet.getString("description"));
        return appointment;
    }
}
