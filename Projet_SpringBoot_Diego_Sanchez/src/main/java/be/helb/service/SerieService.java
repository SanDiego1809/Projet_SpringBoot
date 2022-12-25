package be.helb.service;

import be.helb.dao.AlbumDao;
import be.helb.dao.SerieDao;
import be.helb.model.Album;
import be.helb.model.Author;
import be.helb.model.Serie;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SerieService
{
    private SerieDao serieDao;

    //injection par constructeur --> évite autowired
    public SerieService(SerieDao serieDao)
    {
        this.serieDao = serieDao;
    }

    public List<Serie> getAll()
    {
        return serieDao.findAll();
    }

    public Serie createSerie(Serie serie)
    {
        return serieDao.save(serie);
    }

    public void deleteSerieById(Long serieId)
    {
        serieDao.deleteById(serieId);
    }

    public void deleteAllSeries()
    {
        serieDao.deleteAll();
    }

    public Serie updateSerie(Serie serie, Long serieId)
    {
        Serie depDB = serieDao.findById(serieId).get();

        depDB.setName(serie.getName());
        depDB.setGenre(serie.getGenre());
        depDB.setNumberOfVolumes(serie.getNumberOfVolumes());
        depDB.setOrigin(serie.getOrigin());
        depDB.setLanguage(serie.getLanguage());

        return serieDao.save(depDB);
    }

    public List<Serie> getSerieByName(String name)
    {

        List<Serie> series = serieDao.findByName(name);
        return series;
    }
    public Serie getSerieById(Long id)
    {

        Serie serie = serieDao.findById(id).get();
        return serie;
    }



    public SerieDao getSerieDao() {
        return serieDao;
    }

    public void setSerieDao(SerieDao serieDao) {
        this.serieDao = serieDao;
    }
}
