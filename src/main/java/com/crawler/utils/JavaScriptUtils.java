package com.crawler.utils;

import static com.crawler.utils.StringUtils.*;
import static java.util.Arrays.stream;

public class JavaScriptUtils {
    private static final String HTTP = "http";
    private static final String EMPTY = "";
    private static final String SLASH = "/";
    private static final String DOT_JS = ".js";
    private static final String HYPHEN = "-";

    private JavaScriptUtils() {
        // empty constructor
    }

    public static boolean isPrivateLibrary(String url) {
        return !isPublicLibrary(url);
    }

    public static boolean isPublicLibrary(String url) {
        return isNotEmpty(url) && beginsWith(url, HTTP);
    }

    public static String getLibraryNameWithoutVersion(String httpUrl) {
        if (isEmpty(httpUrl)) {
            return EMPTY;
        } else {
            final String versionedLibraryName = stream(httpUrl.split(SLASH)).reduce((v1, v2) -> v2).get();
            return extractLibraryName(versionedLibraryName);
        }
    }

    private static String extractLibraryName(String versionedLibraryNameOptional) {
        final String libName = stream(versionedLibraryNameOptional.split("-")).findFirst().get();
        return libName.replace(DOT_JS, EMPTY);
    }
}
