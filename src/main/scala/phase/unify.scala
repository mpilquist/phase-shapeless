package phase

import shapeless.{ ::, HList, HNil, Lub }

object UnifyExample {

  implicit class HListOps[L <: HList](val l: L) {
    def unify2(implicit u: Unifier[L]): u.Out = u(l)
  }

  sealed trait Unifier[L <: HList] {
    type Out <: HList
    def apply(l: L): Out
  }

  object Unifier {

    type Aux[L0 <: HList, Out0 <: HList] = Unifier[L0] { type Out = Out0 }

    implicit def forHNil: Unifier.Aux[HNil, HNil] = new Unifier[HNil] {
      type Out = HNil
      def apply(l: HNil) = l
    }

    implicit def forOne[H]: Unifier.Aux[H :: HNil, H :: HNil] =
      new Unifier[H :: HNil] {
        type Out = H :: HNil
        def apply(l: H :: HNil) = l
      }

    implicit def forHList[H1, H2, HLub, T <: HList](implicit
      lub: Lub[H1, H2, HLub],
      tailUnifier: Unifier[HLub :: T]
      ): Unifier.Aux[H1 :: H2 :: T, HLub :: tailUnifier.Out] =
        new Unifier[H1 :: H2 :: T] {
          type Out = HLub :: tailUnifier.Out
          def apply(l: H1 :: H2 :: T) =
            lub.left(l.head) :: tailUnifier(lub.right(l.tail.head) :: l.tail.tail)
        }
  }
}
