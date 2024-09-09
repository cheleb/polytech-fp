package hellofs2

import io.circe.Codec

final case class Dude(id: Int, name: String, email: Option[String])
    derives Codec.AsObject
