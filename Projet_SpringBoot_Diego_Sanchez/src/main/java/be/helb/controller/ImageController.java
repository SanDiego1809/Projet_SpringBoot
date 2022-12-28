package be.helb.controller;

import be.helb.model.Image;
import be.helb.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
public class ImageController
{
    private ImageService imageService;

    public ImageController(ImageService imageService)
    {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("file")MultipartFile file)
    {
        try {
            imageService.store(file);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully uploaded " +file.getOriginalFilename());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/images")
    public ResponseEntity<List<Image>> getListFiles() {


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
