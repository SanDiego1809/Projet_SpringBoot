package be.helb.controller;

import be.helb.model.Album;
import be.helb.model.Author;
import be.helb.service.AlbumService;
import be.helb.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Author> getAuthorsList() {
        return authorService.getAll();
    }

    @PostMapping("/author")
    public Author createAuthor(@RequestBody Author author)
    {
        return authorService.createAuthor(author);
    }

    @DeleteMapping("/author/{id}")
    public String deleteAuthorById(@PathVariable("id") Long authorId)
    {
        authorService.deleteAuthorById(authorId);
        return "Deleted Successfully";
    }

    @DeleteMapping("/authors")
    public String deleteAllAuthors()
    {
        authorService.deleteAllAuthors();
        return "Deleted Successfully";
    }
    @PutMapping("/author/{id}")
    public Author updateAuthor(@RequestBody Author author,@PathVariable("id") Long authorId)
    {
        return authorService.updateAuthor(author, authorId);
    }

    @GetMapping("/author/{name}")
    public Author getAuthorByName(@PathVariable("name") String name)
    {
        return authorService.getAuthorByName(name);
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable("id") long id)
    {
        return authorService.getAuthorById(id);
    }

}
