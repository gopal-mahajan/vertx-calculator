package com.example.demo.exception;


import io.vertx.core.Handler;


public class ArithmeticExceptionHandler implements Handler<ArithmeticException> {
  @Override
  public void handle(ArithmeticException e) {
  }
}
