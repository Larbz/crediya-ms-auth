package pe.com.pragma.r2dbc.user;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.com.pragma.model.user.User;
import pe.com.pragma.model.user.gateways.UserRepository;
import pe.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryAdapter extends ReactiveAdapterOperations<
        User/* change for domain model */,
    UserEntity/* change for adapter model */,
    Long,
        UserReactiveRepository
> implements UserRepository {


    public UserRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, User.class/* change for domain model */));
    }

    @Override
    public Mono<User> save(User entity) {
        return super.save(entity);
    }

    @Override
    public Mono<User> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return super.findAll();
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return null;
    }

    // Conversión de Entity → Domain
    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDni(),
                entity.getPhone(),
                entity.getRoleId(),
                entity.getBaseSalary()
        );
    }
    //
//    // Conversión de Domain → Entity
    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getDni(),
                user.getPhone(),
                user.getRoleId(),
                user.getBaseSalary()
        );
    }

}
