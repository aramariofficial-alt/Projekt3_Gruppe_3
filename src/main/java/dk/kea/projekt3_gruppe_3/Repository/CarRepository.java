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

    public Car findById(int vehicleNumber) {
        String sql = "SELECT * FROM wish WHERE id = ?";
        Car car = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vehicleNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    car = new Car();
                    car.getVehicleNumber(resultSet.getInt("vehicleNumber"));
                    car.getChassisNumber(resultSet.getString("chassisNumber"));
                    car.getBrand(resultSet.getString("brand"));
                    car.getModel(resultSet.getInt("model"));
                    car.getStatus(resultSet.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding car by id");
            e.printStackTrace();
        }

        return car; // Returnerer bil, eller null hvis det ikke blev fundet
    }


    public void save(Car car)

    public List<Car> findByStatus(CarStatus status)

    public void updateStatus(int vehicleNumber, CarStatus status)

    public boolean existsByVehicleNumber(int vehicleNumber)

    public boolean existsByChassisNumber(String chassisNumber)

    public boolean updateStatusIfAvailable(int vehicleNumber)