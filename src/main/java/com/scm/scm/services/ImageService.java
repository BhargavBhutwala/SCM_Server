package com.scm.scm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

   String uploadImage(MultipartFile picture);

   String getURLFromPublicId(String publicId);

}
