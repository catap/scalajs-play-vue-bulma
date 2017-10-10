package services

import java.nio.ByteBuffer

import autowire._
import boopickle.Default._
import org.scalajs.dom.ext.Ajax
import shared.Api

import scala.scalajs.js.typedarray._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object AjaxClient extends Client[ByteBuffer, Pickler, Pickler] {
  override def doCall(req: Request): Future[ByteBuffer] = {
    Ajax.post(
      url = "/api/" + req.path.mkString("/"),
      data = Pickle.intoBytes(req.args),
      responseType = "arraybuffer",
      headers = Map("Content-Type" -> "application/octet-stream")
    ).map(r => TypedArrayBuffer.wrap(r.response.asInstanceOf[ArrayBuffer]))
  }

  override def read[Result: Pickler](p: ByteBuffer): Result = Unpickle[Result].fromBytes(p)

  override def write[Result: Pickler](r: Result): ByteBuffer = Pickle.intoBytes(r)
}
