package com.view.plantview.sensor.api.application.sensor.create;

import com.view.plantview.sensor.api.application.UseCase;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateSensorUseCase extends UseCase<CreateSensorCommand, Either<Notification, CreateSensorOutput>> {

}
