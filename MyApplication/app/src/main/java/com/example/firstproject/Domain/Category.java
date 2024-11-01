package com.example.firstproject.Domain;

public class Category {
    private int Id;
    private String ImagePath;
    private String Name;

    public Category() {
    }

    public Category(int id, String imagePath, String name) {
        this.Id = id;
        ImagePath = imagePath;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
