package br.com.cwi.demo.Classes;

import br.com.cwi.demo.Repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {

    private final CarRepository carRepository;

    DummyData(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        carRepository.deleteAll()
                .thenMany(
                        Flux.just("Koenigsegg One:1", "Hennessy Venom GT", "Bugatti Veyron Super Sport",  "SSC Ultimate Aero", "McLaren F1", "Pagani Huayra", "Noble M600",
                                "Aston Martin One-77", "Ferrari LaFerrari", "Lamborghini Aventador")
                                .map(model -> new Car(UUID.randomUUID().toString(), model))
                                .flatMap(carRepository::save))
                .subscribe(System.out::println);
    }
}
