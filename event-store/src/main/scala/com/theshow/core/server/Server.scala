package com.theshow.core.server

import cats.effect.ExitCode
import cats.effect.kernel.Async
import com.theshow.core.config.Config
import cats.effect.ExitCode
import cats.effect.kernel.Async
import cats.effect.std.Console
import fs2.Stream
import org.http4s.blaze.server.BlazeServerBuilder
import scala.concurrent.ExecutionContext.global

object Server {
  def stream[F[_]:Async: Console](config: Config): fs2.Stream[F,ExitCode] = for {
    _ <- Stream.eval(Console[F].println("Starting the server"))
    server <- BlazeServerBuilder[F] //global is depreciated remove
      .bindHttp(
        config.serverConfig.port.value,
        config.serverConfig.host.host
      )
      .withHttpApp()
      .serve
  } yield server //doesn't map any resting point, will work for now
}
