package dev.kinodesu.service.impl;

import dev.kinodesu.infra.repository.GenshinUserRepository;
import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.service.GenshinUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenshinUserServiceImpl implements GenshinUserService {
    private final GenshinUserRepository genshinUserRepository;

    @Override
    public List<GenshinUser> getAllUsers() {
        return genshinUserRepository.findAllGenshinUsers();
    }

    @Override
    public GenshinUser save(GenshinUser newGenshinUser) {
        return genshinUserRepository.save(newGenshinUser);
    }

    @Override
    public Optional<GenshinUser> getUserByUid(String uid) {
        return genshinUserRepository.findGenshinUserByUid(uid);
    }

    @Override
    public void updateUser(GenshinUser newGenshinUser) {
        save(newGenshinUser);
    }
}
