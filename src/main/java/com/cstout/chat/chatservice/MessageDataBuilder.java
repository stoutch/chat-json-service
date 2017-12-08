package com.cstout.chat.chatservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.cstout.chat.serialization.Link;
import com.cstout.chat.serialization.MessageData;

public class MessageDataBuilder {
  private MessageData data;

  /**
   * Initializes a builder for a MessageData object.
   */
  public MessageDataBuilder() {
    data = new MessageData();
  }

  /**
   * Adds a list of mentions to the MessageData object.
   *
   * @param mentions Mentions to add
   */
  public void setMentions(List<String> mentions) {
    data.mentions = mentions;
  }

  /**
   * Adds a list of emojis to the MessageData object.
   *
   * @param emojis Emojis to add
   */
  public void setEmojis(List<String> emojis) {
    data.emoticons = emojis;
  }

  /**
   * Given a mapping of URLs and webpage titles, creates a list of Link objects and adds them to the
   * MessageData object.
   *
   * @param urlTitleMap Map of a URL to the title corresponding to that webpage.
   */
  public void setLinks(HashMap<String, String> urlTitleMap) {
    String title;
    Link link;
    List<Link> links = new ArrayList<>();
    if (urlTitleMap != null && !urlTitleMap.isEmpty()) {
      for (String url : urlTitleMap.keySet()) {
        title = urlTitleMap.get(url);
        link = new Link(url, title);
        links.add(link);
      }
    }
    data.links = links;
  }

  /**
   * Returns a built MessageData object
   */
  public MessageData build() {
    return data;
  }
}
