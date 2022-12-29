package be.helb.controller;

import be.helb.model.Serie;
import be.helb.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class SerieController
{
    private SerieService serieService;

    public SerieController(SerieService serieService)
    {
        this.serieService = serieService;
    }

    @GetMapping("/series")
    public ResponseEntity<List<Serie>> getSeriesList()
    {
        try {
            List<Serie> seriesList = serieService.getAll();
            if (seriesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(seriesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/serie")
    public ResponseEntity<Serie> createSerie(@RequestBody Serie serie)
    {
        try {
            return new ResponseEntity<>(serieService.createSerie(serie), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/serie/{id}")
    public ResponseEntity<HttpStatus> deleteSerieById(@PathVariable("id") Long serieId)
    {
        try {
            serieService.deleteSerieById(serieId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/serieName/{name}")
    public ResponseEntity<HttpStatus> deleteSerieByName(@PathVariable("name") String serieName)
    {
        try {
            serieService.deleteSerieByName(serieName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/series")
    public ResponseEntity<HttpStatus> deleteAllSeries()
    {
        try {
            serieService.deleteAllSeries();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/serie/{id}")
    public ResponseEntity<Serie> updateSerie(@RequestBody Serie serie,@PathVariable("id") Long serieId)
    {
        try{
            return new ResponseEntity<>(serieService.updateSerie(serie, serieId),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/serie/{name}")
    public ResponseEntity<List<Serie>> getSerieByName(@PathVariable (value = "name") String name)
    {
        try {
            List<Serie> seriesList = serieService.getSerieByName(name);
            if (seriesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(seriesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/series/{id}")
    public ResponseEntity<Serie> getSerieById(@PathVariable("id") long id)
    {
        try{
            return new ResponseEntity<>(serieService.getSerieById(id),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/seriesByGenre/{genre}")
    public ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable("genre") String genre)
    {
        try {
            List<Serie> seriesList = serieService.getAllSeriesByGenre(genre);
            if (seriesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(seriesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
