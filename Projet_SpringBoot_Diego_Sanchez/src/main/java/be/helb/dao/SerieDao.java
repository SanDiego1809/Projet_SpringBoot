package be.helb.dao;

import be.helb.model.Author;
import be.helb.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieDao extends JpaRepository<Serie, Long>
{
    //List<Serie> findByName(String name);
}
