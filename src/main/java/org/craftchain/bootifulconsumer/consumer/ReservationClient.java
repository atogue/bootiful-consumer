package org.craftchain.bootifulconsumer.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient {

    @Autowired
    private WebClient client;

    public Flux<Reservation> getAllReservations() {
        return this.client
                .get()
                .uri("http://localhost:8080/reservations")
                .retrieve()
                .bodyToFlux(Reservation.class);
    }
}

