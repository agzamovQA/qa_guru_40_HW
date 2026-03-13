package tests.homework.lesson_8;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
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
    @DisplayName("ZIP: Проверка наличия 3-х файлов")
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

    @Test
    @DisplayName("PDF: Проверка согласий на обработку перс. данных")
    void pdfFileFromZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("CreditCard.zip"))) {
            ZipEntry entry;
            boolean pdfFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Найден файл: " + entry.getName());

                if (entry.getName().endsWith("consent_personal_data_partner_debit_card_04_24.pdf")) {
                    pdfFound = true;

                    PDF pdf = new PDF(zis);

                    System.out.println("Содержимое PDF:");
                    System.out.println(pdf.text);

                    Assertions.assertTrue(pdf.text.contains("Согласие на обработку персональных данных"));

                    break;
                }
            }

            Assertions.assertTrue(pdfFound, "В архиве не найден PDF файл");
        }

    }

    @Test
    @DisplayName("CSV: Проверка заполнения данных по Кредитным картам")
    void csvFileFromZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("CreditCard.zip"))) {
            ZipEntry entry;
            boolean csvFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Найден файл: " + entry.getName());

                if (entry.getName().endsWith("creditCardNumbers.csv")) {
                    csvFound  = true;

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));

                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(2, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"VISA", "4771890110101317", "180CC_90"},
                            data.get(0)
                    );
                    Assertions.assertArrayEquals(
                            new String[] {"MIR", "4444000011132819", "360CC_180"},
                            data.get(1)
                    );

                    break;
                }
            }

            Assertions.assertTrue(csvFound, "В архиве не найден PDF файл");
        }

    }

    @Test
    @DisplayName("XLS: Поиск продукта VISA Universal 180 в таблице")
    void xlsFileFromZipTest1() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("CreditCard.zip"))) {
            ZipEntry entry;
            boolean xlsFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Найден файл: " + entry.getName());

                if (entry.getName().endsWith("ProductCards.xlsx")) {
                    xlsFound  = true;

                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue();

                    Assertions.assertTrue(actualValue.contains("VISA Universal 180"));

                    break;
                }
            }

            Assertions.assertTrue(xlsFound, "В архиве не найден XLS файл");
        }
    }
}
