package components

import shared._
import client.Types._
import services.ApiClient

import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.undefined

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object Api {
  val component = literal(
    data = () => {
      literal(
        request = "some text here",
        response = undefined
      )
    },
    methods = literal(
      fetchResponse = ((data: Data) =>
        ApiClient.requestResponse(SharedRequest(data.request)) onComplete {
          case Failure(t) =>
            data.response = s"Error happened! ${t.getMessage}"

          case Success(SharedResponse(msg)) if msg.isEmpty =>
            data.response = undefined

          case Success(SharedResponse(msg)) =>
            data.response = msg
        }
        ): VueMethod
    ),
    template = "<div><h3>API call</h3> <div v-if='response'><p>{{response}}</p></div> " +
      "<input v-model='request'/>  <button @click='fetchResponse'>Fetch!</button> " +
      "</div>"
  )
}
