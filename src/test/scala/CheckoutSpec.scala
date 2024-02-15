import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CheckoutSpec extends AnyWordSpec with Matchers {

  ".calculateTotal" should {
    "return the sum of all products if no errors present" in {
      val input: List[String] = List("Apple", "Apple", "Orange", "Apple")
      val checkout: Checkout = new Checkout
      checkout.calculateTotal(input) shouldBe Right(BigDecimal(2.05))
    }

    "return an accumalation of errors if multiple items are incorrect" in {
      val input: List[String] = List("expected", "error")
      val checkout: Checkout = new Checkout
      checkout.calculateTotal(input) shouldBe Left("Unknown item: expected, Unknown item: error")
    }
  }
}
