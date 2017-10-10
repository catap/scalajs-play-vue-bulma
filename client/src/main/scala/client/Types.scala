package client

import scala.scalajs.js.{Any, Array, ThisFunction0, UndefOr, native}
import scala.scalajs.vuejs.Vue

object Types {
  @native
  trait Data extends Vue {
    var count: Int = native
    var on: Boolean = native
    var display: String = native
    var model: String = native
    var todos: Array[String] = native
    var request: String = native
    var response: UndefOr[String] = native
  }

  type VueMethod = ThisFunction0[Data, Any]
}
