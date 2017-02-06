package myapp.service.impl;

import myapp.service.AnimalService;
import myapp.domain.Animal;
import myapp.repository.AnimalRepository;
import myapp.service.dto.AnimalDTO;
import myapp.service.mapper.AnimalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Animal.
 */
@Service
@Transactional
public class AnimalServiceImpl implements AnimalService{

    private final Logger log = LoggerFactory.getLogger(AnimalServiceImpl.class);
    
    private final AnimalRepository animalRepository;

    private final AnimalMapper animalMapper;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    /**
     * Save a animal.
     *
     * @param animalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AnimalDTO save(AnimalDTO animalDTO) {
        log.debug("Request to save Animal : {}", animalDTO);
        Animal animal = animalMapper.animalDTOToAnimal(animalDTO);
        animal = animalRepository.save(animal);
        AnimalDTO result = animalMapper.animalToAnimalDTO(animal);
        return result;
    }

    /**
     *  Get all the animals.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AnimalDTO> findAll() {
        log.debug("Request to get all Animals");
        List<AnimalDTO> result = animalRepository.findAll().stream()
            .map(animalMapper::animalToAnimalDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one animal by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AnimalDTO findOne(Long id) {
        log.debug("Request to get Animal : {}", id);
        Animal animal = animalRepository.findOne(id);
        AnimalDTO animalDTO = animalMapper.animalToAnimalDTO(animal);
        return animalDTO;
    }

    /**
     *  Delete the  animal by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Animal : {}", id);
        animalRepository.delete(id);
    }
}
