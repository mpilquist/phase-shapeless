package phase

import shapeless._

object AbstractHLists {

  object sq extends Poly1 {
    implicit def caseInt = at[Int](x => x * x)
    implicit def caseLong = at[Long](x => x * x)
    implicit def caseFloat = at[Float](x => x * x)
    implicit def caseDouble = at[Double](x => x * x)
    implicit def default[A] = at[A](x => x)
  }

  def square[L <: HList](l: L)(implicit
    m: ops.hlist.Mapper.Aux[sq.type, L, L]
  ): L = l map sq
}
