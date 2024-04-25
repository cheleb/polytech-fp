def f = for {
  i1 <- Some(1)
  i2 <- Some(2)
  i3 <- Some(3)
} yield i1 + i2 + i3
