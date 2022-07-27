package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Инициализация данных по моделям автомобилей
 */
public class V2__CarsModelsInit extends BaseJavaMigration {
	private static final String MODEL_LIST = "db/cars.json";
	private static final Integer BATCH_SIZE = 500;

	@Override
	public void migrate(Context context) throws Exception {
		DataSource dataSource = new SingleConnectionDataSource(context.getConnection(), false);
		JsonParser jsonParser = new JacksonJsonParser();

		String json = new String(getResource(MODEL_LIST).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		Map<String, Object> modelList = jsonParser.parseMap(json);

		@SuppressWarnings("unchecked")
		LinkedHashMap<String, ArrayList<String>> models = (LinkedHashMap<String, ArrayList<String>>) modelList.get("list");

		// марки автомобилей (toyota, ford, ...)
		Map<String, String> brandName2Id = new HashMap<>();

		saveCarBrands(dataSource, models, brandName2Id);
		saveCarModels(dataSource, models, brandName2Id);
	}

	private void saveCarBrands(DataSource dataSource, LinkedHashMap<String, ArrayList<String>> models, Map<String, String> brandName2Id) {
		List<MapSqlParameterSource> batch = new ArrayList<>();
		Iterator<Map.Entry<String, ArrayList<String>>> brandIter = models.entrySet().iterator();
		int i = 0;
		while (brandIter.hasNext()) {
			Map.Entry<String, ArrayList<String>> brand = brandIter.next();

			// create row
			MapSqlParameterSource row = new MapSqlParameterSource();
			String id = UUID.randomUUID().toString();
			row.addValue("id", id);
			row.addValue("name", brand.getKey());
			batch.add(row);

			// save id
			brandName2Id.put(brand.getKey(), id);

			if (++i % BATCH_SIZE == 0 || !brandIter.hasNext()) {
				new SimpleJdbcInsert(dataSource).withTableName("car_brand")
						.usingColumns("id", "name")
						.executeBatch(batch.toArray(new SqlParameterSource[]{}));
				batch.clear();
			}
		}
	}

	private void saveCarModels(DataSource dataSource, LinkedHashMap<String, ArrayList<String>> models, Map<String, String> brandName2Id) {
		List<MapSqlParameterSource> batch = new ArrayList<>();
		Iterator<Map.Entry<String, ArrayList<String>>> brandIter = models.entrySet().iterator();
		int i = 0;
		while (brandIter.hasNext()) {
			Map.Entry<String, ArrayList<String>> brand = brandIter.next();

			String brandId = brandName2Id.get(brand.getKey());
			Iterator<String> brandModelsIter = brand.getValue().iterator();

			while (brandModelsIter.hasNext()) {
				String model = brandModelsIter.next();

				// create row
				MapSqlParameterSource row = new MapSqlParameterSource();
				String id = UUID.randomUUID().toString();
				row.addValue("id", id);
				row.addValue("name", model);
				row.addValue("car_brand_id", brandId);
				batch.add(row);

				if (++i % BATCH_SIZE == 0 || (!brandIter.hasNext() && !brandModelsIter.hasNext())) {
					new SimpleJdbcInsert(dataSource).withTableName("car_model")
							.usingColumns("id", "name", "car_brand_id")
							.executeBatch(batch.toArray(new SqlParameterSource[]{}));
					batch.clear();
				}
			}
		}
	}

	private Resource getResource(String name) {
		return new ClassPathResource(name);
	}
}
