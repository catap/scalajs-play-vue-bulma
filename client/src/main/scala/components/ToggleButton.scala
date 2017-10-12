package components

import client.Types._

import scala.scalajs.js.Dynamic.literal

object ToggleButton {
  val component = literal(
    methods = literal(
      toggle = (data => data.$root.$emit("toggleButton")): VueMethod
    ),
    template = "<div><h3>Toggle Button</h3><button @click='toggle'>Toggle</button></div>"
  )
}
