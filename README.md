# chat-json-service
Java service to convert messaging text to a JSON object

This project is intended to be used as a library in other applications, however there is still a text entry point in AppTest.java for direct usage.

Given some message text, the service will extract emojis (":excited:"), mentions ("@Chris"), and links. For links, the service will extract the title of the webpage.

After the data has been extracted, it is composed into a JSON object and returned as a string.
