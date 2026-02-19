package com.projet.repository;

import com.projet.entity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryRepository extends AbstractRepository<Category> {
    public Category save(Category category) {
        try {
            String requete = "INSERT INTO Category (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, category.getName());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public Category find(int id) {
        Category category = null;
        try {
            String requete = "SELECT id, name FROM Category WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                category = new Category();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String requete = "SELECT id, name FROM Category";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

}
