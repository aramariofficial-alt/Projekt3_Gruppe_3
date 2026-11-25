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

    public CarRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Car> findByStatus(String status) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE status = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, status);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = new Car(
                            resultSet.getInt("vehicle_number"),
                            resultSet.getString("chassis_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("status"));
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente biler", e);
        }

        return cars;
    }
}