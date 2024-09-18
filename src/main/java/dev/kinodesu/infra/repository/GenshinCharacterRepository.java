package dev.kinodesu.infra.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Stopwatch;
import dev.kinodesu.model.request.GenshinApiRequestBody;
import dev.kinodesu.model.request.GenshinUser;
import dev.kinodesu.model.response.GenshinApiResponse;
import dev.kinodesu.model.response.GenshinCharacter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
@Slf4j
public class GenshinCharacterRepository {

    private static Set<GenshinCharacter> ownedGenshinCharacterList = new HashSet<>();

    public List<GenshinCharacter> getOwnedGenshinCharacterList() {
        return ownedGenshinCharacterList.stream().toList();
    }

    public void setOwnedGenshinCharacterList(List<GenshinCharacter> DbOwnedGenshinCharacterList) {
        ownedGenshinCharacterList = new HashSet<>(DbOwnedGenshinCharacterList);
    }

    private final ObjectWriter writer;

    public HttpResponse<String> findAllOwnedCharacters(GenshinApiRequestBody requestBody,
                                                       GenshinUser genshinUser) {

        String token = genshinUser.getToken();
        String mid = genshinUser.getMid();

        String requestBodyJson = "";

        try {
            requestBodyJson = writer.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }


        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sg-public-api.hoyolab.com/event/calculateos/sync/avatar/list"))
                .header("Content-Type", "application/json")
                .header("Cookie", String.format("ltoken_v2=%s; ltmid_v2=%s", token, mid))
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                .build();

        GenshinApiResponse genshinApiResponse = null;

        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            log.info("Realizando busca por lista de personagens");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            stopwatch.stop();
            log.info("Busca por lista de personagens finalizada | Tempo decorrido: {}",
                    stopwatch.elapsed(TimeUnit.MILLISECONDS));


            return response;
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
}
