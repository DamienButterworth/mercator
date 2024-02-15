sealed trait StoreProduct {
  val itemName: String
  val cost: BigDecimal
}

object StoreProduct {
  def fromString(value: String): Either[ErrorResponse, StoreProduct] =
    value.toLowerCase.trim match {
      case Orange.itemName => Right(Orange)
      case Apple.itemName => Right(Apple)
      case other if other.isEmpty => Left(EmptyInput)
      case other => Left(InvalidItem(other))
    }
}

case object Orange extends StoreProduct {
  override val itemName: String = "orange"
  override val cost: BigDecimal = BigDecimal.decimal(0.25)
}

case object Apple extends StoreProduct {
  override val itemName: String = "apple"
  override val cost: BigDecimal = BigDecimal.decimal(0.6)
}
