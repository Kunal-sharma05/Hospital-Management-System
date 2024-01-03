package entity;

import java.util.Date;

public class Patient {
	private int patientId;
	private String firstName,lastName,contactNumber,address;
	private Date dateOfBirth; 
	private char gender;//M fore male , f for female, O for others,N for none
	public Patient()
	{
		this.patientId=0;
		this.firstName="No input"; 
		this.lastName= "No input";
		this.contactNumber= "No input";
		this.address = "No input";
		this.gender='N';
	}
	public Patient(int patientId,String firstName,String lastName,Date dateOfBirth,String contactNumber,String address,char gender)
	{

		this.patientId=patientId;
		this.firstName=firstName; 
		this.lastName= lastName;
		this.dateOfBirth=dateOfBirth;
		this.contactNumber= contactNumber;
		this.address = address;
		this.gender=gender;	
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", gender=" + gender + "]";
	}
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	

}
