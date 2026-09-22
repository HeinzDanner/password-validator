import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @Test
    void hasMinLengthAcceptsLongEnoughPassword() {
        assertTrue(PasswordValidator.hasMinLength("abcdef", 6));
    }

    @Test
    void hasMinLengthAcceptsPasswordLongerThanMinimum() {
        assertTrue(PasswordValidator.hasMinLength("abcdefg", 6));
    }

    @Test
    void hasMinLengthRejectsTooShortPassword() {
        assertFalse(PasswordValidator.hasMinLength("abc", 6));
    }

    @Test
    void hasMinLengthRejectsNullPassword() {
        assertFalse(PasswordValidator.hasMinLength(null, 6));
    }

    @Test
    void containsDigitFindsDigit() {
        assertTrue(PasswordValidator.containsDigit("abc1def"));
    }

    @Test
    void containsDigitFindsMultipleDigits() {
        assertTrue(PasswordValidator.containsDigit("abc123def"));
    }

    @Test
    void containsDigitRejectsPasswordWithoutDigit() {
        assertFalse(PasswordValidator.containsDigit("abcdef"));
    }

    @Test
    void containsDigitRejectsNullPassword() {
        assertFalse(PasswordValidator.containsDigit(null));
    }

    @Test
    void containsUpperAndLowerRequiresBothCases() {
        assertTrue(PasswordValidator.containsUpperAndLower("Abcdef"));
        assertFalse(PasswordValidator.containsUpperAndLower("abcdef"));
        assertFalse(PasswordValidator.containsUpperAndLower("ABCDEF"));
    }

    @Test
    void containsUpperAndLowerRejectsNullPassword() {
        assertFalse(PasswordValidator.containsUpperAndLower(null));
    }

    @Test
    void isCommonPasswordRecognizesExactCommonPassword() {
        assertTrue(PasswordValidator.isCommonPassword("password"));
        assertTrue(PasswordValidator.isCommonPassword("qwerty"));
        assertTrue(PasswordValidator.isCommonPassword("123456"));
    }

    @Test
    void isCommonPasswordIsCaseInsensitive() {
        assertTrue(PasswordValidator.isCommonPassword("Password"));
        assertTrue(PasswordValidator.isCommonPassword("PASSWORD"));
        assertTrue(PasswordValidator.isCommonPassword("QwErTy"));
    }

    @Test
    void isCommonPasswordRecognizesCommonPasswordWithDigitsAndSpecialChars() {
        assertTrue(PasswordValidator.isCommonPassword("Password1!"));
        assertTrue(PasswordValidator.isCommonPassword("Admin123!"));
        assertTrue(PasswordValidator.isCommonPassword("Welcome2026!"));
    }

    @Test
    void isCommonPasswordRecognizesGermanCommonPassword() {
        assertTrue(PasswordValidator.isCommonPassword("Passwort1!"));
    }

    @Test
    void isCommonPasswordRejectsUncommonPassword() {
        assertFalse(PasswordValidator.isCommonPassword("Xy9!mN42"));
    }

    @Test
    void isCommonPasswordRejectsNullPassword() {
        assertFalse(PasswordValidator.isCommonPassword(null));
    }

    @Test
    void containsSpecialCharChecksAllowedCharacters() {
        assertTrue(PasswordValidator.containsSpecialChar("abc!", "!@#"));
        assertFalse(PasswordValidator.containsSpecialChar("abc$", "!@#"));
    }

    @Test
    void containsSpecialCharRejectsWhenAllowedCharsAreEmpty() {
        assertFalse(PasswordValidator.containsSpecialChar("abc!", ""));
    }

    @Test
    void containsSpecialCharRejectsNullPassword() {
        assertFalse(PasswordValidator.containsSpecialChar(null, "!@#"));
    }

    @Test
    void containsSpecialCharRejectsNullAllowedCharacters() {
        assertFalse(PasswordValidator.containsSpecialChar("abc!", null));
    }

    @Test
    void isValidAcceptsValidPassword() {
        assertTrue(PasswordValidator.isValid("Strong1!"));
    }

    @Test
    void isValidRejectsNullPassword() {
        assertFalse(PasswordValidator.isValid(null));
    }

    @Test
    void isValidRejectsBlankPassword() {
        assertFalse(PasswordValidator.isValid("   "));
    }

    @Test
    void isValidRejectsTooShortPassword() {
        assertFalse(PasswordValidator.isValid("A1!"));
    }

    @Test
    void isValidRejectsPasswordWithoutDigit() {
        assertFalse(PasswordValidator.isValid("Strong!!"));
    }

    @Test
    void isValidRejectsPasswordWithoutUppercaseLetter() {
        assertFalse(PasswordValidator.isValid("strong1!"));
    }

    @Test
    void isValidRejectsPasswordWithoutLowercaseLetter() {
        assertFalse(PasswordValidator.isValid("STRONG1!"));
    }

    @Test
    void isValidRejectsPasswordWithoutSpecialChar() {
        assertFalse(PasswordValidator.isValid("Strong12"));
    }

    @Test
    void isValidRejectsCommonPassword() {
        assertFalse(PasswordValidator.isValid("Password1!"));
    }

    @Test
    void isValidUsesCustomMinimumLength() {
        assertTrue(PasswordValidator.isValid("Abc1!", 5, "!"));
        assertFalse(PasswordValidator.isValid("Abc1!", 6, "!"));
    }

    @Test
    void isValidUsesCustomAllowedSpecialCharacters() {
        assertTrue(PasswordValidator.isValid("Strong1#", 8, "#"));
        assertFalse(PasswordValidator.isValid("Strong1!", 8, "#"));
    }
}