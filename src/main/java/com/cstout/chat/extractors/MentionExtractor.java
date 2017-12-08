package com.cstout.chat.extractors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MentionExtractor implements IExtractor {

  private final String pattern = "(^(@\\w+)|(\\s+)(@\\w+))"; // this regex will match on
  // alphanumeric words that must begin
  // with @

  /*
   * Removes leading '@' and de-duplicates mentions
   *
   * @see Extractors.IExtractor#extract(java.util.List)
   */
  @Override
  public List<String> extract(List<String> matches) {
    ArrayList<String> mentions = new ArrayList<>();

    // Use a hash set to get the unique mentions from a message.
    // It doesn't really make sense to have multiple mentions for the same person if we're losing
    // the context they were mentioned in
    HashSet<String> uniqueMentions = new HashSet<>();
    int startIndex;
    if (matches == null || matches.size() < 1) {
      return mentions;
    }

    for (String match : matches) {
      startIndex = match.indexOf('@');
      if (startIndex != -1) {
        match = match.substring(startIndex + 1);
        if (!uniqueMentions.contains(match.toLowerCase())) {
          uniqueMentions.add(match.toLowerCase());
          mentions.add(match);
        }
      }
    }
    return mentions;
  }

  @Override
  public String getPattern() {
    return pattern;
  }

}
