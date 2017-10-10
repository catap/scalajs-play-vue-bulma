package components

import client.Types._

import scala.scalajs.js.Dynamic.literal

object Toggle {
  val component = literal(
    data = () => {
      literal(
        on = true,
        display = "On"
      )
    },
    methods = literal(
      toggle = ((data: Data) => data.on = !data.on): VueMethod
    ),
    template = "<div><h3>Toggle</h3> <div v-if='on'><p>{{display}}</p></div> " +
      "<button @click='toggle'>Toggle</button> " +
      "</div>"
  )
}
