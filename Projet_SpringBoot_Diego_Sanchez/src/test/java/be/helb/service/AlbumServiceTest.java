package be.helb.service;

import be.helb.dao.AlbumDao;
import be.helb.model.Album;
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
    public void testGetBestAlbum()
    {
        List<Album> albumList = List.of(new Album("Tintin"), new Album("Tintin2"));
        //albumList.get(1).getAlbumList.add(new Album());
        //albumList.get(1).getAlbumList.add(new Album());
        //albumList.get(1).getAlbumList.add(new Album());

        albumDaoMock = EasyMock.mock(AlbumDao.class);
        EasyMock.expect(albumDaoMock.findAll()).andReturn(albumList);

        albumService = new AlbumService(albumDaoMock);
        List<Album> result = albumService.getAll();
        EasyMock.verify();
        assertEquals(2, result.size());
    }


}
