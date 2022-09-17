package com.view.plantview.sensor.api.application.user.update;

import com.view.plantview.sensor.api.application.UseCase;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateUserUseCase extends UseCase<UpdateUserCommand, Either<Notification, UpdateUserOutput>> {
}
