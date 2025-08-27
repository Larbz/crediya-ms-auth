package pe.com.pragma.api.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.pragma.model.user.User;
import pe.com.pragma.usecase.getuserbyid.GetUserByIdUseCase;
import pe.com.pragma.usecase.getusers.GetUsersUseCase;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@AllArgsConstructor
public class UserHandler {
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getUsersUseCase.execute(), User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        return Mono.just(request.pathVariable("id"))
                .map(Long::parseLong)
                .flatMap(getUserByIdUseCase::execute)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).bodyValue(Map.of("error", "User not found")))
                .onErrorResume(NumberFormatException.class, e ->
                        ServerResponse.badRequest().bodyValue("El ID debe ser numérico"));
    }
}
