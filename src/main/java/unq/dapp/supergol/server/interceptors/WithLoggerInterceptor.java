package unq.dapp.supergol.server.interceptors;


import spark.Filter;
import spark.Request;

import java.time.LocalDateTime;

public interface WithLoggerInterceptor  {
  default Filter loggingInterceptor(){
    return (request,response)->{
      new Logger().info(request);
    };
  }
}

class Logger {
  void info(Request request) {
    System.out.println(String.format("%s - %s: %s", timestamp(), request.requestMethod(), request.url()));
  }

  private LocalDateTime timestamp() {
    return LocalDateTime.now();
  }
}
