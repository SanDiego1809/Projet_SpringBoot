package be.helb.service;

import be.helb.dao.AuthorDao;
import be.helb.model.Author;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorServiceTest
{
    private AuthorDao authorDaoMock;
    private AuthorService authorService;
    @Test
    public void testGetAllAuthors()
    {
        List<Author> authorsList = List.of(new Author("test"), new Author("test2"));

        authorDaoMock = EasyMock.mock(AuthorDao.class);
        EasyMock.expect(authorDaoMock.findAll()).andReturn(authorsList);

        authorService = new AuthorService(authorDaoMock);
        EasyMock.replay(authorDaoMock);
        List<Author> result = authorService.getAll();
        EasyMock.verify(authorDaoMock);
        assertEquals(authorsList, result);
    }
    @Test
    public void testGetAllAuthorsByCountry()
    {

        List<Author> authorsList = List.of(new Author("test"), new Author("test2"));

        authorsList.get(0).setCountry("country1");
        authorsList.get(1).setCountry("country2");

        authorDaoMock = EasyMock.mock(AuthorDao.class);
        EasyMock.expect(authorDaoMock.findAuthorByCountryContainsIgnoreCase("country1")).andReturn(authorsList);

        authorService = new AuthorService(authorDaoMock);
        EasyMock.replay(authorDaoMock);
        List<Author> result = authorService.getAllAuthorsByCountry("country1");
        EasyMock.verify(authorDaoMock);

        assertEquals(authorsList, result);
    }
}
