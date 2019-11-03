package pl.mirek.carmanagement.dao;

import pl.mirek.carmanagement.model.Car;

import java.util.List;

public interface CarDao {

    void saveCar(Car car);

    void updateCar(Car car);

    void deleteCar(long id);

    List<Car> findAll();

    Car getCarById(long id);

    void getTableInfo();

    List<Car> findCarByBrand(String brand);

    List<Car> findCarManufacturedBetween(long from, long to);
}
