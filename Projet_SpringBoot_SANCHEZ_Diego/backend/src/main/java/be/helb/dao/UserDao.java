package be.helb.dao;

import be.helb.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<ApplicationUser, Long>
{
    ApplicationUser findByUsername(String username);
}
