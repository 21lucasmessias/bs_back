package com.view.plantview.sensor.api.application.downtime.update;

import com.view.plantview.sensor.api.application.UseCase;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateDowntimeUseCase extends UseCase<UpdateDowntimeCommand, Either<Notification, UpdateDowntimeOutput>> {
}
