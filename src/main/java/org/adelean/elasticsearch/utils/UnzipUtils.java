package org.adelean.elasticsearch.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnzipUtils.class);

    private UnzipUtils() {
    }

    public String unzip(String zipFilePath, String outputDirectory) throws IOException {
        var destDir = createOutputDirectory(outputDirectory);
        try (var zipFile = new ZipFile(zipFilePath)) {
            var entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                var filePath = destDir.resolve(entry.getName());
                extractEntry(zipFile, entry, filePath);
            }
        } catch (IOException e) {
            logAndThrowError("Failed to unzip file: " + zipFilePath, e);
        }
        return getXmlFilePath(zipFilePath, destDir);
    }

    private static Path createOutputDirectory(String outputDirectory) throws IOException {
        var destDir = Paths.get(outputDirectory);
        if (Files.notExists(destDir)) {
            Files.createDirectories(destDir);
        }
        return destDir;
    }

    private static void extractEntry(ZipFile zipFile, ZipEntry entry, Path filePath) throws IOException {
        if (entry.isDirectory()) {
            Files.createDirectories(filePath);
        } else {
            Files.createDirectories(filePath.getParent());
            try (var inputStream = zipFile.getInputStream(entry)) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                logAndThrowError("Failed to extract file: " + filePath, e);
            }
        }
    }

    private static void logAndThrowError(String message, IOException e) throws IOException {
        LOGGER.error(message, e);
        throw e;
    }

    private static String getXmlFilePath(String zipFilePath, Path destDir) {
        var xmlFileName = new File(zipFilePath).getName().replace(".zip", ".xml");
        return destDir.resolve(xmlFileName).toString();
    }
}
