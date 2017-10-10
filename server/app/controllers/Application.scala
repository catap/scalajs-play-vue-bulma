package controllers

import java.nio.ByteBuffer
import javax.inject._

import boopickle.Default._

import org.webjars.play.WebJarsUtil
import play.api.mvc._

import shared.Api
import services.ApiService

import scala.concurrent.ExecutionContext.Implicits.global

object Router extends autowire.Server[ByteBuffer, Pickler, Pickler] {
  override def read[R: Pickler](p: ByteBuffer) = Unpickle[R].fromBytes(p)
  override def write[R: Pickler](r: R) = Pickle.intoBytes(r)
}

@Singleton
class Application @Inject()(implicit cc: ControllerComponents, webJarsUtil: WebJarsUtil) extends AbstractController(cc) {
  val apiService = new ApiService()

  def index = Action {
    Ok(views.html.index())
  }

  def autowireApi(path: String) = Action.async(parse.raw) {
    implicit request =>
      println(s"Request path: $path")

      // get the request body as ByteString
      val b = request.body.asBytes(parse.UNLIMITED).get

      // call Autowire route
      Router.route[Api](apiService)(
        autowire.Core.Request(path.split("/"), Unpickle[Map[String, ByteBuffer]].fromBytes(b.asByteBuffer))
      ).map(buffer => {
        val data = Array.ofDim[Byte](buffer.remaining())
        buffer.get(data)
        Ok(data)
      })
  }
}
