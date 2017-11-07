package br.com.cwi.demo.Repository;

import br.com.cwi.demo.Classes.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CarRepository extends ReactiveMongoRepository<Car, String> { }