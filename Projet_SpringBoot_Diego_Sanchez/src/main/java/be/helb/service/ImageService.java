package be.helb.service;

import be.helb.dao.ImageDao;
import be.helb.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService
{
    private ImageDao imageDao;

    public ImageService(ImageDao imageDao)
    {
        this.imageDao = imageDao;
    }

    public Image store(MultipartFile file) throws IOException
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(fileName, file.getContentType(), file.getBytes());

        return imageDao.save(image);
    }


}
