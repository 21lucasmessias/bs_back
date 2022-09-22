package io.sanctuary.bs.api.application.user.create;

import io.sanctuary.bs.api.application.UseCase;
import io.sanctuary.bs.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateUserUseCase extends UseCase<CreateUserCommand, Either<Notification, CreateUserOutput>> {

}
