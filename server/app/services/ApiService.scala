package services

import shared._

import scala.concurrent.Future

class ApiService extends Api {
  override def requestResponse(request: SharedRequest)  = Future.successful {
      SharedResponse(s"response for request: ${request.message}")
    }
}
