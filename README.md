# guava-agent
This project builds a Java agent that exposes interfaces in the Guava API that are required by the Selenium 2 **`UrlChecker`** class. The modifications applied by the agent enable the implementation of the **Selenium Foundation** local Grid feature to use **`UrlChecker`** with newer releases of Guava that address security defects.
