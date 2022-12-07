package be.helb.controller;

import be.helb.model.Author;
import be.helb.model.Serie;
import be.helb.service.SerieService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SerieController
{
    private SerieService serieService;

    public SerieController(SerieService serieService)
    {
        this.serieService = serieService;
    }

    @GetMapping("series")
    public List<Serie> getSeriesList()
    {
        return serieService.getAll();
    }

    @PostMapping("/serie")
    public Serie createSerie(@Valid @RequestBody Serie serie)
    {
        return serieService.createSerie(serie);
    }

    @DeleteMapping("/serie/{id}")
    public String deleteSerieById(@PathVariable("id") Long serieId)
    {
        serieService.deleteSerieById(serieId);
        return "Deleted Successfully";
    }
    @PutMapping("/serie/{id}")
    public Serie updateSerie(@RequestBody Serie serie,@PathVariable("id") Long serieId)
    {
        return serieService.updateSerie(serie, serieId);
    }
}
