package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.PersonCreationDto;
import com.betrybe.agrix.controllers.dtos.PersonDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person response entity.
   *
   * @param newPerson the new person
   * @return the response entity
   */
  @PostMapping()
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonCreationDto newPerson) {
    Person person = personService.create(newPerson.toPerson());
    PersonDto personDto = new PersonDto(person.getId(),
        person.getUsername(), person.getRole());
    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
  }
}
