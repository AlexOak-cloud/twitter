package doob.services;


import doob.entity.Image;
import doob.repositoryes.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.util.Optional;

@Service
public class ImageService {

    public static final String path = "E:/Code/Image/";

    @Autowired
    private ImageRepository imageRepository;

    public void save(Image image, MultipartFile file){
        try(FileWriter fileWriter = new FileWriter(file)){

        }

        imageRepository.save(image);
    }

    public Image findBuId(int id){
        return imageRepository.findById(id).get();
    }

    public void deleteById(int id){
        imageRepository.deleteById(id);
    }

    public void delete(Image image){
        imageRepository.delete(image);
    }
}
