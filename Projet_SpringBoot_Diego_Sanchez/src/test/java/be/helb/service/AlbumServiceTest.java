package be.helb.service;

import be.helb.dao.AlbumDao;
import be.helb.model.Album;
import be.helb.model.Serie;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlbumServiceTest
{
    //@Autowired --> ne fonctionne pas pcq la classe n'est pas un bin (Pas d'annotation)
    //private AlbumService albumService;

    //Impossible d'utiliser le DAO qui se trouve dans le dossier main
    //Mocker = créer un faux DAO qui va renvoyer des fausses données

    //trois types d'erreurs: problème assert equals --> valeurs de retour
    //                       si le test a 2 find all
    //                       si on a pas mis un verify

    //replay->appel-> verify

    //Faire minimum de tests pertinents


    private AlbumDao albumDaoMock;

    private AlbumService albumService; //= new AlbumService();


    @Test
    public void testGetAllAlbums()
    {
        List<Album> albumsList = List.of(new Album("test"), new Album("test2"));

        albumDaoMock = EasyMock.mock(AlbumDao.class);
        EasyMock.expect(albumDaoMock.findAll()).andReturn(albumsList);

        albumService = new AlbumService(albumDaoMock);
        EasyMock.replay(albumDaoMock);
        List<Album> result = albumService.getAll();
        EasyMock.verify(albumDaoMock);
        assertEquals(albumsList, result);
    }

    @Test
    public void testGetAllAlbumsBySerie()
    {
        List<Album> albumsList = List.of(new Album("test"), new Album("test2"));
        Serie serie1 = new Serie("test");
        albumsList.get(0).setSerie(serie1);
        albumsList.get(1).setSerie(serie1);

        /*albumList.get(1).getAlbumList.add(new Album());*/

        albumDaoMock = EasyMock.mock(AlbumDao.class);
        EasyMock.expect(albumDaoMock.findBySerieId(serie1.getId())).andReturn(albumsList);

        albumService = new AlbumService(albumDaoMock);
        EasyMock.replay(albumDaoMock);
        List<Album> result = albumService.getAllAlbumsBySerieId(serie1.getId());
        EasyMock.verify(albumDaoMock);
        assertEquals(albumsList, result);
    }


}
