package com.crawler.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.crawler.utils.JavaScriptUtils.*;

public class JavaScriptUtilsTest {

    @Test
    public void testIsPrivateLibrary() {
        Assert.assertTrue(isPrivateLibrary("/ga/js/popUp.js"));
        Assert.assertTrue(isPrivateLibrary("//consent.trustarc.com/notice?domain=oracle.com&c=teconsent&js=bb&noticeType=bb&text=true&gtm=1&language=de"));
        Assert.assertTrue(isPrivateLibrary("/feo-cdn/_/m/Ren-UTF-8~_me_KJ0Qtk7v9K-Dw-sEHXugnZx_t2KQn6IG4Vt2-v4.js"));
    }

    @Test
    public void testIsPublicLibrary() {
        Assert.assertTrue(isPublicLibrary("https://static.oracle.com/cdn/apm/1.46.0/apmeum.js"));
        Assert.assertTrue(isPublicLibrary("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"));
    }


    @Test
    public void testGetLibraryNameWithoutVersion() {
        Assert.assertEquals("apmeum", getLibraryNameWithoutVersion("https://static.oracle.com/cdn/apm/1.46.0/apmeum.js"));
        Assert.assertEquals("apmeum", getLibraryNameWithoutVersion("https://static.oracle.com/cdn/apm/1.46.0/apmeum-2.3.4.js"));
        Assert.assertEquals("apmeum", getLibraryNameWithoutVersion("apmeum-2.3.4.js"));
        Assert.assertEquals("apmeum", getLibraryNameWithoutVersion("apmeum"));
    }
}
