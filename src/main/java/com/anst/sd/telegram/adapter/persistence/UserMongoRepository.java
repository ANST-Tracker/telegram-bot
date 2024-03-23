package com.anst.sd.telegram.adapter.persistence;

import com.anst.sd.telegram.domain.user.UserCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<UserCode, String> {
    Boolean existsByTelegramId(String telegramId);

    UserCode findByTelegramId(String telegramId);
}