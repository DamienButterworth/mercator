import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CheckoutSpec extends AnyWordSpec with Matchers {

  ".calculateTotal" should {
    "return the sum of all products if no errors present" in {
      val input: List[String] = List("Apple", "Orange")
      val checkout: Checkout = new Checkout
      checkout.calculateTotal(input) shouldBe Right(BigDecimal(0.85))
    }

    "return the sum of all products applying a discount on number of apples" in {
      val input: List[String] = List("Apple", "Apple", "Apple")
      val checkout: Checkout = new Checkout
      checkout.calculateTotal(input) shouldBe Right(BigDecimal(1.2))
    }

    "return the sum of all products applying a discount on the number of oranges" in {
      val input: List[String] = List("Orange", "Orange", "Orange", "Orange", "Orange")
      val checkout: Checkout = new Checkout
      checkout.calculateTotal(input) shouldBe Right(BigDecimal(1.0))
    }

    "return an accumalation of errors if multiple items are incorrect" in {
      val input: List[String] = List("expected", "error")
      val checkout: Checkout = new Checkout
      checkout.calculateTotal(input) shouldBe Left("Unknown item: expected, Unknown item: error")
    }
  }
}
