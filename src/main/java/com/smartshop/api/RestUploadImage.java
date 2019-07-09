package com.smartshop.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import com.smartshop.service.store.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/img")
public class RestUploadImage {

    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

    @PostMapping("/phone")
    public ResponseEntity<String> uploadImgPhone(@RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.storeImgPhone(file);
            files.add(file.getOriginalFilename());
            message = "You succeessfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/laptop")
    public ResponseEntity<String> uploadImgLaptop(@RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.storeImgLaptop(file);
            files.add(file.getOriginalFilename());
            message = "You succeessfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/tablet")
    public ResponseEntity<String> uploadImgTablet(@RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.storeImgTablet(file);
            files.add(file.getOriginalFilename());
            message = "You succeessfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/news")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.storeImgNews(file);
            files.add(file.getOriginalFilename());
            message = "You succeessfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    /*@GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }*/

    @GetMapping("/phone/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImgPhone(@PathVariable String filename) {
        Resource file = storageService.loadImgPhone(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/tablet/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImgTablet(@PathVariable String filename) {
        Resource file = storageService.loadImgTablet(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/laptop/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImgLaptop(@PathVariable String filename) {
        Resource file = storageService.loadImgLaptop(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/news/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImgNews(@PathVariable String filename) {
        Resource file = storageService.loadImgNews(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
