package rian.example.springcloudfunction.service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Component
public class WebclientService {

	@Value("${covid.api.url}")
	private String url;

	@Value("${covid.api.resource}")
	private String resource;

	public Mono<String> getCases(String input) {
		TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.doOnConnected(connection -> {
					connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
					connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				});
		WebClient client = WebClient.builder().baseUrl(url)
				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient))).build();
		return client.get().uri(URI.create(String.format(url + resource, input))).retrieve().bodyToMono(String.class);
	}

}
