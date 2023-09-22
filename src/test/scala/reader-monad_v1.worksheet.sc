import java.sql.Connection

case class User(name: String, age: Int)

trait ConnectionPool {
  def getConnection: Connection
}

val pool = new ConnectionPool {
  def getConnection: Connection = ???
}

// The spring way
class UserRepository(pool: ConnectionPool) {
  def createUser(userName: String, age: Int): User = User(userName, age)
}

// The functional way ... but we need to pass the pool everywhere
class UserRepository_V2 {
  def createUser(userName: String, age: Int, pool: ConnectionPool): User =
    User(userName, age)
}

// Currying to the rescue
class UserRepository_V3 {
  def createUser(userName: String, age: Int)(pool: ConnectionPool): User =
    User(userName, age)
}

class UserRepository_V3_1 {
  def createUser(userName: String, age: Int): ConnectionPool => User = { pool =>
    User(userName, age)
  }
}

case class Reader(fun: ConnectionPool => User)

class UserRepository_V4 {
  def createUser(userName: String, age: Int): Reader = Reader { pool =>
    User(userName, age)
  }
}
