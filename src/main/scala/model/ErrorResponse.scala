package model

sealed trait ErrorResponse {
  val message: String
}

object ErrorResponse {
  implicit class ErrorResponseOps(private val errors: List[ErrorResponse]) {
    def combineErrors: String = errors.map(_.message).mkString(", ")
  }
}

case class InvalidItem(item: String) extends ErrorResponse {
  override val message: String = s"Unknown item: $item"
}

case object EmptyInput extends ErrorResponse {
  override val message: String = "Empty input"
}