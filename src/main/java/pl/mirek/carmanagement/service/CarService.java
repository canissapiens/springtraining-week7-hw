package pl.mirek.carmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mirek.carmanagement.dao.CarDao;
import pl.mirek.carmanagement.model.Car;

import java.util.List;

@Service
public class CarService {

    private CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public void saveCar(Car car) {
        carDao.saveCar(car);
    }

    public List<Car> findAll() {
        return carDao.findAll();
    }

    public Car getCarById(long id) {
        return carDao.getCarById(id);
    }

    public List<Car> findCarByBrand(String brand) {
        return carDao.findCarByBrand(brand);
    }

    public List<Car> findCarManufacturedBetween(long from, long to) {
        return carDao.findCarManufacturedBetween(from, to);
    }

    public void deleteCar(long id) {
        carDao.deleteCar(id);
    }

    public void updateCar(Car car) {
        carDao.updateCar(car);
    }
}
