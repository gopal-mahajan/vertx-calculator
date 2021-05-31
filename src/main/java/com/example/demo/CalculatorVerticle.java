package com.example.demo;

import com.example.demo.service.CalculatorServiceVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
public class CalculatorVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new CalculatorVerticle());
    vertx.deployVerticle(new CalculatorServiceVerticle());
  }

  @Override
  public void start() {
    System.out.println("Started CalculatorVerticle");
  }

  @Override
  public void stop() {
    System.out.println("Shutting Down Application");
  }
}
