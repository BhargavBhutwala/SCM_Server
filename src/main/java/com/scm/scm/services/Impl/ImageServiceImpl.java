package com.scm.scm.services.Impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.scm.helper.AppConstants;
import com.scm.scm.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

   @Autowired
   private Cloudinary cloudinary;

   @Override
   public String uploadImage(MultipartFile picture) {

      // Code to upload the image on the server/aws/cloudinary

      String fileName = UUID.randomUUID().toString();

      try {
         byte[] data = new byte[picture.getInputStream().available()];

         picture.getInputStream().read(data);

         cloudinary.uploader().upload(data, ObjectUtils.asMap(
               "public_id", fileName));

         return this.getURLFromPublicId(fileName);

      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }

   }

   @Override
   public String getURLFromPublicId(String publicId) {

      return cloudinary.url()
            .transformation(new Transformation<>().width(AppConstants.CONTACT_IMAGE_WIDTH)
                  .height(AppConstants.CONTACT_IMAGE_HEIGHT).crop(AppConstants.CONTACT_IMAGE_CROP))
            .generate(publicId);
   }

}
