package be.helb.controller;

import be.helb.model.Album;
import be.helb.service.AlbumService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin
@RestController //crée un singleton du controleur
public class AlbumController
{
    private AlbumService albumService;

    public AlbumController(AlbumService albumService)
    {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<Album> getAlbumsList()
    {
        return albumService.getAll();
    }


    @PostMapping("/album")
    public Album createAlbum(@RequestBody Album album)
    {
        return albumService.createAlbum(album);
    }

    @DeleteMapping("/album/{id}")
    public String deleteAlbumById(@PathVariable("id") Long albumId)
    {
        albumService.deleteAlbumById(albumId);
        return "Deleted Successfully";
    }

    @DeleteMapping("/albums")
    public String deleteAllAlbums()
    {
        albumService.deleteAllAlbums();
        return "Deleted Successfully";
    }
    @PutMapping("/album/{id}")
    public Album updateAlbum(@RequestBody Album album,@PathVariable("id") Long albumId)
    {
        return albumService.updateAlbum(album, albumId);
    }

    @GetMapping("/album/{name}")
    public List<Album> getAlbumByName(@PathVariable (value = "name") String name)
    {
        return albumService.getAlbumByName(name);
    }

    @GetMapping("/albums/{id}")
    public Album getAlbumById(@PathVariable("id") long id)
    {
        return albumService.getAlbumById(id);
    }


    @GetMapping("/series/{id}/albums")
    public List<Album> getAllAlbumsBySerie(@PathVariable (value = "id") Long albumId)
    {
        return albumService.getAllAlbumsBySerieId(albumId);
    }


}
