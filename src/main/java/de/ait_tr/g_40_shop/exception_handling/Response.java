package de.ait_tr.g_40_shop.exception_handling;

import de.ait_tr.g_40_shop.exception_handling.exceptions.FirstTestException;

import java.util.Objects;

public class Response {

  private String message;

  public Response(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Response response)) return false;

    return Objects.equals(message, response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(message);
  }

  @Override
  public String toString() {
    return "Response: " + message;
  }
}
