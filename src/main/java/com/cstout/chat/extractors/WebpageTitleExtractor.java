package com.cstout.chat.extractors;

import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebpageTitleExtractor extends UrlExtractor {

  /**
   * Initializes a WebpageTitleExtractor based off a {@code UrlExtractor}.
   */
  public WebpageTitleExtractor() {
    super();
  }

  /**
   * Creates a mapping from URL to the title of the corresponding website.
   *
   * @param urls The list of URLs to do a title lookup for.
   * @return A map from URL to title.
   */
  public HashMap<String, String> getUrlTitleMap(List<String> urls) {
    HashMap<String, String> urlTitleMap = new HashMap<>();
    if (urls == null || urls.isEmpty()) {
      return urlTitleMap;
    }

    Document document;
    String title;
    for (String url : urls) {
      if (!urlTitleMap.containsKey(url)) {
        try {
          document = Jsoup.connect(url).get();
          title = document.title();
        } catch (Exception e) {
          title = "";
        }

        if (title == null || title.isEmpty()) {
          title = url; // if no title for the webpage, default to using the URL itself
        }

        /*
         * This will remove duplicate URLs, but it shouldn't really matter too much. When a user
         * sends a link in a message, there's really no use to having it in there twice, compared to
         * something like emojis where multiple occurrences can be used to express more
         * emotion/relate to different things.
         */
        urlTitleMap.put(url, title);
      }
    }

    return urlTitleMap;
  }


}
