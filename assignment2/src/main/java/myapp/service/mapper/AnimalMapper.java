package myapp.service.mapper;

import myapp.domain.*;
import myapp.service.dto.AnimalDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Animal and its DTO AnimalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnimalMapper {

    AnimalDTO animalToAnimalDTO(Animal animal);

    List<AnimalDTO> animalsToAnimalDTOs(List<Animal> animals);

    Animal animalDTOToAnimal(AnimalDTO animalDTO);

    List<Animal> animalDTOsToAnimals(List<AnimalDTO> animalDTOs);
}
