package be.helb.controller;

import be.helb.model.Album;
import be.helb.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Album>> getAlbumsList()
    {
        try {
            List<Album> albumsList = albumService.getAll();
            if (albumsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albumsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/album")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album)
    {
        try {
            return new ResponseEntity<>(albumService.createAlbum(album), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/album/{id}")
    public ResponseEntity<HttpStatus> deleteAlbumById(@PathVariable("id") Long albumId)
    {
        try {
            albumService.deleteAlbumById(albumId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/albumName/{name}")
    public ResponseEntity<HttpStatus> deleteAlbumByName(@PathVariable("name") String albumName)
    {
        try {
            albumService.deleteAlbumByName(albumName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/albums")
    public ResponseEntity<HttpStatus> deleteAllAlbums()
    {
        try {
            albumService.deleteAllAlbums();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/album/{id}")
    public ResponseEntity<Album> updateAlbum(@RequestBody Album album,@PathVariable("id") Long albumId)
    {
        try{
            return new ResponseEntity<>(albumService.updateAlbum(album, albumId),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/album/{name}")
    public ResponseEntity<List<Album>> getAlbumByName(@PathVariable (value = "name") String name)
    {
        try {
            List<Album> albumsList = albumService.getAlbumByName(name);
            if (albumsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albumsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") long id)
    {
        try{
            return new ResponseEntity<>(albumService.getAlbumById(id),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/albumBySerieId/{id}")
    public ResponseEntity<List<Album>> getAllAlbumsBySerie(@PathVariable (value = "id") Long serieId)
    {
        try {
            List<Album> albumsList =  albumService.getAllAlbumsBySerieId(serieId);
            if (albumsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albumsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
