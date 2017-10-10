package components

import scala.scalajs.js.Array
import scala.scalajs.js.Dynamic.literal

object Loop {
  val component = literal(
    data = () => {
      literal(
        todos = Array("TODO 1", "TODO 2", "TODO 3")
      )
    },
    template = "<div><h3>Loop</h3>" +
      "<ol><li v-for='todo in todos'>{{todo}}</li></ol> " +
      "</div>"
  )
}
