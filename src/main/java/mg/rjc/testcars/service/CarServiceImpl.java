package mg.rjc.testcars.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.rjc.testcars.entities.Car;
import mg.rjc.testcars.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car saveCar(Car car) {
        car.setPhotoName("unknown.png");
        log.info("Saving new car {} to the database", car.getRegistrationNumber());
        return carRepository.save(car);
    }

    @Override
    public List<Car> getCars() {
        log.info("Fetching all car");
        return carRepository.findAll();
    }

    @Override
    public byte[] getPhoto(Long id) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()) {
            Car car = optionalCar.get();;
            return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/cars/" + car.getPhotoName()));
        } else {
            log.error("Car {} is not exists", id);
            throw new Exception("Car " + id + " is not exists");
        }
    }

    @Override
    public void uploadPhoto(MultipartFile file, Long id) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()){
            Car car = optionalCar.get();
            car.setPhotoName(file.getOriginalFilename());
            Files.write(Paths.get(System.getProperty("user.home") + "/cars/" + car.getPhotoName()), file.getBytes());
            carRepository.save(car);
        }else {
            log.error("Car {} is not exists", id);
            throw new Exception("Car " + id + " is not exists");
        }
    }

}
