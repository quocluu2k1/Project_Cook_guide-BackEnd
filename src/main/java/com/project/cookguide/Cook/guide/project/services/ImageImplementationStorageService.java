package com.project.cookguide.Cook.guide.project.services;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageImplementationStorageService {
    Logger logger = LoggerFactory.getLogger(Logger.class);

    private final Path storageFolder = Paths.get("src/main/resources/static/images/implementation");

    public ImageImplementationStorageService(){
        try {
            Files.createDirectories(storageFolder);
        }catch (IOException e){
            throw new RuntimeException("cannot initialize storage",e);
        }
    }
    private Boolean isImageFile(MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png","jpg","jpeg","bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }

    public String storeFile(MultipartFile file) {
        try {
            if(file.isEmpty()){
//                throw new RuntimeException("Failed to store empty file");
                return "Failed to store empty file";
            }
            if(!isImageFile(file)){
//                throw new RuntimeException("You can only upload image file");
                return "You can only upload image file";
            }
            float fileSizeInMegabytes = file.getSize()/1000000.0f;
            if(fileSizeInMegabytes>10.0f){
//                throw new RuntimeException("File must be <= 10MB");
                return "File must be <= 10MB";
            }
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-","");
            generatedFileName = generatedFileName + "." + fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(
                            Paths.get(generatedFileName))
                    .normalize().toAbsolutePath();
            if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("cannot store file outside current directory");
            }
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream,destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        }catch (IOException e){
            throw new RuntimeException("failed to store file",e);
        }
    }

    public void deleteFile(String urlFile){
        if(urlFile!=null){
            String fileName = urlFile.replaceAll("/images/implementation/","");
            Path filePath = this.storageFolder.resolve(
                            Paths.get(fileName))
                    .normalize().toAbsolutePath();
            try {
                Files.deleteIfExists(filePath);
            }catch (Exception e){

            }
        }


    }

    public Stream<Path> loadAll() {
        return null;
    }

    public byte[] readFileContent(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }
            else {
                throw new RuntimeException(
                        "Could not read file: " + fileName);
            }
        }
        catch (IOException exception) {
            throw new RuntimeException("Could not read file: " + fileName, exception);
        }
    }

    public void deleteAllFiles() {

    }
}
