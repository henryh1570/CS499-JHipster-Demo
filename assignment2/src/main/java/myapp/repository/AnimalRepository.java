package myapp.repository;

import myapp.domain.Animal;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Animal entity.
 */
@SuppressWarnings("unused")
public interface AnimalRepository extends JpaRepository<Animal,Long> {

}
