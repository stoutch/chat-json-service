package com.cstout.chat.chatservice;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.cstout.chat.extractors.EmojiExtractor;

public class ParseEmojiTest {

  @Test
  public final void testBasic() {
    String input = ":excited:";

    List<String> expected = new ArrayList<>();
    expected.add("excited");

    Parser parser = new Parser();
    parser.setExtractor(new EmojiExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testMultiple() {
    String input = ":excited: and :happy:";

    List<String> expected = new ArrayList<>();
    expected.add("excited");
    expected.add("happy");

    Parser parser = new Parser();
    parser.setExtractor(new EmojiExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testEmpty() {
    String input = "::";

    List<String> expected = new ArrayList<>();

    Parser parser = new Parser();
    parser.setExtractor(new EmojiExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testSandwich() {
    String input = "a:surprised:b";

    List<String> expected = new ArrayList<>();
    expected.add("surprised");

    Parser parser = new Parser();
    parser.setExtractor(new EmojiExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testLong() {
    String input = ":abcdefghijklmnop:";

    List<String> expected = new ArrayList<>();

    Parser parser = new Parser();
    parser.setExtractor(new EmojiExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testAdjacent() {
    String input = ":first::second:";

    List<String> expected = new ArrayList<>();
    expected.add("first");
    expected.add("second");

    Parser parser = new Parser();
    parser.setExtractor(new EmojiExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testShareColon() {
    String input = ":first:second:";

    List<String> expected = new ArrayList<>();
    expected.add("first");

    Parser parser = new Parser();
    parser.setExtractor(new EmojiExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

}
