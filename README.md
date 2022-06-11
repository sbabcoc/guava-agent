[![Maven Central](https://img.shields.io/maven-central/v/com.nordstrom.tools/guava-agent.svg)](https://search.maven.org/search?q=g:com.nordstrom.tools%20AND%20a:guava-agent&core=gav)

# guava-agent
This project builds a Java agent that exposes interfaces in the Guava API that are required by the Selenium 2 **`UrlChecker`** class. The modifications applied by the agent enable the implementation of the **Selenium Foundation** local Grid feature to use **`UrlChecker`** with newer releases of Guava that address security defects.

**NOTE**: The rationale for this library was to enable continuing maintenance of the Selenium 2 version of **Selenium Foundation**, which is compatible with Java 7. The Guava project dropped support for Java 7 at release 31.0, so the current release of **guava-agent** will not receive any further updates.
