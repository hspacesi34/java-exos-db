package com.projet.repository;

import com.projet.entity.Category;
import com.projet.entity.Manufacturer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManufacturerRepository extends AbstractRepository<Manufacturer> {

    public Manufacturer save(Manufacturer manufacturer) {
        try {
            String requete = "INSERT INTO manufacturer (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, manufacturer.getName());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manufacturer;
    }

    public Manufacturer find(int id) {
        Manufacturer manufacturer = null;
        try {
            String requete = "SELECT id, name FROM manufacturer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                manufacturer = new Manufacturer(result.getInt("id"), result.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manufacturer;
    }

    public ArrayList<Manufacturer> findAll() {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        try {
            String requete = "SELECT id, name FROM manufacturer";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Manufacturer manufacturer = new Manufacturer(result.getInt("id"), result.getString("name"));
                manufacturers.add(manufacturer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    public void delete(int id) {
        try {
            String requete = "DELETE FROM manufacturer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
