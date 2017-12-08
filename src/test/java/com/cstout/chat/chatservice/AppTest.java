package com.cstout.chat.chatservice;

import java.util.Scanner;

/**
 * Text entry point for message parsing
 */
public class AppTest {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter a message:");
    String message = scan.nextLine();
    ChatService service = new ChatService();
    service.submitText(message);
    System.out.println("Extracted text:");
    System.out.println(service.getJSON());
    scan.close();
  }
}
