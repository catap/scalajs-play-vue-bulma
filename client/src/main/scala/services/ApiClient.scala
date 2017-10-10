package services

import shared._
import autowire._
import boopickle.Default._

import scala.language.implicitConversions
import scala.concurrent.ExecutionContext.Implicits.global

object ApiClient extends Api {
  private val apiClient = AjaxClient[Api]

  override def requestResponse(request: SharedRequest) =
    apiClient.requestResponse(request).call()
}
