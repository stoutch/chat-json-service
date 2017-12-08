package com.cstout.chat.chatservice;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import com.cstout.chat.extractors.WebpageTitleExtractor;

public class ParseUrlTest {

  @Test
  public final void testBasicHttp() {
    String input = "http://www.atlassian.com";

    List<String> expected = new ArrayList<>();
    expected.add("http://www.atlassian.com");

    Parser parser = new Parser();
    parser.setExtractor(new WebpageTitleExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testBasicWithText() {
    String input = "abcdeasdf  http://www.atlassian.com asdkfjha";

    List<String> expected = new ArrayList<>();
    expected.add("http://www.atlassian.com");

    Parser parser = new Parser();
    parser.setExtractor(new WebpageTitleExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testBasicHttps() {
    String input = "abcdeasdf  https://www.atlassian.com asdkfjha";

    List<String> expected = new ArrayList<>();
    expected.add("https://www.atlassian.com");

    Parser parser = new Parser();
    parser.setExtractor(new WebpageTitleExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testNoHttp() {
    String input = "asd;flkajs ;lkfj www.atlassian.com asdlkjhasdfkl jhas";

    List<String> expected = new ArrayList<>();

    Parser parser = new Parser();
    parser.setExtractor(new WebpageTitleExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testInvalidURL() {
    String input = "http://abjbjbjbjasldkj";

    List<String> expected = new ArrayList<>();

    Parser parser = new Parser();
    parser.setExtractor(new WebpageTitleExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  // These tests are a little less reliable in the long run as automated tests because the title is
  // liable to change
  @Test
  public final void testBasicTitle() {
    String input = "http://www.google.com";

    HashMap<String, String> expected = new HashMap<>();
    expected.put("http://www.google.com", "Google");

    Parser parser = new Parser();
    WebpageTitleExtractor extractor = new WebpageTitleExtractor();
    parser.setExtractor(extractor);
    List<String> urls = parser.parse(input);
    HashMap<String, String> actual = extractor.getUrlTitleMap(urls);
    assertEquals(expected, actual);
  }

  @Test
  public final void testNoTitle() {
    String input = "http://jsonprettyprint.com/json-pretty-printer.php";

    HashMap<String, String> expected = new HashMap<>();
    expected.put("http://jsonprettyprint.com/json-pretty-printer.php",
        "http://jsonprettyprint.com/json-pretty-printer.php");

    Parser parser = new Parser();
    WebpageTitleExtractor extractor = new WebpageTitleExtractor();
    parser.setExtractor(extractor);
    List<String> urls = parser.parse(input);
    HashMap<String, String> actual = extractor.getUrlTitleMap(urls);
    assertEquals(expected, actual);
  }

}
