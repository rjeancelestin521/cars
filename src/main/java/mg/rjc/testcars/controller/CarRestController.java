package mg.rjc.testcars.controller;

import lombok.RequiredArgsConstructor;
import mg.rjc.testcars.entities.Car;
import mg.rjc.testcars.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class CarRestController {

    private final CarService carService;

    @GetMapping(path = "/cars")
    public ResponseEntity<List<Car>> getCats() {
        return ResponseEntity.ok().body(carService.getCars());
    }

    @PostMapping(path = "/cars/save")
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cars/save").toUriString());
        Car newCar = carService.saveCar(car);
        return ResponseEntity.created(uri).body(newCar);
    }

    @GetMapping(path = "/photoCar/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getPhotoByCarId(@PathVariable("id") Long id) throws Exception{
        return ResponseEntity.ok().body(carService.getPhoto(id));
    }

    @PostMapping(path = "/uploadPhotoCar/{id}")
    public ResponseEntity<?> uploadPhoto(MultipartFile file, @PathVariable("id") Long id) throws Exception {
        carService.uploadPhoto(file, id);
        return ResponseEntity.ok().build();
    }

}
