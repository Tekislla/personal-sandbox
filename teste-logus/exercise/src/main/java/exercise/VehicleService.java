package exercise;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class VehicleService {

	private ModelService modelService;

	public void run() throws IOException {
		Double fueledGasQuantity = 0d;
		Double fueledEthanolQuantity = 0d;

		Reader reader = Files.newBufferedReader(
				Paths.get("/home/candidato13/eclipse-workspace/exercise/src/main/java/exercise/veiculos.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

		List<String[]> vehicles = csvReader.readAll();

		for (String[] vehicle : vehicles) {
			Vehicle vehicleObj = new Vehicle();
			vehicleObj.setModel(vehicle[0]);
			vehicleObj.setLicensePlate(vehicle[1]);

			List<Double> auxFueledList = fuelVehicle(vehicleObj);
			fueledGasQuantity += auxFueledList.get(0);
			fueledEthanolQuantity += auxFueledList.get(1);

		}

		System.out.println("Total geral abastecido de GASOLINA: " + fueledGasQuantity + "litros\n"
				+ "Total abastecido de ETANOL: " + fueledEthanolQuantity + "litros");

	}

	public List<Double> fuelVehicle(Vehicle vehicle) throws IOException {
		PriceTable priceTable = new PriceTable();
		Double fueledGasQuantity = 0d;
		Double fueledEthanolQuantity = 0d;
		List<Double> totalFueled = new ArrayList<Double>();

		Double gasPrice = priceTable.getGasPrice();
		Double ethanolPrice = priceTable.getEthanolPrice();
		Model model = modelService.findModelByVehicleModel(vehicle.getModel());

		if (model.getEthanolConsumption() >= model.getGasConsumption()) {
			fueledEthanolQuantity += fuelEthanolVehicle(model, ethanolPrice, vehicle.getLicensePlate());
		} else {
			fueledGasQuantity += fuelGasVehicle(model, gasPrice, vehicle.getLicensePlate());
		}

		totalFueled.add(fueledGasQuantity);
		totalFueled.add(fueledEthanolQuantity);

		return totalFueled;

	}

	public Double fuelGasVehicle(Model model, Double price, String licensePlate) {
		Double gasCapacity = model.getFuelCapacity();
		Double totalPrice = gasCapacity * price;

		System.out.println("Veículo modelo " + model.getVehicleModel() + " placa " + licensePlate
				+ " foi abastecido com " + gasCapacity + " litros de GASOLINA, e o valor total foi " + totalPrice);

		return gasCapacity;
	}

	public Double fuelEthanolVehicle(Model model, Double price, String licensePlate) {
		Double ethanolCapacity = model.getFuelCapacity();
		Double totalPrice = ethanolCapacity * price;

		System.out.println("Veículo modelo " + model.getVehicleModel() + " placa " + licensePlate
				+ " foi abastecido com " + ethanolCapacity + " litros de ETANOL, e o valor foi " + totalPrice);

		return ethanolCapacity;
	}

}