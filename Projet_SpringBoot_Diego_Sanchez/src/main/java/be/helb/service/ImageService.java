package be.helb.service;

import be.helb.dao.ImageDao;
import be.helb.dao.UserDao;
import be.helb.model.Image.Image;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ImageService
{
    private ImageDao imageDao;
    private UserDao userDao;

    public ImageService(ImageDao imageDao, UserDao userDao)
    {
        this.imageDao = imageDao;
        this.userDao = userDao;
    }

    public Image store(MultipartFile file) throws IOException
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(fileName, file.getContentType(), file.getBytes());

        return imageDao.save(image);
    }
    public Image getFile(String id) {
        return imageDao.findById(id).get();
    }

    public Stream<Image> getAllFiles() {
        return imageDao.findAll().stream();
    }


}
