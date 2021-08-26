package eapli.base.settings.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegex {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2);
    private static final Pattern VALID_TITULO_CATALOGO_REGEX = Pattern.compile("^[A-Z]([a-z]|[A-Z]| |){1,25}$", 2);
    private static final Pattern VALID_DESCRICAO_BREVE_CATALOGO_REGEX = Pattern.compile("^[A-Z]([a-z]|[A-Z]| ){1,30}$", 2);
    private static final Pattern VALID_DESCRICAO_COMPLETA_CATALOGO_REGEX = Pattern.compile("[A-Z]([a-z]|[A-Z]| ){1,100}$", 2);
    private static final String TRAILING_WHITESPACE_REGEX = ".*[ \t]+$";
    private static final String HEADING_WHITESPACE_REGEX = "^[ \t]+.*";

    private StringRegex() {
    }

    public static boolean isTituloCatalogo(final String text) {
        Matcher matcher = VALID_TITULO_CATALOGO_REGEX.matcher(text);
        return matcher.find();
    }

    public static boolean isDescricaoBreveCatalogo(final String text) {
        Matcher matcher = VALID_DESCRICAO_BREVE_CATALOGO_REGEX.matcher(text);
        return matcher.find();
    }

    public static boolean isDescricaoCompletaCatalogo(final String text) {
        Matcher matcher = VALID_DESCRICAO_COMPLETA_CATALOGO_REGEX.matcher(text);
        return matcher.find();
    }

    public static boolean isEmail(final String text) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(text);
        return matcher.find();
    }

    public static boolean isPhrase(final String text) {
        return !isNullOrEmpty(text) && !text.matches("^[ \t]+.*") && !text.matches(".*[ \t]+$");
    }

    public static boolean isSingleWord(final String text) {
        return !isNullOrEmpty(text) && text.indexOf(32) == -1;
    }

    public static boolean isNullOrEmpty(final String text) {
        return text == null || text.isEmpty();
    }

    public static boolean isNullOrWhiteSpace(final String text) {
        return isNullOrEmpty(text) || text.trim().isEmpty();
    }

    public static boolean containsDigit(final String text) {
        return text.matches(".*\\d.*");
    }

    public static boolean containsAlpha(final String text) {
        return text.matches(".*[a-zA-Z].*");
    }

    public static boolean containsCapital(final String text) {
        return text.matches(".*[A-Z].*");
    }

    public static boolean containsAny(final String subject, final String chars) {
        return !isNullOrEmpty(chars) && !isNullOrEmpty(subject) ? subject.chars().anyMatch((c) -> {
            return chars.indexOf(c) != -1;
        }) : false;
    }
}
