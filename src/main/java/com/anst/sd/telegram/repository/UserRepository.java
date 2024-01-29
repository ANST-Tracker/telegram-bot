package com.anst.sd.telegram.repository;

import com.anst.sd.telegram.model.constant.UserCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository <UserCode, String>{
    Optional<UserCode> findByTelegramId(String telegramId);
}
