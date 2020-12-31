package rian.example.springcloudfunction.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import rian.example.springcloudfunction.model.CountryDetails;

@Component
public class Mapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);

	public List<CountryDetails> mapperToCountryDetailsList(String response) {
		List<CountryDetails> list = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.createObjectNode();
			rootNode = mapper.readTree(response);
			CountryDetails[] result = mapper.treeToValue(rootNode, CountryDetails[].class);
			list = Arrays.asList(result);
			list.stream().forEach(i -> LOGGER.info(i.toString()));
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}

		return list;
	}

}
