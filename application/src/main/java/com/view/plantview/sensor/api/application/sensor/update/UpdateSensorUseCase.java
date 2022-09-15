package com.view.plantview.sensor.api.application.sensor.update;

import com.view.plantview.sensor.api.application.UseCase;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateSensorUseCase extends UseCase<UpdateSensorCommand, Either<Notification, UpdateSensorOutput>> {
}
