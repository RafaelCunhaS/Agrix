import com.betrybe.agrix.exceptions.PersonNotFoundException;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.services.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class PersonServiceTest {

  @Autowired
  PersonService personService;
  @MockBean
  PersonRepository repository;

  @Test
  public void testPersonCreation() {
    Person person = new Person();
    person.setUsername("Admin");
    person.setPassword("pass");
    person.setRole(Role.ADMIN);

    Person personToReturn = new Person();
    personToReturn.setId(123L);
    personToReturn.setUsername(person.getUsername());
    personToReturn.setPassword(person.getPassword());
    personToReturn.setRole(person.getRole());

    Mockito.when(repository.save(any(Person.class))).thenReturn(personToReturn);

    Person savedPerson = personService.create(person);

    Mockito.verify(repository).save(any(Person.class));

    assertEquals(123L, savedPerson.getId());
    assertEquals(person.getUsername(), savedPerson.getUsername());
    assertEquals(person.getPassword(), savedPerson.getPassword());
    assertEquals(person.getRole(), savedPerson.getRole());
  }

  @Test
  public void testGetPersonById() {
    Person person = new Person();
    person.setId(123L);
    person.setUsername("Admin");
    person.setPassword("pass");
    person.setRole(Role.ADMIN);

    Mockito.when(repository.findById(eq(123L)))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonById(123L);

    Mockito.verify(repository).findById(eq(123L));

    assertEquals(123L, returnedPerson.getId());
    assertEquals(person.getUsername(), returnedPerson.getUsername());
    assertEquals(person.getPassword(), returnedPerson.getPassword());
    assertEquals(person.getRole(), returnedPerson.getRole());
  }

  @Test
  public void testGetPersonByIdNotFound() {
    Mockito.when(repository.findById(eq(123L)))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(123L));
  }

  @Test
  public void testGetPersonByUsername() {
    Person person = new Person();
    person.setUsername("Admin");
    person.setPassword("pass");
    person.setRole(Role.ADMIN);

    Mockito.when(repository.findByUsername(eq("Admin")))
        .thenReturn(person);

    UserDetails returnedPerson = personService.loadUserByUsername("Admin");

    Mockito.verify(repository).findByUsername(eq("Admin"));

    assertEquals(person.getUsername(), returnedPerson.getUsername());
    assertEquals(person.getPassword(), returnedPerson.getPassword());
  }

  @Test
  public void testGetPersonByUsernameNotFound() {
    Mockito.when(repository.findByUsername(eq("Admin")))
        .thenReturn(new Person());

    assertThrows(PersonNotFoundException.class,
        () -> personService.loadUserByUsername("Admin"));
  }
}
