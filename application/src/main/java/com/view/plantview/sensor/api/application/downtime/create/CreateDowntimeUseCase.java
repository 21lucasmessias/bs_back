package com.view.plantview.sensor.api.application.downtime.create;

import com.view.plantview.sensor.api.application.UseCase;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateDowntimeUseCase extends UseCase<CreateDowntimeCommand, Either<Notification, CreateDowntimeOutput>> {
}
