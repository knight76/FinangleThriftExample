package example

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}
import idl.thrift.HelloService

object Server {

  class HelloImpl extends HelloService.MethodPerEndpoint {
    def hi(): Future[String] = Future.value("Hello World")
  }

  val server = Thrift.server.serveIface("localhost:8080", new HelloImpl)

  def main(args: Array[String]): Unit = {
    println("Starting thrift server(8080)...")
    Await.result(server)
  }
}
