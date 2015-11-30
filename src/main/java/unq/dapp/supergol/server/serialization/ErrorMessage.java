package unq.dapp.supergol.server.serialization;

public class ErrorMessage {
  private final String error;
  private final String message;

  public ErrorMessage(Exception exception) {
    this.error = exception.getClass().getSimpleName().replace("Exception", "");
    this.message = exception.getMessage();
  }
}
