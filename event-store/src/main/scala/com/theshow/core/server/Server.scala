package com.theshow.core.server

import cats.effect.kernel.Async
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.ExecutionContext.global

object Server {
  def Stream[F[_]:Async] = {
    BlazeServerBuilder[F](global)
      .bindHttp(8080,"localhost")
      .withHttpApp()
      .serve
      .compile
      .drain
  }
}
