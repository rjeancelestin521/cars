package mg.rjc.testcars.service;

import mg.rjc.testcars.entities.Car;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    public Car saveCar(Car car);
    public List<Car> getCars();
    public byte[] getPhoto(Long id) throws Exception;
    public void uploadPhoto(MultipartFile file, Long id) throws Exception;
}
