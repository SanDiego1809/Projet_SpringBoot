package be.helb.service;

import be.helb.dao.AlbumDao;
import be.helb.dao.AuthorDao;
import be.helb.dao.SerieDao;
import be.helb.model.Album;
import be.helb.model.Author;
import be.helb.model.Serie;
import org.springframework.stereotype.Service;

import java.util.List;
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
        authorDao.deleteById(authorId);
    }

    public Author updateAuthor(Author author, Long authorId)
    {
        Author depDB = authorDao.findById(authorId).get();


        depDB.setName(author.getName());
        depDB.setFirstName(author.getFirstName());
        depDB.setCountry(author.getCountry());
        depDB.setDateOfBirth(author.getDateOfBirth());
        depDB.setDateOfDeath(author.getDateOfDeath());


        return authorDao.save(depDB);
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
}
