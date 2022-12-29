package be.helb.dao;

import be.helb.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Long>
{
    List<Author> findByName(String name);
    List<Author> findByNameContainsIgnoreCase(String name);
    List<Author> findAuthorByCountryContainsIgnoreCase(String country);
}
