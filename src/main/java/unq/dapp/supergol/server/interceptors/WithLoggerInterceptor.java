package unq.dapp.supergol.server.interceptors;


import spark.Filter;

public interface WithLoggerInterceptor  {
  default Filter loggingInterceptor(){
    return (request,response)->{

    };
  }
}
