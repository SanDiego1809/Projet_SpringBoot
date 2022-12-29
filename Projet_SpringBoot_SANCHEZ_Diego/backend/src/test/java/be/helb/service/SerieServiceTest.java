package be.helb.service;

import be.helb.dao.SerieDao;
import be.helb.model.Serie;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerieServiceTest
{
    private SerieDao serieDaoMock;
    private SerieService serieService;
    @Test
    public void testGetAllAuthors()
    {
        List<Serie> seriesList = List.of(new Serie("test"), new Serie("test2"));

        serieDaoMock = EasyMock.mock(SerieDao.class);
        EasyMock.expect(serieDaoMock.findAll()).andReturn(seriesList);

        serieService = new SerieService(serieDaoMock);
        EasyMock.replay(serieDaoMock);
        List<Serie> result = serieService.getAll();
        EasyMock.verify(serieDaoMock);
        assertEquals(seriesList, result);
    }
    @Test
    public void testGetAllSeriesByGenre()
    {

        List<Serie> seriesList = List.of(new Serie("test"), new Serie("test2"));

        seriesList.get(0).setGenre("genre1");
        seriesList.get(1).setGenre("genre2");

        serieDaoMock = EasyMock.mock(SerieDao.class);
        EasyMock.expect(serieDaoMock.findSerieByGenreContainsIgnoreCase("genre1")).andReturn(seriesList);

        serieService = new SerieService(serieDaoMock);
        EasyMock.replay(serieDaoMock);
        List<Serie> result = serieService.getAllSeriesByGenre("genre1");
        EasyMock.verify(serieDaoMock);

        assertEquals(seriesList, result);
    }
}
