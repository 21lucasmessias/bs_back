package io.sanctuary.bs.api.application.user.update;

import io.sanctuary.bs.api.application.UseCase;
import io.sanctuary.bs.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateUserUseCase extends UseCase<UpdateUserCommand, Either<Notification, UpdateUserOutput>> {
}
