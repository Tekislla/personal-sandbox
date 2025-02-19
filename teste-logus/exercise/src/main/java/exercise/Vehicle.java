package exercise;

public class Vehicle {
	private String licensePlate;
	private String model;

	public Vehicle() {

	}

	public Vehicle(String licensePlate, String model) {
		super();
		this.licensePlate = licensePlate;
		this.model = model;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}