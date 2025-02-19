package exercise;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class ModelService {

	public Model findModelByVehicleModel(String vehicleModel) throws IOException {
		Model modelObj = new Model();
		Reader reader = Files.newBufferedReader(
				Paths.get("/home/candidato13/eclipse-workspace/exercise/src/main/java/exercise/modelos.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

		List<String[]> models = csvReader.readAll();

		for (String[] model : models) {
			if (vehicleModel == model[0]) {
				modelObj.setVehicleModel(model[0]);

				if (model[1] != null || model[1] != "") {
					modelObj.setEthanolConsumption(Double.parseDouble(model[1].replaceAll(",", ".")));
				} else {
					modelObj.setEthanolConsumption(0);
				}

				if (model[2] != null || model[2] != "") {
					modelObj.setGasConsumption(Double.parseDouble(model[2].replaceAll(",", ".")));
				} else {
					modelObj.setGasConsumption(0);
				}

				modelObj.setFuelCapacity(Double.parseDouble(model[3].replaceAll(",", ".")));
			}
		}

		return modelObj;
	}
}