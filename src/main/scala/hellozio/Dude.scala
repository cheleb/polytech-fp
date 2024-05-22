package hellozio

import zio.json.JsonCodec

final case class Dude(id: Int, name: String, email: Option[String])
    derives JsonCodec
