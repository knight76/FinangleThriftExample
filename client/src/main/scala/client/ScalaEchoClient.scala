package client

import com.twitter.finagle.Thrift
import com.twitter.util.Await
import idl.thrift.HelloService

object ScalaEchoClient {
  def main(args: Array[String]): Unit = {
    println("Starting Scala client...")

    val methodPerEndpoint: HelloService.MethodPerEndpoint =
      Thrift.client.build[HelloService.MethodPerEndpoint]("localhost:8080")

    val response = methodPerEndpoint.hi()
    response onSuccess {
      result: String => println(result)
    }

    Await.result(response)
  }
}
