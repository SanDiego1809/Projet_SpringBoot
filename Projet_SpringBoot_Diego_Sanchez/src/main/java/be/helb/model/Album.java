package be.helb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity// va créer automatiquement la table
@Table(name = "albums")
public class Album implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int number;

    private String scriptwriter;
    private String drawer;
    private String colorist;

    private String editor;

    @Column(name="dateofpublication")
    private LocalDate dateOfPublication;

    @Column(name="numberofpages")
    private int numberOfPages;

    /*@ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;*/


    public Album() {

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

    public String getScriptwriter() {
        return scriptwriter;
    }

    public void setScriptwriter(String scriptwriter) {
        this.scriptwriter = scriptwriter;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getColorist() {
        return colorist;
    }

    public void setColorist(String colorist) {
        this.colorist = colorist;
    }

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

    /*public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }*/
}
