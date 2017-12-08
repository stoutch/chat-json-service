package com.cstout.chat.extractors;

import java.util.ArrayList;
import java.util.List;


public class EmojiExtractor implements IExtractor {

  private final String pattern = ":\\w{1,15}:";

  /*
   * Removes leading and trailing ':'
   * 
   * @see Extractors.IExtractor#extract(java.util.List)
   */
  @Override
  public List<String> extract(List<String> matches) {
    ArrayList<String> emojis = new ArrayList<>();
    if (matches == null || matches.size() < 1) {
      return emojis;
    }

    int startIndex, endIndex;
    for (String match : matches) {
      // make sure the string is not null and length
      // greater than two (because of start and end ':')
      if (match != null && match.length() > 2) {
        // Using indexOf here just to be safe. We should always be guaranteed to have an input that
        // starts and ends with ':'
        startIndex = match.indexOf(':') + 1;
        endIndex = match.indexOf(':', startIndex);
        emojis.add(match.substring(startIndex, endIndex));
      }
    }
    return emojis;
  }

  @Override
  public String getPattern() {
    return pattern;
  }

}
