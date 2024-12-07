package com.quyet.osahan_eat.service.impl;

import com.quyet.osahan_eat.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FilesStorageService {
    @Value("${fileUpload.rootPath}")
    private String rootPath;

    private Path root;

    public void init() {
        try {
            root = Paths.get(rootPath);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (Exception e) {
            System.out.println("error create folder root" + e.getMessage());
        }
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        try {
            init();
            Files.copy(file.getInputStream(),
                    root.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING); // remove if file existed
            return true;
        } catch (Exception e) {
            System.out.println("error save file" + e.getMessage());
            return false;
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            init();
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (Exception e) {
            System.out.println("error load file" + e.getMessage());
        }
        return null;
    }
}
