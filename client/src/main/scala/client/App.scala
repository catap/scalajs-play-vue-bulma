package client


import scala.scalajs.js._
import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.annotation._
import scala.scalajs.vuejs._

object App extends JSApp {
  import components._

  @JSExport
  def main(): Unit =
    new Vue(literal(
        el = "#main",
        components = literal(
          sampler = Simpler.component,
          counter = Counter.component,
          toggle = Toggle.component,
          model = Model.component,
          loop = Loop.component,
          api = Api.component
        )
      )
    )
}