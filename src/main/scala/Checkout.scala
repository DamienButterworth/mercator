import EmptyInput.ErrorResponseOps

class Checkout {

  def calculateTotal(items: List[String]): Either[String, BigDecimal] = {
      items.map(StoreProduct.fromString).partitionMap(identity) match {
      case (Nil, products) => Right(products.map(_.cost).sum)
      case (errors, _) => Left(errors.combineErrors)
    }
  }
}
