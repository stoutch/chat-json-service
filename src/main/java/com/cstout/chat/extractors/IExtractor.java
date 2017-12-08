package com.cstout.chat.extractors;

import java.util.List;

public interface IExtractor {
  /**
   * Cleanses matches found for a given regex pattern.
   * 
   * @param text
   * @return
   */
  public List<String> extract(List<String> text);

  /**
   * Returns a regex pattern used to match message text to whatever is being searched for.
   */
  public String getPattern();
}
