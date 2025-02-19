package exercise;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

// Coloquei todas as funções (tanto do vehicleService quanto do modelService) aqui na main para conseguir rodar, pois não estava conseguindo fazer a chamada para outro arquivo;
// Modifiquei os arquivos CSV, trocando o ponto e vírgula por vírgula, pois não achei um modo para o CSVReader conseguir delimitar por ponto e vírgula, apenas por vírgula;
// Troquei também as vírgulas dos valores por pontos, no arquivo CSV, tendo em mente que o CSVReader separa por vírgula.
public class ExerciseApp {

	public static VehicleService service;

	public static void main(String[] args) throws IOException {
		run();

	}

	public static void run() throws IOException {
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

		System.out.println("Total geral abastecido de GASOLINA: " + fueledGasQuantity + " litros\n"
				+ "Total abastecido de ETANOL: " + fueledEthanolQuantity + " litros");

	}

	public static List<Double> fuelVehicle(Vehicle vehicle) throws IOException {
		PriceTable priceTable = new PriceTable();
		Double fueledGasQuantity = 0d;
		Double fueledEthanolQuantity = 0d;
		List<Double> totalFueled = new ArrayList<Double>();

		Double gasPrice = priceTable.getGasPrice();
		Double ethanolPrice = priceTable.getEthanolPrice();
		Model model = findModelByVehicleModel(vehicle.getModel());

		if (model.getEthanolConsumption() >= model.getGasConsumption()) {
			fueledEthanolQuantity += fuelEthanolVehicle(model, ethanolPrice, vehicle.getLicensePlate());
		} else {
			fueledGasQuantity += fuelGasVehicle(model, gasPrice, vehicle.getLicensePlate());
		}

		totalFueled.add(fueledGasQuantity);
		totalFueled.add(fueledEthanolQuantity);

		return totalFueled;

	}

	public static Double fuelGasVehicle(Model model, Double price, String licensePlate) {
		Double gasCapacity = model.getFuelCapacity();
		Double totalPrice = gasCapacity * price;

		System.out.println("Veículo modelo " + model.getVehicleModel() + " placa " + licensePlate
				+ " foi abastecido com " + gasCapacity + " litros de GASOLINA, e o valor total foi " + totalPrice);

		return gasCapacity;
	}

	public static Double fuelEthanolVehicle(Model model, Double price, String licensePlate) {
		Double ethanolCapacity = model.getFuelCapacity();
		Double totalPrice = ethanolCapacity * price;

		System.out.println("Veículo modelo " + model.getVehicleModel() + " placa " + licensePlate
				+ " foi abastecido com " + ethanolCapacity + " litros de ETANOL, e o valor foi " + totalPrice);

		return ethanolCapacity;
	}

	public static Model findModelByVehicleModel(String vehicleModel) throws IOException {
		Model modelObj = new Model();
		Reader reader = Files.newBufferedReader(
				Paths.get("/home/candidato13/eclipse-workspace/exercise/src/main/java/exercise/modelos.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

		List<String[]> models = csvReader.readAll();

		for (String[] model : models) {
			if (vehicleModel.equals(model[0])) {
				modelObj.setVehicleModel(model[0]);

				if (model[1].equals("") || model[1].equals(0) || model[1].equals(null)) {
					modelObj.setEthanolConsumption(0);
				} else {
					modelObj.setEthanolConsumption(Double.parseDouble(model[1]));
				}

				if (model[2].equals("") || model[2].equals(0) || model[2].equals(null)) {
					modelObj.setGasConsumption(0);
				} else {
					modelObj.setGasConsumption(Double.parseDouble(model[2]));
				}

				modelObj.setFuelCapacity(Double.parseDouble(model[3]));
			}
		}

		return modelObj;
	}

}
