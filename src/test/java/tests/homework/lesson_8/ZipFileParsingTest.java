package tests.homework.lesson_8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipFileParsingTest {
    private ClassLoader cl = ZipFileParsingTest.class.getClassLoader();

    @Test
    void zipFileParsingTest() throws Exception {
        List<String> fileNames = new ArrayList<>();

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("CreditCard.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
                fileNames.add(entry.getName());
            }
        }

        assertEquals(3, fileNames.size(), "Должно быть ровно 3 файла в архиве");

        List<String> expectedFiles = Arrays.asList(
                "consent_personal_data_partner_debit_card_04_24.pdf",
                "creditCardNumbers.csv",
                "ProductCards.xlsx"
        );

        for (String expectedFile : expectedFiles) {
            assertTrue(fileNames.contains(expectedFile),
                    "Файл " + expectedFile + " должен присутствовать в архиве");
        }
    }
}
