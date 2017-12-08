package com.cstout.chat.chatservice;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class OverallTest {

  private ChatService service = new ChatService();

  @Test
  public final void testA() {
    String input = "Good morning! :megusta: :coffee: @chris @CHRIS https://facebook.com";
    String expected =
        "{\"emoticons\":[\"megusta\",\"coffee\"],\"mentions\":[\"chris\"],\"links\":[{\"url\":\"https://facebook.com\",\"title\":\"Facebook - Log In or Sign Up\"}]}";

    service.submitText(input);
    String actual = service.getJSON();
    assertEquals(expected, actual);
  }

}
