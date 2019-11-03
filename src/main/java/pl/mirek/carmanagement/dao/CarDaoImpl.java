package pl.mirek.carmanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.mirek.carmanagement.model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(Car car) {
        String sql = "INSERT INTO car (car_brand, car_model, car_mnfd_year) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, car.getCarBrand(), car.getCarModel(), car.getCarMnfdYear());
    }

    @Override
    public void updateCar(Car car) {
        String sql = "UPDATE car SET car.car_brand=?, car.car_model=?, car.car_mnfd_year=? WHERE car.car_id=?";
        jdbcTemplate.update(sql, car.getCarBrand(), car.getCarModel(), car.getCarMnfdYear(), car.getCarId());
    }

    @Override
    public void deleteCar(long id) {
        String sql = "DELETE FROM car WHERE car_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Car> findAll() {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM `car`";
        carList = jdbcTemplate.query(sql, singleCarMapper);
        return carList;
    }

    @Override
    public Car getCarById(long id) {
        String sql = "SELECT * FROM `car` WHERE car_id = ?";
        List<Car> cars = jdbcTemplate.query(sql, singleCarMapper, id);
        return cars.get(0);
    }

    @Override
    public List<Car> findCarByBrand(String brand) {
        String sql = "SELECT * FROM `car` WHERE car_brand LIKE ?";
        List<Car> cars = jdbcTemplate.query(sql, singleCarMapper, "%" + brand + "%");
        if (cars.size() > 0) {
            return cars;
        }
        return null;
    }

    @Override
    public List<Car> findCarManufacturedBetween(long from, long to) {
        String sql = "SELECT * FROM `car` WHERE car_mnfd_year BETWEEN ? AND ? ORDER BY car_mnfd_year ASC";
        List<Car> cars = jdbcTemplate.query(sql, singleCarMapper, from, to);
        if (cars.size() > 0) {
            return cars;
        }
        return null;
    }


    @Override
    public void getTableInfo() {
        String sql = "SHOW CREATE TABLE `car`;";
        List<String> columns = jdbcTemplate.queryForObject(sql, twoColumnTextMapper);
    }

    private RowMapper<Car> singleCarMapper = new RowMapper<Car>() {
        @Override
        public Car mapRow(ResultSet rs, int i) throws SQLException {
            Car car = new Car(rs.getString("car_brand"),
                    rs.getString("car_model"),
                    rs.getLong("car_mnfd_year"));
            car.setCarId(rs.getLong("car_id"));
            return car;
        }
    };

    private RowMapper<List<String>> twoColumnTextMapper = new RowMapper<List<String>>() {
        public List<String> mapRow(ResultSet rs, int numRow) throws SQLException {
            List<String> output = new ArrayList<>();
            output.add(rs.getString(1));
            output.add(rs.getString(2));
            return output;
        }
    };
}
