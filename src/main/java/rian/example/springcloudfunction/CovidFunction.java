package rian.example.springcloudfunction;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
import rian.example.springcloudfunction.input.Request;
import rian.example.springcloudfunction.mapper.Mapper;
import rian.example.springcloudfunction.output.Response;
import rian.example.springcloudfunction.service.WebclientService;

@Component
public class CovidFunction implements Function<Request, Response> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CovidFunction.class);

	private final WebclientService webclientService;

	private Mapper mapper;

	public CovidFunction(final WebclientService webclientService, Mapper mapper) {
		this.webclientService = webclientService;
		this.mapper = mapper;
	}

	@Override
	public Response apply(Request request) {
		Mono<String> call = webclientService.getCases(request.getName());
		call.subscribe(value -> {
			Integer total = mapper.mapperToCountryDetailsList(value).stream()
					.mapToInt(caseCovid -> caseCovid.getCases()).sum();
			LOGGER.info("Enviando.... Total: " + total);
		});
		return Response.builder().message("Sucess...").build();
	}

}
