package util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.EnvProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.slf4j.LoggerFactory;
import reporter.LogToReporterAppender;

import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static helper.AppConstants.*;


@Slf4j
public class UtilityGeneric {
    public static final int RANDOM_UPPER_LIMIT = 30;
    private static final org.slf4j.Logger APPLICATION_LOGGER = org.slf4j.LoggerFactory.getLogger(UtilityGeneric.class);
    static String sectionName;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void getMessage(String message, String expectedMessage, String actualMessage) {
        Assert.assertEquals(message, expectedMessage, actualMessage);
    }

    public static String getDate(int numberOfDays) {
        APPLICATION_LOGGER.info("Fetching required date with Time");
        String requiredDateWithTime =
                ZonedDateTime.now(ZoneOffset.UTC).plusDays(numberOfDays).plusMinutes(1).plusSeconds(RANDOM_UPPER_LIMIT).format(DateTimeFormatter.ofPattern(MM_DD_YYYY_WITH_SLASH));
        return requiredDateWithTime;

    }

    public static String getFormatedDate(String date) {
        APPLICATION_LOGGER.info("Fetching required date with Time");
        LocalDate ld = LocalDate.parse(date);
        String newDate = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ld);
        return newDate;
    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));

        }

        return sb.toString();
    }

    public static LogToReporterAppender registerReporterAppender(String pattern) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("logger name");
        rootLogger.setLevel(Level.INFO);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(lc);
        encoder.setPattern(pattern);
        encoder.setOutputPatternAsHeader(true);
        encoder.start();

        LogToReporterAppender appender = new LogToReporterAppender<>();
        appender.setContext(lc);
        appender.setEncoder(encoder);
        appender.start();

        rootLogger.addAppender(appender);
        return appender;
    }

    public static void set_section_name(String section) {
        sectionName = section;
    }

    public static String
    get_section_name() {
        try {
            if (sectionName != null)
                return sectionName;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ("Section name is null");
    }

    
}

