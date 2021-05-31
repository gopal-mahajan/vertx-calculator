package com.example.demo.service;

import com.example.demo.exception.ArithmeticExceptionHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class CalculatorServiceVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> future) {

    Router router = Router.router(vertx);
    router.get("/:operation/:no1/:no2").handler(this::getSolution);

    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(
            config().getInteger("http.port", 8080),
            result -> {
              if (result.succeeded()) {
                future.complete();
              } else {
                future.fail(result.cause());
              }
            });
  }

  private void getSolution(RoutingContext routingContext) {
    String operation = routingContext.request().getParam("operation");
    int temp = Integer.parseInt(routingContext.request().getParam("no1"));
    int temp2 = Integer.parseInt(routingContext.request().getParam("no2"));
    int ans = 0;
    switch (operation) {
      case ("add"):
        ans = temp + temp2;
        break;
      case ("sub"):
        ans = temp - temp2;
        break;
      case ("multiply"):
        ans = temp * temp2;
        break;
      case ("divide"):
        try {
          ans = temp / temp2;
          break;
        } catch (ArithmeticException e) {
          routingContext
              .response()
              .exceptionHandler(throwable -> new ArithmeticExceptionHandler())
              .end("Cannot divide by zero ");
          return;
        }
    }

    routingContext.response().end("Ans is " + Json.encodePrettily(ans));
  }
}
