package com.cstout.chat.extractors;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.validator.routines.UrlValidator;

public class UrlExtractor implements IExtractor {

  // matches any string which starts with http(s)://. We'll later validate to make sure we have a
  // valid URL
  private final String pattern = "(https?:\\/\\/)\\S+";

  /*
   * Validates that a given url which has been extracted is a valid URL
   * 
   * @see Extractors.IExtractor#extract(java.util.List)
   */
  @Override
  public List<String> extract(List<String> matches) {
    ArrayList<String> urls = new ArrayList<>();
    String[] schemes = {"http", "https"};
    UrlValidator urlValidator = new UrlValidator(schemes);
    for (String match : matches) {
      if (urlValidator.isValid(match)) {
        urls.add(match);
      }
    }

    return urls;
  }

  @Override
  public String getPattern() {
    return pattern;
  }

}
