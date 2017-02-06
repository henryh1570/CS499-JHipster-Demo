package myapp.service;

import myapp.service.dto.PersonDTO;
import java.util.List;

/**
 * Service Interface for managing Person.
 */
public interface PersonService {

    /**
     * Save a person.
     *
     * @param personDTO the entity to save
     * @return the persisted entity
     */
    PersonDTO save(PersonDTO personDTO);

    /**
     *  Get all the people.
     *  
     *  @return the list of entities
     */
    List<PersonDTO> findAll();

    /**
     *  Get the "id" person.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PersonDTO findOne(Long id);

    /**
     *  Delete the "id" person.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
