package com.cstout.chat.chatservice;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.cstout.chat.extractors.MentionExtractor;

public class ParseMentionTest {

  @Test
  public final void testBasic() {
    String input = "@Chris";

    List<String> expected = new ArrayList<>();
    expected.add("Chris");

    Parser parser = new Parser();
    parser.setExtractor(new MentionExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testExtractMulti() {
    String input = "@Chris @Alex John";

    List<String> expected = new ArrayList<>();
    expected.add("Chris");
    expected.add("Alex");

    Parser parser = new Parser();
    parser.setExtractor(new MentionExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testDeduplicateMentions() {
    String input = "@Chris @Alex @Chris ";

    List<String> expected = new ArrayList<>();
    expected.add("Chris");
    expected.add("Alex");

    Parser parser = new Parser();
    parser.setExtractor(new MentionExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testDuplicateMentionsMixedCase() {
    String input = "@Chris @cHRis @Alex ";

    List<String> expected = new ArrayList<>();
    expected.add("Chris");
    expected.add("Alex");

    Parser parser = new Parser();
    parser.setExtractor(new MentionExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

  @Test
  public final void testAdjacent() {
    String input = "@Chris@Alex";

    List<String> expected = new ArrayList<>();
    expected.add("Chris");

    Parser parser = new Parser();
    parser.setExtractor(new MentionExtractor());
    List<String> actual = parser.parse(input);
    assertEquals(expected, actual);
  }

}
