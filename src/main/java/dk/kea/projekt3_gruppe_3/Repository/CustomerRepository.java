package dk.kea.projekt3_gruppe_3.Repository;

import dk.kea.projekt3_gruppe_3.Model.Customer;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final DataSource dataSource;

    public CustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Customer customer) {
        String sql = """
                INSERT INTO customer (first_name, last_name, email, phone, address)
                VALUES (?, ?, ?, ?, ?)
                """;

        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getAddress());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke gemme kunden", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public Customer findById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToCustomer(resultSet);
                }

                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke finde kunden", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer ORDER BY customer_id";
        List<Customer> customers = new ArrayList<>();
        Connection connection = DataSourceUtils.getConnection(dataSource);

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                customers.add(mapRowToCustomer(resultSet));
            }

            return customers;
        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente kunderne", e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    private Customer mapRowToCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("customer_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("address")
        );
    }
}