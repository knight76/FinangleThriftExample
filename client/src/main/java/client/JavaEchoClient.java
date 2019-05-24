package client;

import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import com.twitter.util.Function;
import com.twitter.util.Future;
import idl.thrift.HelloService;
import scala.runtime.BoxedUnit;

public final class JavaEchoClient {

  public static void main(String[] args) throws Exception {
    System.out.println("Starting Java client...");

    HelloService.MethodPerEndpoint client = Thrift.client().newIface("localhost:8080", HelloService.MethodPerEndpoint.class);
    Future<String> response = client.hi().onSuccess(new Function<String, BoxedUnit>() {
      @Override
      public BoxedUnit apply(String response) {
        System.out.println("Received response: " + response);
        return null;
      }
    });

    Await.result(response);
  }
}
