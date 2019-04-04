package finki.ukim.mk.emt.konstantinb.lab01.services;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ManufacturerAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.ManufacturerNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface ManufacturerService {
    Manufacturer addNewManufacturer(Manufacturer manufacturer) throws ManufacturerAlreadyExistsException;
    Manufacturer addNewManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException;
    List<Manufacturer> getAllManufacturers();
    Manufacturer update(Manufacturer manufacturer);
    void delete(Manufacturer manufacturer) throws ManufacturerNotFoundException;
    Manufacturer getById(Long manufacturerID) throws ManufacturerNotFoundException;
    Manufacturer getByName(String name) throws ManufacturerNotFoundException;
}
