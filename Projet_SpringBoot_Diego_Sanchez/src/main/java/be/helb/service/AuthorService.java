package be.helb.service;

import be.helb.dao.AuthorDao;
import be.helb.model.Album;
import be.helb.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService
{
    private AuthorDao authorDao;

    //injection par constructeur --> évite autowired
    public AuthorService(AuthorDao authorDao)
    {
        this.authorDao = authorDao;
    }

    public List<Author> getAll()
    {
        return authorDao.findAll();
    }

    public Author createAuthor(Author author)
    {
        return authorDao.save(author);
    }

    public void deleteAuthorById(Long authorId)
    {
        Author author = authorDao.findById(authorId).get();
        for (Album album: author.getAlbums())
        {
            album.getAuthors().remove(author);
        }
        author.getAlbums().clear();
        authorDao.deleteById(authorId);
    }
    public void deleteAuthorByName(String name)
    {
        List<Author> authorsList = authorDao.findByName(name);

        for (Author author: authorsList)
        {
            for (Album album: author.getAlbums())
            {
                album.getAuthors().remove(author);
            }
            author.getAlbums().clear();
        }
        authorDao.deleteAll(authorsList);

    }
    public void deleteAllAuthors()
    {
        List<Author> list = authorDao.findAll();
        for (Author author: list)
        {
            for (Album album: author.getAlbums())
            {
                album.getAuthors().clear();
            }
            author.getAlbums().clear();
        }

        authorDao.deleteAll();
    }

    public Author updateAuthorById(Author author, Long authorId)
    {
        Author depDB = authorDao.findById(authorId).get();

        depDB.setName(author.getName());
        depDB.setFirstName(author.getFirstName());
        depDB.setCountry(author.getCountry());
        depDB.setDateOfBirth(author.getDateOfBirth());
        depDB.setDateOfDeath(author.getDateOfDeath());

        return authorDao.save(depDB);
    }
    public List<Author> getAuthorByName(String name)
    {
        List<Author> authors = authorDao.findByNameContainsIgnoreCase(name);
        return authors;
    }
    public Author getAuthorById(Long id)
    {

        Author author = authorDao.findById(id).get();
        return author;
    }

    public Set<Album> getAllAlbumsByAuthorId(Long authorId)
    {
        Author author = getAuthorById(authorId);
        Set<Album> albums = author.getAlbums();
        return albums;
    }
    public List<Author> getAllAuthorsByCountry(String country)
    {
        return authorDao.findAuthorByCountryContainsIgnoreCase(country);
    }
}
