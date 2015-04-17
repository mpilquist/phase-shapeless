package phase

object cars {
  case class Make(name: String)
  case class Model(name: String)
  case class Year(value: Int)
  case class Car(make: Make, model: Model, year: Year)

  val tesla = Car(Make("Tesla"), Model("S"), Year(2015))
}

