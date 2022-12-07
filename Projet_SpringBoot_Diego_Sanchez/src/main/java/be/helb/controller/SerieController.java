package be.helb.controller;

import be.helb.model.Author;
import be.helb.model.Serie;
import be.helb.service.SerieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
