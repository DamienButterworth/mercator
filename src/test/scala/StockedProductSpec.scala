import model.{Apple, EmptyInput, InvalidItem, Orange, StoreProduct}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StockedProductSpec extends AnyWordSpec with Matchers {

  ".fromString" should {
    "return an InvalidItem when the value does not match either 'orange' or 'apple" in {
      val potentialProduct: String = "invalid"
      StoreProduct.fromString(potentialProduct) shouldBe Left(InvalidItem(potentialProduct))
    }

    "return an EmptyInput when the value is empty" in {
      StoreProduct.fromString("") shouldBe Left(EmptyInput)
    }

    "return Orange and Apple for the correct value" in {
      val orangeStr: String = "orange"
      val appleStr: String = "apple"

      StoreProduct.fromString(orangeStr) shouldBe Right(Orange)
      StoreProduct.fromString(appleStr) shouldBe Right(Apple)
    }

    "return StockedProduct regardless of case or whitespace" in {
      val orangeStr: String = " OrAnGe "
      StoreProduct.fromString(orangeStr) shouldBe Right(Orange)
    }
  }
}
