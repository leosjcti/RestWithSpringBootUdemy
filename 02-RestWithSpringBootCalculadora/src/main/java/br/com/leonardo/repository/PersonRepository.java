package br.com.leonardo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leonardo.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
