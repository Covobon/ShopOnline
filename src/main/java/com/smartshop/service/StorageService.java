package com.smartshop.service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("./src/main/resources");

    private final Path imgPhone = Paths.get("./src/main/resources/img/Phone");

    private final Path imgLaptop = Paths.get("./src/main/resources/img/Laptop");

    private final Path imgTablet = Paths.get("./src/main/resources/img/Tablet");

    private final Path imgNews = Paths.get("./src/main/resources/img/News");

    private final Path imgOther = Paths.get("./src/main/resources/img/other");


    public void store(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch(Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void storeImgPhone(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.imgPhone.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Save file fail!");
        }
    }

    public void storeImgLaptop(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.imgLaptop.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Save file fail!");
        }
    }

    public void storeImgTablet(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.imgTablet.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Save file fail!");
        }
    }

    public void storeImgNews(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.imgNews.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Save file fail!");
        }
    }

    public Resource loadFile(String filename, Path path) {
        try {
            Path file = path.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadImgPhone(String nameFile) {
        return loadFile(nameFile, imgPhone);
    }

    public Resource loadImgLaptop(String nameFile) {
        return loadFile(nameFile, imgLaptop);
    }

    public Resource loadImgTablet(String nameFile) {
        return loadFile(nameFile, imgTablet);
    }

    public Resource loadImgNews(String nameFile) {
        return loadFile(nameFile, imgNews);
    }

    public Resource loadImgOthers(String nameFile) {
        return loadFile(nameFile, imgOther);
    }

}
