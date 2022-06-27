package com.epam.cinema.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IImageUploadingService {
    String save(MultipartFile file);

    Resource load(String filename);
}
