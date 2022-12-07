package be.helb.controller;

import be.helb.model.Album;
import be.helb.service.AlbumService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //crée un singleton du controleur
public class AlbumControlller
{
    private AlbumService albumService;

    public AlbumControlller(AlbumService albumService)
    {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<Album> getAlbumsList()
    {
        return albumService.getAll();
    }

    //create Album
    @PostMapping("/create")
    public Album createAlbum(@Valid @RequestBody Album album)
    {
        return albumService.createAlbum(album);
    }

    @DeleteMapping("/album/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long albumId)
    {
        albumService.deleteAlbumById(albumId);
        return "Deleted Successfully";
    }
    @PutMapping("/album/{id}")
    public Album updateDepartment(@RequestBody Album album,@PathVariable("id") Long albumId)
    {
        return albumService.updateAlbum(album, albumId);
    }


}
