package be.helb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity// va créer automatiquement la table
@Table(name = "authors")
public class Author implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "firstname")
    private String firstName;

    private String country;

    @Column(name="dateofbirth")
    private LocalDate dateOfBirth;

    @Column(name="dateofdeath")
    private LocalDate dateOfDeath;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private Set<Album> albums = new HashSet<>();



    public void addAlbum(Album album) {
        this.albums.add(album);
        album.getAuthors().add(this);
    }

    public void removeAlbum(Album album) {
        this.albums.remove(album);
        album.getAuthors().remove(this);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
