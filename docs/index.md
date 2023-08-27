# Functional programming in Scala.

## Introduction

This is a collection of notes and exercises about functional programming in Scala.

The goal is to provide a quick introduction to functional programming in Scala.

## Prerequisites

* Basic knowledge of types and functions
* Basic knowledge of Scala


## What is functional programming?

"Functional programming is a programming paradigm where programs are constructed by **applying and composing functions**. It is a declarative programming paradigm in which function definitions are trees of expressions that each return a value, rather than a sequence of imperative statements which change the state of the program." (Wikipedia)

Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and **avoids changing-state and mutable data**.

Functional programming is a **declarative** programming paradigm, which means programming is done with expressions or declarations instead of statements.

Functional code is **idempotent**: the same input always gives the same output.

Functional code is also modular: it's easier to test, reuse and parallelize.


## Basic scala

* Traits
* Case Classes
* Pattern Matching
* For Comprehensions
* Sample: Options 

See [Basic Scala](../src/test/scala/basic.worksheet.sc)

## Functions, Methods et lambdas

[Functions...](./function-method-lambda.md)


See [Functions worksheet](../src/test/scala/lambda.worksheet.sc)

## Immutability

* val vs var
* Immutable collections

## Recursion and Tail Recursion

* Stack safety
* Tail Recursion Optimization

## Higher Order Functions

* map
* flatMap
* filter
...

## Currying

## Partially Applied Functions

## Lazy Evaluation

## Streams

## Monads

## IOs and Effects


