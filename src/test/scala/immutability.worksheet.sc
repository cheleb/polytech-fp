class MutableBalance(var balance: Double) {
  def deposit(amount: Double): Unit = {
    balance += amount
  }
  override def toString(): String = s"$balance"
}

val balance = new MutableBalance(100)
balance.deposit(50)

case class ImmutableBalance(balance: Double) {
  def deposit(amount: Double): ImmutableBalance = {
    ImmutableBalance(balance + amount)
  }
}

val immutableBalance = ImmutableBalance(100)
immutableBalance
  .deposit(50)
