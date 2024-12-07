package com.quyet.osahan_eat.service;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FilesStorageService {

    public boolean saveFile(MultipartFile file);
    Resource loadFile(String fileName);
}
