import model.{Apple, Orange, StoreProduct}

class Checkout {

  def calculateTotal(items: List[String]): Either[String, BigDecimal] = {
      items.map(StoreProduct.fromString).partitionMap(identity) match {
      case (Nil, products) =>
        val currentTotal: BigDecimal = products.map(_.cost).sum
        Right(applyDiscounts(currentTotal, products))
      case (errors, _) => Left(errors.combineErrors)
    }
  }

  private def applyDiscounts(total: BigDecimal, storeProducts: List[StoreProduct]): BigDecimal = {
    val oranges: List[StoreProduct] = storeProducts.collect { case orange@Orange => orange}
    val apples: List[StoreProduct] = storeProducts.collect { case apple@Apple => apple }

    val appleDiscount: BigDecimal = (apples.size / 2) * Apple.cost
    val orangeDiscount: BigDecimal = (oranges.size / 3) * Orange.cost

    total - (appleDiscount + orangeDiscount)
  }
}
