package com.quyet.osahan_eat.controller;

import com.quyet.osahan_eat.payload.ResponseData;
import com.quyet.osahan_eat.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    FilesStorageService filesStorageService;

    @PostMapping("")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file) {
        ResponseData responseData = new ResponseData();
        responseData.setData(filesStorageService.saveFile(file));
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        Resource file = filesStorageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);

    }
}
