package rian.example.springcloudfunction.handler;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import rian.example.springcloudfunction.input.Request;
import rian.example.springcloudfunction.output.Response;

public class CovidFunctionHandler extends AzureSpringBootRequestHandler<Request, Response> {

	@FunctionName("covidFunction")
	public HttpResponseMessage execute(@HttpTrigger(name = "request", methods = { HttpMethod.GET,
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Request>> request,
			ExecutionContext context) {

		context.getLogger().info("Greeting user name: " + request.getBody().get().getName());
		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(request.getBody().get(), context))
				.header("Content-Type", "application/json").build();
	}
}
