package dk.kea.projekt3_gruppe_3.Service;

import dk.kea.projekt3_gruppe_3.Model.Car;
import dk.kea.projekt3_gruppe_3.Model.CarStatus;
import dk.kea.projekt3_gruppe_3.Repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final CarRepository carRepository;

    public DashboardService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Map<CarStatus, Integer> countCarsByStatus(){
        Map<CarStatus,Integer> carCountByStatus = new LinkedHashMap<>();

        for (CarStatus status : CarStatus.values()){
            List<Car> cars = carRepository.findByStatus(status);
            carCountByStatus.put(status,cars.size());
        }

        return carCountByStatus;
    }
}
