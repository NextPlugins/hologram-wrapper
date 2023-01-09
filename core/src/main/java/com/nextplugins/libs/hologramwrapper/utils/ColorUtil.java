package com.nextplugins.libs.hologramwrapper.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class ColorUtil {

    private static final char ALT_COLOR_CHAR = '&';

    private ColorUtil() {}

    public static String of(String string) {
        return ChatColor.translateAlternateColorCodes(ALT_COLOR_CHAR, string);
    }

    public static List<String> of(List<String> strings) {
        return strings.stream().map(ColorUtil::of).collect(Collectors.toList());
    }

    public static String[] of(String... strings) {
        return of(List.of(strings)).toArray(new String[] {});
    }

}
