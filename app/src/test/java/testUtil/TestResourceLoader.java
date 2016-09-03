package testutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Used for loading body json files under /fixtures.
 */
public final class TestResourceLoader {

  private TestResourceLoader() {
    // no instance
  }

  public static String load(String fileName) {
    if (fileName == null) {
      throw new NullPointerException("File name should not be null");
    }
    String result;
    try {
      result = readPathIntoString(fileName);
    } catch (IOException e) {
      throw new IllegalStateException("Error reading body: " + fileName, e);
    }
    return result;
  }

  private static InputStream openPathAsStream(String path) {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = loader.getResourceAsStream(path);

    if (inputStream == null) {
      throw new IllegalStateException("Invalid path: " + path);
    }

    return inputStream;
  }

  private static String readPathIntoString(String path) throws IOException {
    InputStream inputStream = openPathAsStream(path);
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder out = new StringBuilder();
    int read;
    while ((read = reader.read()) != -1) {
      out.append((char) read);
    }
    reader.close();

    return out.toString();
  }
}
