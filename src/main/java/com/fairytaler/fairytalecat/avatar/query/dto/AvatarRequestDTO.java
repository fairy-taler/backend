package com.fairytaler.fairytalecat.avatar.query.dto;

public class AvatarRequestDTO {
    private String animal;
    private String material;
    private String objectName;

    public AvatarRequestDTO() {}

    public AvatarRequestDTO(String animal, String material, String objectName) {
        this.animal = animal;
        this.material = material;
        this.objectName = objectName;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public String toString() {
        return "AvatarRequestDTO{" +
                "animal='" + animal + '\'' +
                ", material='" + material + '\'' +
                ", objectName='" + objectName + '\'' +
                '}';
    }
}
