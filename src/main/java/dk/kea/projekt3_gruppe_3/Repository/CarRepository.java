
package dk.kea.projekt3_gruppe_3.Repository;

import dk.kea.projekt3_gruppe_3.Model.Car;
import dk.kea.projekt3_gruppe_3.Model.CarStatus;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DataSourceUtils;

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

    // Henter alle biler
    public List<Car> findAll() {
        String sql = "SELECT * FROM car";
        List<Car> cars = new ArrayList<>();

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                cars.add(mapRowToCar(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all cars", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return cars;
    }


    // Henter en bestemt bil ud fra vognnummer
    public Car findById(int vehicleNumber) {
        String sql = "SELECT * FROM car WHERE vehicle_number = ?";
        Car car = null;

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vehicleNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    car = mapRowToCar(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding car by id", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return car;
    }


    // Gemmer en ny bil i databasen
    public void save(Car car) {
        String sql = "INSERT INTO car (vehicle_number, chassis_number, brand, model, status) VALUES (?, ?, ?, ?, ?)";

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, car.getVehicleNumber());
            statement.setString(2, car.getChassisNumber());
            statement.setString(3, car.getBrand());
            statement.setString(4, car.getModel());
            statement.setString(5, car.getStatus().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving car", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    // Henter alle biler med en bestemt status
    public List<Car> findByStatus(CarStatus status) {
        String sql = "SELECT * FROM car WHERE status = ?";
        List<Car> cars = new ArrayList<>();

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status.name());

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    cars.add(mapRowToCar(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding cars by status", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return cars;
    }


    // Opdaterer status på en bestemt bil
    public void updateStatus(int vehicleNumber, CarStatus status) {
        String sql = "UPDATE car SET status = ? WHERE vehicle_number = ?";

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status.name());
            statement.setInt(2, vehicleNumber);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating car status", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    // Undersøger om vognnummeret allerede eksisterer
    public boolean existsByVehicleNumber(int vehicleNumber) {
        String sql = "SELECT 1 FROM car WHERE vehicle_number = ?";

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vehicleNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking vehicle number", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    // Undersøger om stelnummeret allerede eksisterer
    public boolean existsByChassisNumber(String chassisNumber) {
        String sql = "SELECT 1 FROM car WHERE chassis_number = ?";

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, chassisNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking chassis number", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    // Ændrer kun status til RENTED, hvis bilen er AVAILABLE
    public boolean updateStatusIfAvailable(int vehicleNumber) {
        String sql = "UPDATE car SET status = 'RENTED' WHERE vehicle_number = ? AND status = 'AVAILABLE'";

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vehicleNumber);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Error renting car", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    // Hjælpemetode til at oprette et Car-objekt fra databasen
    private Car mapRowToCar(ResultSet resultSet) throws SQLException {

        return new Car(
                resultSet.getInt("vehicle_number"),
                resultSet.getString("chassis_number"),
                resultSet.getString("brand"),
                resultSet.getString("model"),
                CarStatus.valueOf(resultSet.getString("status"))
        );
    }
}
