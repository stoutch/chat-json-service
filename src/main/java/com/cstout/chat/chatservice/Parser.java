package com.cstout.chat.chatservice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.cstout.chat.extractors.IExtractor;

public class Parser {
  private IExtractor extractor;

  /**
   * Initializes a Parser object.
   *
   * After initialization, {@code setExtractor} must be called prior to calling {@code parse}.
   */
  public Parser() {
    // empty constructor for basic initialization
  }

  /**
   * Determines which implementation of IExtractor to use for parsing
   *
   * @param extractor An object which implements IExtractor and provides details on how parsing
   *        should be carried out
   */
  public void setExtractor(IExtractor extractor) {
    this.extractor = extractor;
  }

  /**
   * Uses the regex pattern and extract function from the current IExtractor to find matching
   * substrings in the given string.
   *
   * @param text The string in which to find any matches.
   * @return A list of strings which are matches according to the IExtractor.
   */
  public List<String> parse(String text) {
    if (extractor == null) {
      throw new IllegalStateException("Extractor must be defined in order to parse text.");
    }

    ArrayList<String> rawMatches = new ArrayList<>();
    String pattern = extractor.getPattern();

    if (text == null || text.length() < 1) {
      return rawMatches;
    }

    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(text);
    while (m.find()) {
      rawMatches.add(m.group());
    }

    List<String> matches = extractor.extract(rawMatches);
    return matches;
  }

}
