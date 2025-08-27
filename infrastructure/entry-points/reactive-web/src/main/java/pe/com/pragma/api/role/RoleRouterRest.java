package pe.com.pragma.api.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouterRest {
//    @Bean
//    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
//        return route(GET("/api/usecase/path"), handler::listenGETUseCase)
//                .andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase)
//                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase));
//    }
@Bean
public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
    return route()
            .path("/api/v1/users",builder -> builder.GET("/{id}", userHandler::getUserById).GET( userHandler::getAll)
            )
            .build();
}
}
