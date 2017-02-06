package myapp.service.mapper;

import myapp.domain.*;
import myapp.service.dto.PersonDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Person and its DTO PersonDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class, })
public interface PersonMapper {

    @Mapping(source = "owns.id", target = "ownsId")
    PersonDTO personToPersonDTO(Person person);

    List<PersonDTO> peopleToPersonDTOs(List<Person> people);

    @Mapping(source = "ownsId", target = "owns")
    Person personDTOToPerson(PersonDTO personDTO);

    List<Person> personDTOsToPeople(List<PersonDTO> personDTOs);

    default Animal animalFromId(Long id) {
        if (id == null) {
            return null;
        }
        Animal animal = new Animal();
        animal.setId(id);
        return animal;
    }

    default Item itemFromId(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
