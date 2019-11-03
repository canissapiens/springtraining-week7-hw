package pl.mirek.carmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mirek.carmanagement.model.Car;
import pl.mirek.carmanagement.service.CarService;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "index";
    }

    @GetMapping("search-car")
    public String searchCar(@RequestParam String searchstring, Model model) {
        String[] input = searchstring.split("\\ *,\\ *");
        List<Car> carList = null;
        if (input.length == 1) {
            carList = carService.findCarByBrand(input[0]);
        } else if (input.length == 2) {
            carList = carService.findCarManufacturedBetween(Long.valueOf(input[0]), Long.valueOf(input[1]));
        } else {
            carList = carService.findAll();
        }
        model.addAttribute("cars", carList);
        return "index";
    }

    @GetMapping("/edit-car")
    public String editCar(@RequestParam long caridentifier, Model model) {
        Car car = carService.getCarById(caridentifier);
        model.addAttribute("newCar", car);
        return "addcar";
    }

    @GetMapping("/delete-car")
    public String deleteCar(@RequestParam long caridentifier) {
        // usuwamy samochód
        carService.deleteCar(caridentifier);
        return "redirect:";
    }

    @GetMapping("/add-car")
    public String addCarForm(Model model) {

        Car car = new Car("", "", 2000L);
        car.setCarId(0L);
        model.addAttribute("newCar", car);
        return "addcar";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car newCar) {
        if (newCar.getCarId() == 0) {
            carService.saveCar(newCar);
        } else {
            carService.updateCar(newCar);
        }
        return "redirect:";
    }
}
