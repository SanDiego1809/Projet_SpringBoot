package be.helb.dao;

import be.helb.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumDao extends JpaRepository<Album, Long>
{
    List<Album> findByName(String name);
    List<Album> findByNameContainsIgnoreCase(String name);
    List<Album> findBySerieId(Long id);
}
