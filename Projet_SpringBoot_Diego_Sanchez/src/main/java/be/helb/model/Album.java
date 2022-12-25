package be.helb.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity// va créer automatiquement la table
@Table(name = "albums")
public class Album implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int number;

   /* @OneToMany
    private Author scriptwriter;
    private Author drawer;
    private Author colorist;*/

    private String editor;

    @Column(name="dateofpublication")
    private LocalDate dateOfPublication;

    @Column(name="numberofpages")
    private int numberOfPages;

    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;

    @ManyToMany
    @JoinTable(name = "album_authors",
            joinColumns = {@JoinColumn(name = "album_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();


    public Album()
    {

    }
    public Album(String name)
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /*public Author getScriptwriter() {
        return scriptwriter;
    }

    public void setScriptwriter(Author scriptwriter) {
        this.scriptwriter = scriptwriter;
    }

    public Author getDrawer() {
        return drawer;
    }

    public void setDrawer(Author drawer) {
        this.drawer = drawer;
    }

    public Author getColorist() {
        return colorist;
    }

    public void setColorist(Author colorist) {
        this.colorist = colorist;
    }*/

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
