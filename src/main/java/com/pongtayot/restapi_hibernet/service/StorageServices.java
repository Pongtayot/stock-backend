package com.pongtayot.restapi_hibernet.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageServices {
    void init();
    String store(MultipartFile file);
}
