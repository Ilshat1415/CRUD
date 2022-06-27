package ru.liga.crud.support;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class MaskingPatternLayout extends PatternLayout {
    private final static String SALARY_PATTERN = "['\"]?salary['\"]?\\s*[:=]\\s*['\"]?(.*?)['\",; ]";
    private final static String TELEPHONE_NUMBER_PATTERN = "['\"]?telephoneNumber['\"]?\\s*[:=]\\s*['\"]?(.*?)['\",; ]";
    private final static String EMAIL_PATTERN = "(\\w+@\\w+\\.\\w+)";
    private final static String PASSWORD_PATTERN = "[\"']?password[\"']?\\s*[:=]\\s*[\"']?(.*?)[\"',; ]";
    private final static String JWT_TOKEN_PATTERN = "[\"']?jwtToken[\"']?\\s*[:=]\\s*[\"']?(.*?)[\"',; ]";
    private final Pattern multilinePattern;

    public MaskingPatternLayout() {
        List<String> maskPatterns = new ArrayList<>();
        Collections.addAll(
                maskPatterns,
                SALARY_PATTERN,
                TELEPHONE_NUMBER_PATTERN,
                EMAIL_PATTERN,
                PASSWORD_PATTERN,
                JWT_TOKEN_PATTERN);

        multilinePattern = Pattern.compile(
                String.join("|", maskPatterns),
                Pattern.MULTILINE
        );
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }

        StringBuilder builder = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(builder);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                maskSymbol(builder, matcher, group);
            });
        }
        return builder.toString();
    }

    private void maskSymbol(StringBuilder builder, Matcher matcher, int group) {
        if (matcher.group(group) != null) {
            IntStream.range(
                    matcher.start(group),
                    matcher.end(group)).forEach(i -> builder.setCharAt(i, '*')
            );
        }
    }

}