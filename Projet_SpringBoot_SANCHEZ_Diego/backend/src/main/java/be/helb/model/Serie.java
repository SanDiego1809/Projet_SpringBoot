package be.helb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity// va créer automatiquement la table
@Table(name = "series")
public class Serie implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;

    @Column(name="numberofvolumes")
    private int numberOfVolumes;//nombre de tomes d'une bande dessinée

    private String origin;
    private String language;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Album> albums;

    public Serie()
    {

    }
    public Serie(String name)
    {
        this.name = name;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumberOfVolumes() {
        return numberOfVolumes;
    }

    public void setNumberOfVolumes(int numberOfVolumes) {
        this.numberOfVolumes = numberOfVolumes;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
