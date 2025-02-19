package exercise;

public class Model {
	private String vehicleModel;
	private double ethanolConsumption;
	private double gasConsumption;
	private double fuelCapacity;

	public Model() {

	}

	public Model(String vehicleModel, double ethanolConsumption, double gasConsumption, double fuelCapacity) {
		super();
		this.vehicleModel = vehicleModel;
		this.ethanolConsumption = ethanolConsumption;
		this.gasConsumption = gasConsumption;
		this.fuelCapacity = fuelCapacity;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public double getEthanolConsumption() {
		return ethanolConsumption;
	}

	public void setEthanolConsumption(double ethanolConsumption) {
		this.ethanolConsumption = ethanolConsumption;
	}

	public double getGasConsumption() {
		return gasConsumption;
	}

	public void setGasConsumption(double gasConsumption) {
		this.gasConsumption = gasConsumption;
	}

	public double getFuelCapacity() {
		return fuelCapacity;
	}

	public void setFuelCapacity(double fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

}