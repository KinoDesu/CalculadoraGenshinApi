package dev.kinodesu.service;

import dev.kinodesu.model.request.GenshinUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GenshinUserService {
    List<GenshinUser> getAllUsers();

    GenshinUser save(GenshinUser newGenshinUser);

    Optional<GenshinUser> getUserByUid(String uid);

    void updateUser(GenshinUser newGenshinUser);
}
