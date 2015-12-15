package unq.dapp.supergol.server.interceptors;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import spark.Filter;
import spark.Request;

import java.util.Optional;
import java.util.regex.Pattern;

import static spark.Spark.halt;

public interface WithAuthInterceptor {

  final Pattern      pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
  final JWTVerifier verifier = new JWTVerifier(
    new Base64(true).decode("YOUR_CLIENT_SECRET"),
    "YOUR_CLIENT_ID"
  );

  default Filter authenticationInterceptor(){
    return (request,response)-> {
      if (!request.requestMethod().equals("OPTIONS")) {
        AuthInterceptorCompanion.getToken(request).ifPresent(AuthInterceptorCompanion::verify);
      }
    };
  }

  class AuthInterceptorCompanion {

    public static void verify(String token){
      try{
        verifier.verify(token);
      }catch (Exception e){
        halt(401,"Unauthorized: Token validation failed");
      }
    }

    public static Optional<String> getToken(Request request){
      String authorization = request.headers("authorization");
      if(authorization == null){
        halt(401, "Unauthorized: no authorization token present");
      }else{
        String[] parts = authorization.split(" ");

        if (parts.length != 2) {
          halt(401, "Unauthorized: Format is Authorization: Bearer [token]");
        }

        String scheme = parts[0];
        String credentials = parts[1];

        if (pattern.matcher(scheme).matches()) {
          return Optional.of(credentials);
        }
      }

      return Optional.empty();
    }
  }

}
