package be.helb.dao;

import be.helb.model.Image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<Image, String>
{

}
