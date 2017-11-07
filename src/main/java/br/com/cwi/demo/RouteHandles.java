package br.com.cwi.demo;

import br.com.cwi.demo.Classes.Car;
import br.com.cwi.demo.Events.CarEvents;
import br.com.cwi.demo.Service.FluxCarService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
public class RouteHandles {

	@Autowired
	private FluxCarService fluxCarService;

	private Mono<ServerResponse> allCars(ServerRequest serverRequest) {
		return ServerResponse.ok()
				.body(fluxCarService.all(), Car.class)
				.doOnError(throwable -> new IllegalStateException("My godness NOOOOO!!"));
	}

	private Mono<ServerResponse> carById(ServerRequest serverRequest) {
		String carId = serverRequest.pathVariable("carId");
		return ServerResponse.ok()
				.body(fluxCarService.byId(carId), Car.class)
				.doOnError(throwable -> new IllegalStateException("oh boy... not againnn =(("));
	}

	private Mono<ServerResponse> events(ServerRequest serverRequest) {
		String carId = serverRequest.pathVariable("carId");
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(fluxCarService.streams(carId), CarEvents.class)
				.doOnError(throwable -> new IllegalStateException("I give up!! "));
	}

	@Bean("cars")
	RouterFunction<?> routes() {
		return RouterFunctions.route(
				RequestPredicates.GET("/cars"), this::allCars)
				.andRoute(RequestPredicates.GET("/cars/{carId}"), this::carById)
				.andRoute(RequestPredicates.GET("/cars/{carId}/events"), this::events);
	}
}
