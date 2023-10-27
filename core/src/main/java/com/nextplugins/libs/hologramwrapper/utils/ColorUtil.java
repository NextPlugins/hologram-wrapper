package com.nextplugins.libs.hologramwrapper.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ColorUtil {

    private static final char ALT_COLOR_CHAR = '&';
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    private ColorUtil() {}

    public static String of(String string) {
        return ChatColor.translateAlternateColorCodes(ALT_COLOR_CHAR, hex(string));
    }

    public static List<String> of(List<String> strings) {
        return strings.stream().map(ColorUtil::of).collect(Collectors.toList());
    }

    // Originally based on https://www.spigotmc.org/threads/hex-color-code-translate.449748/#post-3867795
    public static String hex(String string) {
        final Matcher matcher = HEX_COLOR_PATTERN.matcher(string);
        final StringBuilder builder = new StringBuilder(string.length() + 4 * 8);

        while (matcher.find()) {
            final String group = matcher.group(1);
            final char[] chars = group.toCharArray();

            matcher.appendReplacement(builder, ALT_COLOR_CHAR + "x");

            for (int i = 0; i < 5; i++) {
                final char colorCode = chars[i];

                matcher.appendReplacement(builder, ALT_COLOR_CHAR + String.valueOf(colorCode));
            }
        }

        return matcher.appendTail(builder).toString();
    }

}
