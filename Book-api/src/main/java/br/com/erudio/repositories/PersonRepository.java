package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Person;

//cria um crud para a tabela automaticamente
public interface PersonRepository extends JpaRepository<Person, Long>{}


