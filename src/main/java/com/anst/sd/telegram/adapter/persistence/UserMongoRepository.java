package com.anst.sd.telegram.adapter.persistence;

import com.anst.sd.telegram.domain.user.UserCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<UserCode, String> {
    Boolean existsByTelegramId(String telegramId);

    Optional<UserCode> findByTelegramId(String telegramId);
}
