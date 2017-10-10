package components

import scala.scalajs.js.Dynamic.literal

object Model {
  val component = literal(
    data = () => {
      literal(
        model = ""
      )
    },
    template = "<div><h3>Model</h3>" +
      "<p>{{model}}</p> <input v-model='model'/> " +
      "</div>"
  )
}
