package tests.homework.lesson_8;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.homework.lesson_8.model.ClientNameBankInfo;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class JsonFileParsingTest {
    private ClassLoader cl = JsonFileParsingTest.class.getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("JSON: Проверка карт клиента")
    void jsonParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("ClientBankCards.json"),
                StandardCharsets.UTF_8
        )) {
            ClientNameBankInfo actual = mapper.readValue(reader, ClientNameBankInfo.class);

            Assertions.assertEquals("Елена", actual.getFirstName());
            Assertions.assertEquals(12345, actual.getId());
            Assertions.assertEquals("GPB Bank", actual.getCards().getIssuer());
            Assertions.assertEquals("4532123456789012", actual.getCards().getNumber());
            Assertions.assertEquals("Visa Classic", actual.getCards().getType());
        }
    }
}
