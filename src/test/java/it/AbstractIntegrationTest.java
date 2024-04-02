package it;

import com.anst.sd.telegram.TelegramApplication;
import com.anst.sd.telegram.adapter.bot.MyTelegramBot;
import com.anst.sd.telegram.adapter.persistence.UserMongoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootTest(classes = {TelegramApplication.class})
@ActiveProfiles({"test"})
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected UserMongoRepository userMongoRepository;
    @Autowired
    protected MyTelegramBot myTelegramBot;

    // ===================================================================================================================
    // = ObjectMapper utils
    // ===================================================================================================================

    protected <T> T mapToObject(String string, Class<?> clazz, Class<?>... classes) {
        try {
            if (classes.length == 0) {
                return (T) objectMapper.readValue(string, clazz);
            }

            Class<?>[] newClasses = ArrayUtils.addFirst(classes, clazz);
            JavaType currentType = null;
            for (int i = newClasses.length - 1; i > 0; i--) {
                if (currentType == null) {
                    currentType =
                            objectMapper.getTypeFactory().constructParametricType(newClasses[i - 1], newClasses[i]);
                } else {
                    currentType = objectMapper.getTypeFactory().constructParametricType(newClasses[i - 1], currentType);
                }
            }

            return objectMapper.readValue(string, currentType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ===================================================================================================================
    // = File utils
    // ===================================================================================================================

    protected String readFile(String fileName) {
        try {
            URL resource = getClass().getResource(fileName);
            return Files.readString(Paths.get(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected <T> T readFromFile(String fileName) {
        String content = readFile(fileName);
        try {
            return objectMapper.readValue(content, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T readFromFile(String fileName, Class<?> clazz, Class<?>... classes) {
        String content = readFile(fileName);
        return mapToObject(content, clazz, classes);
    }

    protected <T> List<T> readListFromFile(String fileName, Class<?>... classes) {
        return readFromFile(fileName, List.class, classes);
    }

    protected void assertEqualsToFile(String fileName, Object value) {
        String expected = readFile(fileName);
        try {
            String actual = objectMapper.writeValueAsString(value);
            Assertions.assertEquals(expected, actual);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    // ===================================================================================================================
    // = Request/Response utils
    // ===================================================================================================================

    protected String getStringFromResponse(MvcResult result) {
        try {
            return result.getResponse().getContentAsString(UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T getFromResponse(MvcResult result, Class<?> clazz, Class<?>... classes) {
        return mapToObject(getStringFromResponse(result), clazz, classes);
    }

    protected <T> List<T> getListFromResponse(MvcResult result, Class<?>... classes) {
        try {
            return getFromResponse(result, List.class, classes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}