package com.theshow.core.server

import cats.effect.ExitCode
import cats.effect.kernel.Async
import com.theshow.core.config.Config
import cats.effect.ExitCode
import cats.effect.kernel.Async
import fs2.Stream
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.ExecutionContext.global

object Server {
  def Stream[F[_]:Async](config: Config): fs2.Stream[F,ExitCode] = {
    BlazeServerBuilder[F] //global is depreciated remove
      .bindHttp(config.serverConfig.port.value,config.serverConfig.host.host)
      .withHttpApp()
      .serve
  }
}
