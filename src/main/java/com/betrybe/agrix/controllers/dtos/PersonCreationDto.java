package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(String username, String password, Role role) {

  /**
   * To person person.
   *
   * @return the person
   */
  public Person toPerson() {
    return new Person(username, password, role);
  }
}
