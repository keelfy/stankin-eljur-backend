package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailAttachmentService {

    public List<File> listAttachmentsInDirectory(String directory) {
        final var directoryFile = new File(directory);
        return listAttachmentsInDirectory(directoryFile);
    }

    public List<File> listAttachmentsInDirectory(File directory) {
        if (directory.exists() || !directory.isDirectory()) {
            log.debug("Directory '" + directory + "' not found");
            return List.of();
        }

        try (Stream<Path> paths = Files.list(directory.toPath())) {
            return paths
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            log.error("Error occurred while listing attachments", ex);
            return List.of();
        }
    }

}
