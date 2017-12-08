package com.cstout.chat.chatservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ParseEmojiTest.class, ParseMentionTest.class, ParseUrlTest.class,
    OverallTest.class})
public class ChatServiceTest {

}
