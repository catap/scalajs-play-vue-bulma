package shared

import scala.concurrent.Future

trait Api {
  def requestResponse(request: SharedRequest) : Future[SharedResponse]
}