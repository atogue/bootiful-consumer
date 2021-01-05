package org.craftchain.bootifulconsumer;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.craftchain.bootifulconsumer.consumer.Reservation;
import org.craftchain.bootifulconsumer.consumer.ReservationClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import wiremock.org.apache.http.HttpHeaders;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(ids = "org.craftchain:bootiful-reactive:+:8080", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//@AutoConfigureWireMock(port = 8080)
public class BootifulConsumerApplicationTests {

	@Autowired
	ReservationClient client;

	@Test
	public void contextLoads() {

//		var json = " [ { \"id\":\"1\" , \"reservationName\":\"Anicet\"  } ] ";
//
//		stubFor(WireMock.get(WireMock.urlEqualTo("/reservations"))
//				.willReturn(WireMock
//						.aResponse()
//						.withBody(json)
//						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//						.withStatus(HttpStatus.OK.value())));


		Flux<Reservation> reservations = this.client.getAllReservations();

		StepVerifier
				.create(reservations)
				.expectNextMatches(r -> r.getId() != null && r.getName().equalsIgnoreCase("anicet"))
				.verifyComplete();
	}

}
