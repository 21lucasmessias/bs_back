package com.view.plantview.sensor.api.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}