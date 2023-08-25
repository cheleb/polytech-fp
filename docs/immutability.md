# Immutability

## What is Immutability?

Immutability is the idea that once a value is created, it cannot be changed. This is in contrast to mutability, which is the idea that a value can be changed.

## Why Immutability?

Immutability is a very important concept in functional programming. It allows us to reason about our code in a much simpler way. If we know that a value cannot change, we know that we can safely pass it around our program without worrying about it changing unexpectedly.

* local reasoning
* concurrency
* memoization
* determinism

## How to achieve Immutability?


### Mutable sample
```scala
class MutableBalance(var balance: Double) {
  def deposit(amount: Double): Unit = {
    balance += amount
  }
}
```

Issues:

* balance is mutable
* deposit is a procedure
* deposit is not referentially transparent
* thread safety



```scala
class ImmutableBalance(balance: Double) {
  def deposit(amount: Double): ImmutableBalance = {
    ImmutableBalance(balance + amount)
  }
}
```



## Persistent Data Structures

Persistent data structures are data structures that preserve the previous version of themselves when they are modified. This allows us to use the previous version of the data structure even after it has been modified.









