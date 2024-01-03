package entity;

public class Doctor {
	private int doctorId;
	private String firstName,lastName,specialization,contactNumber;
	
	public Doctor()
	{
		this.doctorId=0;
		this.firstName="No input";
		this.lastName="No input";
		this.specialization="No input";
		this.contactNumber ="No input";
	}
	public Doctor(int doctorId,String firstName,String lastName,String specialization,String contactNumber)
	{
		this.doctorId=doctorId;
		this.firstName=firstName;
		this.lastName=lastName;
		this.specialization=specialization;
		this.contactNumber =contactNumber;
	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", specialization=" + specialization + ", contactNumber=" + contactNumber + "]";
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
