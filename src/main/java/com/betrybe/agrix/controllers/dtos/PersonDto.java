package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.security.Role;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, Role role) {

}
