package dk.kea.projekt3_gruppe_3.Controller;

import dk.kea.projekt3_gruppe_3.Model.CarStatus;
import dk.kea.projekt3_gruppe_3.Service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        Map<CarStatus, Integer> carCountByStatus = dashboardService.countCarsByStatus();
        model.addAttribute("carCountByStatus",carCountByStatus);
        int totalCars = dashboardService.countTotalCars();
        model.addAttribute("totalCars",totalCars);
        return "businessEmployee";
    }
}
