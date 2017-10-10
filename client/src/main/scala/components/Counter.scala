package components

import client.Types._

import scala.scalajs.js.Dynamic.literal

object Counter {
  val component = literal(
    data = () => {
      literal(
        count = 0
      )
    },
    methods = literal(
      increment = ((data: Data) =>
        data.count += 1
        ): VueMethod
    ),
    template = "<div><h3>Increment</h3> <p>{{count}}</p> " +
      "<button @click='increment'>Increment</button> " +
      "</div>"
  )
}
