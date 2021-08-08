/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.util;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import java.text.Normalizer;

public class SaslPrep {
    public static String prepare(String str, StringType sType) {
        if (str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (char chr : str.toCharArray()) {
            if (SaslPrep.isNonAsciiSpaceChar(chr)) {
                sb.append(' ');
                continue;
            }
            if (SaslPrep.isMappeableToNothing(chr)) continue;
            sb.append(chr);
        }
        String preparedStr = SaslPrep.normalizeKc(sb);
        boolean startsWithRAndAlCat = SaslPrep.isBidiRAndAlCat(preparedStr.codePointAt(0));
        boolean endsWithRAndAlCat = SaslPrep.isBidiRAndAlCat(preparedStr.codePointAt(preparedStr.length() - (Character.isLowSurrogate(preparedStr.charAt(preparedStr.length() - 1)) ? 2 : 1)));
        boolean containsRAndAlCat = startsWithRAndAlCat || endsWithRAndAlCat;
        boolean containsLCat = false;
        int i = 0;
        while (i < preparedStr.length()) {
            char chr = preparedStr.charAt(i);
            int cp = preparedStr.codePointAt(i);
            int ni = i + Character.charCount(cp);
            if (SaslPrep.isProhibited(chr, cp)) {
                throw ExceptionFactory.createException(WrongArgumentException.class, "Prohibited character at position " + i + ".");
            }
            if (!containsRAndAlCat) {
                containsRAndAlCat = SaslPrep.isBidiRAndAlCat(cp);
            }
            if (!containsLCat) {
                containsLCat = SaslPrep.isBidiLCat(cp);
            }
            if (containsRAndAlCat && containsLCat) {
                throw ExceptionFactory.createException(WrongArgumentException.class, "Cannot contain both RandALCat characters and LCat characters.");
            }
            if (!(ni < preparedStr.length() || !containsRAndAlCat || startsWithRAndAlCat && endsWithRAndAlCat)) {
                throw ExceptionFactory.createException(WrongArgumentException.class, "Cannot contain RandALCat characters and not start and end with RandALCat characters.");
            }
            if (sType == StringType.STORED && SaslPrep.isUnassigned(cp)) {
                throw ExceptionFactory.createException(WrongArgumentException.class, "Unassigned character at position " + i + ".");
            }
            i = ni;
        }
        return preparedStr;
    }

    private static boolean isNonAsciiSpaceChar(char chr) {
        return chr == '\u00a0' || chr == '\u1680' || chr >= '\u2000' && chr <= '\u200b' || chr == '\u202f' || chr == '\u205f' || chr == '\u3000';
    }

    private static boolean isMappeableToNothing(char chr) {
        return chr == '\u00ad' || chr == '\u034f' || chr == '\u1806' || chr >= '\u180b' && chr <= '\u180d' || chr >= '\u200b' && chr <= '\u200d' || chr == '\u2060' || chr >= '\ufe00' && chr <= '\ufe0f' || chr == '\ufeff';
    }

    private static String normalizeKc(CharSequence str) {
        return Normalizer.normalize(str, Normalizer.Form.NFKC);
    }

    private static boolean isProhibited(char chr, int cp) {
        return SaslPrep.isAsciiControlCharacter(chr) || SaslPrep.isNonAsciiControlCharacter(cp) || SaslPrep.isPrivateUseCharacter(cp) || SaslPrep.isNonCharacterCodePoint(cp) || SaslPrep.isSurrogateCode(chr) || SaslPrep.isInappropriateForPlainTextCharacter(chr) || SaslPrep.isInappropriateForCanonicalRepresentationCharacter(chr) || SaslPrep.isChangeDisplayPropertiesOrDeprecatedCharacter(chr) || SaslPrep.isTaggingCharacter(cp);
    }

    private static boolean isAsciiControlCharacter(char chr) {
        return chr <= '\u001f' || chr == '\u007f';
    }

    private static boolean isNonAsciiControlCharacter(int cp) {
        return cp >= 128 && cp <= 159 || cp == 1757 || cp == 1807 || cp == 6158 || cp == 8204 || cp == 8205 || cp == 8232 || cp == 8233 || cp >= 8288 && cp <= 8291 || cp >= 8298 && cp <= 8303 || cp == 65279 || cp >= 65529 && cp <= 65532 || cp >= 119155 && cp <= 119162;
    }

    private static boolean isPrivateUseCharacter(int cp) {
        return cp >= 57344 && cp <= 63743 || cp >= 983040 && cp <= 1048573 || cp >= 0x100000 && cp <= 1114109;
    }

    private static boolean isNonCharacterCodePoint(int cp) {
        return cp >= 64976 && cp <= 65007 || cp >= 65534 && cp <= 65535 || cp >= 131070 && cp <= 131071 || cp >= 196606 && cp <= 196607 || cp >= 262142 && cp <= 262143 || cp >= 327678 && cp <= 327679 || cp >= 393214 && cp <= 393215 || cp >= 458750 && cp <= 458751 || cp >= 524286 && cp <= 524287 || cp >= 589822 && cp <= 589823 || cp >= 655358 && cp <= 655359 || cp >= 720894 && cp <= 720895 || cp >= 786430 && cp <= 786431 || cp >= 851966 && cp <= 851967 || cp >= 917502 && cp <= 917503 || cp >= 983038 && cp <= 983039 || cp >= 1048574 && cp <= 1048575 || cp >= 1114110 && cp <= 0x10FFFF;
    }

    private static boolean isSurrogateCode(char chr) {
        return chr >= '\ud800' && chr <= '\udfff';
    }

    private static boolean isInappropriateForPlainTextCharacter(char chr) {
        return chr == '\ufff9' || chr >= '\ufffa' && chr <= '\ufffd';
    }

    private static boolean isInappropriateForCanonicalRepresentationCharacter(char chr) {
        return chr >= '\u2ff0' && chr <= '\u2ffb';
    }

    private static boolean isChangeDisplayPropertiesOrDeprecatedCharacter(char chr) {
        return chr == '\u0340' || chr == '\u0341' || chr == '\u200e' || chr == '\u200f' || chr >= '\u202a' && chr <= '\u202e' || chr >= '\u206a' && chr <= '\u206f';
    }

    private static boolean isTaggingCharacter(int cp) {
        return cp == 917505 || cp >= 917536 && cp <= 917631;
    }

    private static boolean isBidiRAndAlCat(int cp) {
        byte dir = Character.getDirectionality(cp);
        return dir == 1 || dir == 2;
    }

    private static boolean isBidiLCat(int cp) {
        byte dir = Character.getDirectionality(cp);
        return dir == 0;
    }

    private static boolean isUnassigned(int cp) {
        return !Character.isDefined(cp);
    }

    public static enum StringType {
        STORED,
        QUERY;

    }
}

