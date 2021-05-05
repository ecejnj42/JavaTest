package test5;

import java.nio.charset.StandardCharsets;

public class Test5 {
  public static void main(String[] args) {
    final byte[] assertionValue = new byte[] { (byte)0xff,(byte)0xff,(byte)0xff,
        (byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff };
    long longValue = Long.parseLong(new String(assertionValue, StandardCharsets.UTF_8));
    System.out.println("Value " + longValue);
  }
}

