package pl.mirek.carmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.mirek.carmanagement.dao.CarDao;
import pl.mirek.carmanagement.model.Car;
import pl.mirek.carmanagement.service.CarService;

@Component
public class Start {

    private CarService carService;

    @Autowired
    public Start(CarService carService) {
        this.carService = carService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void testDB() {
        // carDao.getTableInfo();
        // carService.saveCar(new Car("Renault", "Twingo", 2008L));
    }
}
