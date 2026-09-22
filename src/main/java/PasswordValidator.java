import java.util.Locale;
import java.util.Set;

public final class PasswordValidator {

    public static final int DEFAULT_MIN_LENGTH = 8;
    public static final String DEFAULT_ALLOWED_SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;:,.<>?/~`";

    private static final Set<String> COMMON_PASSWORD_PARTS = Set.of(
            "password",
            "passwort",
            "123456",
            "1234567",
            "12345678",
            "123456789",
            "1234567890",
            "qwerty",
            "qwertz",
            "abc123",
            "admin",
            "administrator",
            "letmein",
            "welcome",
            "login",
            "secret",
            "iloveyou",
            "monkey",
            "dragon",
            "football",
            "baseball",
            "princess",
            "sunshine",
            "charlie",
            "passw0rd"
    );

    private PasswordValidator() {
    }

    public static boolean hasMinLength(String password, int min) {
        return password != null && password.length() >= min;
    }

    public static boolean containsDigit(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsUpperAndLower(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            }
        }

        return hasUpper && hasLower;
    }

    public static boolean isCommonPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        String normalized = normalizePassword(password);

        for (String commonPasswordPart : COMMON_PASSWORD_PARTS) {
            if (normalized.contains(commonPasswordPart)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsSpecialChar(String password, String allowed) {
        if (password == null || password.isEmpty() || allowed == null || allowed.isEmpty()) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (allowed.indexOf(c) >= 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean isValid(String password) {
        return isValid(password, DEFAULT_MIN_LENGTH, DEFAULT_ALLOWED_SPECIAL_CHARS);
    }

    public static boolean isValid(String password, int minLength, String allowedSpecialChars) {
        if (password == null || password.isBlank()) {
            return false;
        }

        return hasMinLength(password, minLength)
                && containsDigit(password)
                && containsUpperAndLower(password)
                && !isCommonPassword(password)
                && containsSpecialChar(password, allowedSpecialChars);
    }

    // Vereinfachte Normalisierung nur für den Vergleich mit der internen
    // Liste häufiger Passwörter. Die Blacklist enthält nur einfache ASCII-Wörter
    // ohne Sonderzeichen/Umlaute, deshalb eigentlich sinnlos aber stoert auch nicht ;-)

    private static String normalizePassword(String password) {
        String lowerCasePassword = password.toLowerCase(Locale.ROOT);
        StringBuilder normalizedPassword = new StringBuilder();

        for (char c : lowerCasePassword.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                normalizedPassword.append(c);
            }
        }

        return normalizedPassword.toString();
    }
}