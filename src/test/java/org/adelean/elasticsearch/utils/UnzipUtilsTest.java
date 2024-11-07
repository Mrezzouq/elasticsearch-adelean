package org.adelean.elasticsearch.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UnzipUtilsTest {
    private static final String TEST_ZIP_FILE = "src/test/resources/test.zip";
    private static final String OUTPUT_DIRECTORY = "src/test/resources/output";

    @InjectMocks
    private UnzipUtils unzipUtils;

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_ZIP_FILE));
        Files.walk(Paths.get(OUTPUT_DIRECTORY))
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    void testUnzip_successfulExtraction() throws IOException {
        // Arrange
        createTestZipFile();

        // Act
        var extractedFilePath = unzipUtils.unzip(TEST_ZIP_FILE, OUTPUT_DIRECTORY);

        // Assert
        var extractedFile = new File(extractedFilePath);
        assertThat(extractedFile).exists().isFile();
        assertThat(Files.readString(extractedFile.toPath())).isEqualTo("<root><test>value</test></root>");
    }

    @Test
    void testUnzip_throwsIOException() {
        // Arrange
        var nonExistentZipFilePath = "non_existent.zip";

        // Act & Assert
        assertThrows(IOException.class, () -> unzipUtils.unzip(nonExistentZipFilePath, OUTPUT_DIRECTORY));
    }

    private void createTestZipFile() throws IOException {
        var zipFilePath = Paths.get(TEST_ZIP_FILE);
        Files.createDirectories(zipFilePath.getParent());
        try (var zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
            var entry = new ZipEntry("test.xml");
            zipOutputStream.putNextEntry(entry);
            zipOutputStream.write("<root><test>value</test></root>".getBytes());
            zipOutputStream.closeEntry();
        }
    }
}