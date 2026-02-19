package com.projet.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Game extends AbstractEntity {
    private int id;
    private String title;
    private String description;
    private ArrayList<Category> categories = new ArrayList<Category>();
    private Manufacturer manufacturer;
    private LocalDate publishAt;

    public Game(){}

    public Game(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Game(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", manufacturer=" + manufacturer +
                ", publishAt=" + publishAt +
                '}';
    }

    public void fromResultSet(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.title = rs.getString("title");
        this.description = rs.getString("description");

        if (rs.getDate("publish_at") != null) {
            this.publishAt = rs.getDate("publish_at").toLocalDate();
        }

        this.manufacturer = new Manufacturer(
                rs.getInt("manufacturer_id"),
                rs.getString("manufacturer_name")
        );
    }

    public void addCategoryFromResultSet(ResultSet rs) throws SQLException {
        int categoryId = rs.getInt("category_id");
        if (!rs.wasNull()) {
            boolean alreadyAdded = categories.stream().anyMatch(c -> c.getId() == categoryId);
            if (!alreadyAdded) {
                Category category = new Category(
                        categoryId,
                        rs.getString("category_name")
                );
                this.addCategory(category);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(LocalDate publishAt) {
        this.publishAt = publishAt;
    }
}
