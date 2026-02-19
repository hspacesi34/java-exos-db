package com.projet.repository;

import com.projet.entity.AbstractEntity;
import com.projet.entity.Category;
import com.projet.entity.Game;
import com.projet.entity.Manufacturer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GameRepository extends AbstractRepository<Game> {
    public Game save(Game game) {
        try {
            String requete = "INSERT INTO game (title, description, manufacturer_id) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, game.getDescription());
            preparedStatement.setInt(3, game.getManufacturer().getId());

            preparedStatement.executeUpdate();

            ResultSet rsKeys = preparedStatement.getGeneratedKeys();
            int gameId = 0;
            if (rsKeys.next()) {
                gameId = rsKeys.getInt(1);
            }

            String insertGameCategory = "INSERT INTO game_category (game_id, category_id) VALUES (?, ?)";
            PreparedStatement psGC = connection.prepareStatement(insertGameCategory);

            for (Category category : game.getCategories()) {
                psGC.setInt(1, gameId);
                psGC.setInt(2, category.getId());
                psGC.addBatch(); // batch pour optimiser
            }
            psGC.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public Game find(int id) {
        Game game = null;
        try {
            String requete =
                    "SELECT g.id, g.title, g.description, g.publish_at, " +
                    "m.id AS manufacturer_id, m.name AS manufacturer_name, " +
                    "c.id AS category_id, c.name AS category_name " +
                    "FROM game g " +
                    "JOIN manufacturer m ON g.manufacturer_id = m.id " +
                    "LEFT JOIN game_category gc ON g.id = gc.game_id " +
                    "LEFT JOIN category c ON gc.category_id = c.id " +
                    "WHERE g.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                if (game == null) {
                    game = new Game();
                    game.fromResultSet(result);
                }
                game.addCategoryFromResultSet(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public ArrayList<Game> findAll() {
        ArrayList<Game> games = new ArrayList<>();
        Integer lastGameId = null;
        Game currentGame = null;
        try {
            String requete =
                    "SELECT " +
                    "    g.id, " +
                    "    g.title, " +
                    "    g.description, " +
                    "    g.publish_at, " +
                    "    m.id AS manufacturer_id, " +
                    "    m.name AS manufacturer_name, " +
                    "    c.id AS category_id, " +
                    "    c.name AS category_name " +
                    "FROM game g " +
                    "JOIN manufacturer m ON g.manufacturer_id = m.id " +
                    "LEFT JOIN game_category gc ON g.id = gc.game_id " +
                    "LEFT JOIN category c ON gc.category_id = c.id " +
                    "ORDER BY g.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int gameId = result.getInt("id");

                if (lastGameId == null || gameId != lastGameId) {
                    currentGame = new Game();
                    currentGame.fromResultSet(result);
                    games.add(currentGame);
                    lastGameId = gameId;
                }

                currentGame.addCategoryFromResultSet(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }

    public void delete(int id) {
        try {
            String requete = "DELETE FROM game WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
