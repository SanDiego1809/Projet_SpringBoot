package be.helb.dao;

import be.helb.model.Album;
import be.helb.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Long>
{
    Author findByName(String name);

}
