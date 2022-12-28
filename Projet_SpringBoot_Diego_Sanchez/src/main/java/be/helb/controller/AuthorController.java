package be.helb.controller;

import be.helb.model.Album;
import be.helb.model.Author;
import be.helb.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController //crée un singleton du controleur
public class AuthorController
{
    private AuthorService authorService;

    public AuthorController(AuthorService authorService)
    {
        this.authorService = authorService;
    }
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthorsList() {
        try
        {
            List<Author> authorsList = authorService.getAll();
            if (authorsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(authorsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author)
    {
        try
        {
            return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable("id") Long authorId)
    {
        try {
            authorService.deleteAuthorById(authorId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/authorName/{name}")
    public ResponseEntity<HttpStatus> deleteAuthorByName(@PathVariable("name") String name)
    {
        try {
            authorService.deleteAuthorByName(name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/authors")
    public ResponseEntity<HttpStatus> deleteAllAuthors()
    {
        try {
            authorService.deleteAllAuthors();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthorById(@RequestBody Author author,@PathVariable("id") Long authorId)
    {
        try{
            return new ResponseEntity<>(authorService.updateAuthorById(author, authorId),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/author/{name}")
    public ResponseEntity<List<Author>> getAuthorByName(@PathVariable("name") String name)
    {
        try
        {
            List<Author> authorsList = authorService.getAuthorByName(name);
            if (authorsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(authorsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") long id)
    {
        try{
            return new ResponseEntity<>(authorService.getAuthorById(id),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/albumsByAuthor/{id}")
    public ResponseEntity<Set<Album>> getAlbumsByAuthorId(@PathVariable("id") long id)
    {
        try
        {
            Set<Album> albumsList = authorService.getAllAlbumsByAuthorId(id);
            if (albumsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albumsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/albumsByCountry/{country}")
    public ResponseEntity<List<Author>> getAuthorsByCountry(@PathVariable("country") String country)
    {
        try
        {
            List<Author> authorsList = authorService.getAllAuthorsByCountry(country);
            if (authorsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(authorsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
