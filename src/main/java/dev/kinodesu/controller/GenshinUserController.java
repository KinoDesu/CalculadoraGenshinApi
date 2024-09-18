package dev.kinodesu.controller;

import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.service.impl.GenshinUserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Genshin User")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class GenshinUserController {
    private final GenshinUserServiceImpl genshinUserServiceImpl;

    @GetMapping("list")
    public ResponseEntity<Object> getAllUsers() {
        List<GenshinUser> genshinUserList = genshinUserServiceImpl.getAllUsers();

        return ResponseEntity.ok().body(genshinUserList);
    }

    @GetMapping
    public ResponseEntity<Object> getUserByUid(@RequestParam String uid) {
        GenshinUser genshinUser = genshinUserServiceImpl.getUserByUid(uid).orElse(null);

        if (genshinUser == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(genshinUser);
    }

    @PostMapping
    public ResponseEntity<Object> postUser(@RequestBody GenshinUser newUser) {
        GenshinUser userResponse = genshinUserServiceImpl.save(newUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .queryParam("uid", userResponse.getUid())
                .build()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody GenshinUser newUser) {
        GenshinUser userResponse = genshinUserServiceImpl.save(newUser);

        return ResponseEntity.noContent().build();
    }
}
