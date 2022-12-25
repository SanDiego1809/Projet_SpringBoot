package be.helb.service;

import be.helb.dao.AlbumDao;
import be.helb.model.Album;
import be.helb.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AlbumService
{

    private AlbumDao albumDao;

    //injection par constructeur --> évite autowired
    public AlbumService(AlbumDao albumDao)
    {
        this.albumDao = albumDao;
    }

    public List<Album> getAll()
    {
        return albumDao.findAll();
    }


    public Album createAlbum(Album album)
    {
        return albumDao.save(album);
    }

    public void deleteAlbumById(Long albumId)
    {
        albumDao.deleteById(albumId);
    }

    public void deleteAllAlbums()
    {
        albumDao.deleteAll();
    }

    public Album updateAlbum(Album album, Long albumId)
    {
        Album depDB = albumDao.findById(albumId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

        depDB.setName(album.getName());
        depDB.setNumber(album.getNumber());
       /* depDB.setScriptwriter(album.getScriptwriter());
        depDB.setDrawer(album.getDrawer());
        depDB.setColorist(album.getColorist());*/
        depDB.setEditor(album.getEditor());
        depDB.setDateOfPublication(album.getDateOfPublication());
        depDB.setNumberOfPages(album.getNumberOfPages());
        depDB.setSerie(album.getSerie());

        depDB.getAuthors().clear();
        for (Author author: album.getAuthors())
        {
            depDB.getAuthors().add(author);
        }





        //depDB.setAuthors(album.getAuthors());

        return albumDao.save(depDB);
    }

    public List<Album> getAlbumByName(String name)
    {

        List<Album> albums = albumDao.findByName(name);
        return albums;
    }
    public Album getAlbumById(Long id)
    {

        Album album = albumDao.findById(id).get();
        return album;
    }


    public List<Album> getAllAlbumsBySerieId(Long albumId)
    {
        /*if(!albumDao.existsById(albumId))
        {

        }*/
        List<Album> albums = albumDao.findBySerieId(albumId);
        return albums;
    }

    public AlbumDao getAlbumDao()
    {
        return albumDao;
    }

    public void setAlbumDao(AlbumDao albumDao)
    {
        this.albumDao = albumDao;
    }
}
