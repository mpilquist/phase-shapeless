package phase

import shapeless._
import shapeless.record._
import shapeless.ops.hlist.ToTraversable
import shapeless.ops.record.Fields

object labels {

  case class Point(x: Int, y: Int, z: Int)

  def showPoint(p: Point): String = {
    val rec = LabelledGeneric[Point].to(p)
    rec.fields.toList.map { case (k, v) => s"${k.name}: $v" }.mkString("Point(", ", ", ")")
  }

  def show[A, R <: HList, F <: HList](a: A)(implicit
    typ: Typeable[A],
    lgen: LabelledGeneric.Aux[A, R],
    fields: Fields.Aux[R, F],
    toList: ToTraversable.Aux[F, List, (Symbol, Any)]
  ): String = {
    val rec = lgen.to(a)
    rec.fields.toList.map { case (k, v) => s"${k.name}: $v" }.mkString(typ.describe + "(", ", ", ")")
  }

  case class Point2D(x: Int, y: Int) {
    override def toString = show(this)
  }

  case class Foo[A](value: A)(implicit t: Typeable[Foo[A]]) {
    override def toString = show(this)
  }
}
