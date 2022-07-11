package com.epam.cinema.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Image uploading service.
 */
public interface IImageUploadingService {

    /**
     * Save file in folder. Path to this folder sets
     * in implementation of this interface. Return the
     * string what contain path what sets too.
     *
     * @param file - not null
     * @return string - path so saved file
     */
    String save(MultipartFile file);

    /**
     * Load resource.
     *
     * @param filename the filename
     * @return the resource
     */
    Resource load(String filename);
}
