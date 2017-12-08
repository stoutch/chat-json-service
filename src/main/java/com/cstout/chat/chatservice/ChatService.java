package com.cstout.chat.chatservice;

import java.util.HashMap;
import java.util.List;
import com.cstout.chat.extractors.EmojiExtractor;
import com.cstout.chat.extractors.MentionExtractor;
import com.cstout.chat.extractors.WebpageTitleExtractor;
import com.cstout.chat.serialization.MessageData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Service entry point for extracting information from a chat message and creating a JSON object
 * from that data. Currently supports extracting emoji, mentions, and the titles of URLs.
 *
 */
public class ChatService {
  private String messageText;
  private Parser parser;
  private MessageDataBuilder dataBuilder;

  public ChatService() {
    // Use an empty Parser constructor here in order to create a single Parser object where we can
    // just change the parsing method
    parser = new Parser();
    dataBuilder = new MessageDataBuilder();
  }

  /**
   * Enters text to be processed.
   *
   * @param text
   */
  public void submitText(String text) {
    messageText = text;
    parseMessage();
  }

  private void parseMessage() {
    parseMentions();
    parseEmojis();
    parseLinks();
  }

  private void parseMentions() {
    parser.setExtractor(new MentionExtractor());
    List<String> mentions = parser.parse(messageText);
    dataBuilder.setMentions(mentions);
  }

  private void parseEmojis() {
    parser.setExtractor(new EmojiExtractor());
    List<String> emojis = parser.parse(messageText);
    dataBuilder.setEmojis(emojis);
  }

  private void parseLinks() {
    WebpageTitleExtractor extractor = new WebpageTitleExtractor();
    parser.setExtractor(extractor);
    List<String> urls = parser.parse(messageText);
    HashMap<String, String> urlTitleMap = extractor.getUrlTitleMap(urls);
    dataBuilder.setLinks(urlTitleMap);

  }

  /**
   * Returns the JSON object representation of the extractable data from the message.
   */
  public String getJSON() {
    /*
     * separate getting JSON from submitting a string in case the caller wants to get the JSON
     * object multiple times
     */
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    MessageData data = dataBuilder.build();
    return gson.toJson(data);
  }
}
