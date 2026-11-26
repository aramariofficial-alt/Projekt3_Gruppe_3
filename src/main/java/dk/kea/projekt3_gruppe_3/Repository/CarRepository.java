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

    public List<Car> findAll()

    public Car findById(int vehicleNumber)

    public void save(Car car)

    public List<Car> findByStatus(CarStatus status)

    public void updateStatus(int vehicleNumber, CarStatus status)

    public boolean existsByVehicleNumber(int vehicleNumber)

    public boolean existsByChassisNumber(String chassisNumber)

    public boolean updateStatusIfAvailable(int vehicleNumber)