import java.util.UUID
import java.sql.Connection

case class User(id: UUID, name: String, age: Int)
case class Account(userId: UUID, balance: Int)

trait ConnectionPool {
  def getConnection: Connection
}

val pool = new ConnectionPool {
  def getConnection: Connection = ???
}

case class Reader[A, B](fun: A => B):
  def map[C](f: B => C): Reader[A, C] = Reader { a =>
    f(fun(a))
  }

  def flatMap[C](f: B => Reader[A, C]): Reader[A, C] = Reader { a =>
    f(fun(a)).fun(a)
  }

def createUser(userName: String, age: Int): Reader[ConnectionPool, User] =
  Reader { pool =>
    User(UUID.randomUUID(), userName, age)
  }
def createBankAccount(
    userId: UUID,
    amount: Int
): Reader[ConnectionPool, Account] =
  Reader { pool =>
    Account(userId, amount)
  }

val userAndBankAccountR = for {
  user <- createUser("test", 18)
  account <- createBankAccount(user.id, 1000)
} yield (user, account)

userAndBankAccountR.fun(pool)
