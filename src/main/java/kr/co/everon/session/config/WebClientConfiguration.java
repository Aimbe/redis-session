package kr.co.everon.session.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

	final Environment env;

	/*
	* Reactor Netty 설정을 사용자 정의하려면 사전 구성된 HttpClient를 이용한다.  */

	@Bean
	public WebClient WebClient() {

		/*Connect Timeout 과 ReadTimeout, WriteTimeout을 모두 5000ms로 지정한 HttpClient 객체를 만들어 주입*/

		HttpClient httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.responseTimeout(Duration.ofMillis(5000))
			.doOnConnected(conn ->
				conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
					.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));


		String url = env.getProperty("url");

		return WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.build();

	}


	/*
	* 1. 글로벌 리소스에서 독립적인 리소스를 만든다.
	  2.  자원 팩토리에서 ReactorClientHttpConnector 생성자를 사용한다.
	  3.  커넥터 WebClient.Builder에 연결한다.*/

	@Bean
	public ReactorResourceFactory resourceFactory() {
		ReactorResourceFactory factory = new ReactorResourceFactory();
		factory.setUseGlobalResources(false);   // (1)
		return factory;
	}


}