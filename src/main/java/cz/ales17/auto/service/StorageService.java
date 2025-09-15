package cz.ales17.auto.service;

import cz.ales17.auto.storage.StorageException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    String store(MultipartFile file) throws StorageException;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);
}
