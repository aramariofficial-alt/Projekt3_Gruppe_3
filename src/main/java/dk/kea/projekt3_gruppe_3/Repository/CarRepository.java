package dk.kea.projekt3_gruppe_3.Repository;

import dk.kea.projekt3_gruppe_3.Model.Car;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    private final DataSource dataSource;

    public List<Car> getAllCars()

    public Car getCarById(int carId)

    public void createCar(Car car)

    public void updateCar(Car car)

    public void deleteCar(int carId)

    public List<Car> getCarsByStatus(CarStatus status)

    public void updateCarStatus(int carId, CarStatus status)