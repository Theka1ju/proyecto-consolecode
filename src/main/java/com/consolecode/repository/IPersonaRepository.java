package com.consolecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consolecode.model.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {

}
