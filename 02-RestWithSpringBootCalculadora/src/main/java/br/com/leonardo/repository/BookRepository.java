package br.com.leonardo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leonardo.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
