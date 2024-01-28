package podium

import io.ktor.server.application.call
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
    val server = embeddedServer(CIO, 8080) {
        routing {
            get("/") {
                call.respondText("Works")
            }
        }
    }

    server.start(true)
}