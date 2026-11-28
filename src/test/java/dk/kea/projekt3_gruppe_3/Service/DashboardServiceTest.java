package dk.kea.projekt3_gruppe_3.Service;

import dk.kea.projekt3_gruppe_3.Model.CarStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DashboardServiceTest {
    @Autowired
    private DashboardService dashboardService;

    @Test
    public void sumOfStatusCountsEqualsTotalCars(){
        Map<CarStatus, Integer> carCountByStatus = dashboardService.countCarsByStatus();
        int sum = 0;
        for (int count : carCountByStatus.values()){
            sum += count;
        }
        assertEquals(dashboardService.countTotalCars(),sum);
    }

}