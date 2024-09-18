package dev.kinodesu.infra.repository;

import dev.kinodesu.infra.configs.H2Database;
import dev.kinodesu.model.request.GenshinUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenshinUserRepository {

    private final H2Database database;
    private final Connection conn;
    private String query;

    public List<GenshinUser> findAllGenshinUsers() {
        List<GenshinUser> genshinUserList = new ArrayList<>();
        try {
            query = "SELECT user_uid as uid, user_token as token, user_mid as mid, user_name as name FROM genshin_user";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String uid = rs.getString("uid");
                String token = rs.getString("token");
                String mid = rs.getString("mid");
                String name = rs.getString("name");
                genshinUserList.add(new GenshinUser(uid, token, mid, name));
            }

        } catch (SQLException e) {
            System.err.println(e);
            genshinUserList.clear();
        }

        return genshinUserList;
    }

    public Optional<GenshinUser> findGenshinUserByUid(String searchUid) {
        Optional<GenshinUser> genshinUser = Optional.empty();
        query = "SELECT user_uid as uid, user_token as token, user_mid as mid, user_name as name FROM genshin_user " +
                "WHERE user_uid LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, searchUid);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String uid = rs.getString("UID");
                String token = rs.getString("TOKEN");
                String mid = rs.getString("MID");
                String name = rs.getString("NAME");
                genshinUser = Optional.of(new GenshinUser(uid, token, mid, name));
            }


        } catch (SQLException e) {
            System.err.println(e);
        }

        return genshinUser;
    }

    public GenshinUser save(GenshinUser newGenshinUser) {
        GenshinUser genshinUser = findGenshinUserByUid(newGenshinUser.getUid()).orElse(null);

        if (genshinUser == null) {
            query = "INSERT INTO genshin_user (user_uid, user_token, user_mid, user_name) VALUES (?,?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newGenshinUser.getUid());
                pstmt.setString(2, newGenshinUser.getToken());
                pstmt.setString(3, newGenshinUser.getMid());
                pstmt.setString(4, newGenshinUser.getName());

                pstmt.executeUpdate();
                genshinUser = newGenshinUser;
            } catch (SQLException e) {
                System.err.println(e);
            }
        } else {
            query = getUpdateQuery(newGenshinUser, genshinUser);

            if (query == null)
                return null;

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                int changedColumnCount = 1;

                if (!newGenshinUser.getToken().equals(genshinUser.getToken())) {
                    pstmt.setString(changedColumnCount++, newGenshinUser.getToken());
                }
                if (!newGenshinUser.getMid().equals(genshinUser.getMid())) {
                    pstmt.setString(changedColumnCount++, newGenshinUser.getMid());
                }
                if (!newGenshinUser.getName().equals(genshinUser.getName())) {
                    pstmt.setString(changedColumnCount++, newGenshinUser.getName());
                }

                if (changedColumnCount == 1)
                    return null;

                pstmt.setString(changedColumnCount, genshinUser.getUid());

                pstmt.executeUpdate();
                genshinUser = newGenshinUser;
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

        return genshinUser;
    }

    private String getUpdateQuery(GenshinUser newGenshinUser, GenshinUser genshinUser) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE genshin_user SET ");
        boolean first = true;

        if (!newGenshinUser.getToken().equals(genshinUser.getToken())) {
            queryBuilder.append("user_token = ?");
            first = false;
        }

        if (!newGenshinUser.getMid().equals(genshinUser.getMid())) {
            if (!first)
                queryBuilder.append(", ");
            queryBuilder.append("user_mid = ?");
        }
        if (!newGenshinUser.getName().equals(genshinUser.getName())) {
            if (!first)
                queryBuilder.append(", ");
            queryBuilder.append("user_name = ?");
        }

        queryBuilder.append(" WHERE user_uid LIKE ?");
        if (!first)
            return queryBuilder.toString();
        return null;
    }
}
