package components

import client.Types._

import scala.scalajs.js
import scala.scalajs.vuejs.Vue
import scala.scalajs.js.Dynamic.literal

object Toggle {
  val events = new Vue()

  val component = literal(
    data = () => {
      literal(
        on = true,
        display = "On"
      )
    },
    mounted = ((vue: Vue) => {
      val data = vue.$data.asInstanceOf[Data]
      events.$on("toggleButton", () => {
        data.on = !data.on
      })
    }): js.ThisFunction,
    template = "<div><h3>Toggle</h3> <div v-if='on'><p>{{display}}</p></div></div>"
  )
}
