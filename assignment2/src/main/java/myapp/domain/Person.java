package myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Animal owns;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "person_uses",
               joinColumns = @JoinColumn(name="people_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="uses_id", referencedColumnName="id"))
    private Set<Item> uses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal getOwns() {
        return owns;
    }

    public Person owns(Animal animal) {
        this.owns = animal;
        return this;
    }

    public void setOwns(Animal animal) {
        this.owns = animal;
    }

    public Set<Item> getUses() {
        return uses;
    }

    public Person uses(Set<Item> items) {
        this.uses = items;
        return this;
    }

    public Person addUses(Item item) {
        this.uses.add(item);
        return this;
    }

    public Person removeUses(Item item) {
        this.uses.remove(item);
        return this;
    }

    public void setUses(Set<Item> items) {
        this.uses = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        if (person.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", name='" + name + "'" +
            '}';
    }
}
