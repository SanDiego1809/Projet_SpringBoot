package be.helb.controller;

import be.helb.model.Album;
import be.helb.model.Author;
import be.helb.service.AlbumService;
import be.helb.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //crée un singleton du controleur
public class AuthorController
{
    private AuthorService authorService;

    public AuthorController(AuthorService authorService)
    {
        this.authorService = authorService;
    }

    @GetMapping("authors")
    public List<Author> getAuthorsList()
    {
        return authorService.getAll();
    }

    /*@PostMapping
    public String addAlbum(@Valid Album album, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "add-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }*/

}
