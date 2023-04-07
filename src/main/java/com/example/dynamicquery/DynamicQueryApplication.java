package com.example.dynamicquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.query.NativeQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class DynamicQueryApplication {

	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(DynamicQueryApplication.class, args);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Map<String, Object>> executeDynamicQuery(@RequestParam("query") String query) throws Exception{

		// Create a new Query object from the input SQL query string
		Query q = entityManager.createNativeQuery(query);

		// Execute the query and retrieve the results as a list of arrays of Object values
		List<Object[]> resultList = q.getResultList();

		// Initialize an empty list of Map objects to hold the result set data
		List<Map<String, Object>> response = new ArrayList<>();

		// Obtain the ResultSetMetaData object for the query result set
		ResultSetMetaData metaData = q.unwrap(NativeQuery.class).getSession().doReturningWork(
				connection -> connection.prepareStatement(query).executeQuery().getMetaData());

		// Retrieve the number of columns in the result set
		int columnCount = metaData.getColumnCount();

		// Loop over the result set rows and extract the column values and names for each row
		for (Object[] row : resultList) {
			// Create a new LinkedHashMap to hold the row data
			Map<String, Object> map = new LinkedHashMap<>();
			// Loop over the columns and extract their names and values
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				map.put(columnName, row[i-1]);
			}
			// Add the row Map to the response list
			response.add(map);
		}

		// Return the list of Map objects containing the result set data with named column keys
		return response;
	}

}
