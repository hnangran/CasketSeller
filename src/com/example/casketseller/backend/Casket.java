package com.example.casketseller.backend;

import android.graphics.Bitmap;

public class Casket {

  private String exteriorMaterial;

  private String interiorMaterial;

  private String name;

  private double price;

  private String url;

  private Bitmap image;
  
  public Casket(){}
  
  public Casket(String exteriorMaterial, String interiorMaterial, String name, double price,
          String url) {
    super();
    this.exteriorMaterial = exteriorMaterial;
    this.interiorMaterial = interiorMaterial;
    this.name = name;
    this.price = price;
    this.url = url;
  }

  public Casket(String exteriorMaterial, String interiorMaterial, String name, double price,
          String url, Bitmap image) {
    super();
    this.exteriorMaterial = exteriorMaterial;
    this.interiorMaterial = interiorMaterial;
    this.name = name;
    this.price = price;
    this.url = url;
    this.image = image;
  }

  public String getExteriorMaterial() {
    return exteriorMaterial;
  }

  public void setExteriorMaterial(String exteriorMaterial) {
    this.exteriorMaterial = exteriorMaterial;
  }

  public String getInteriorMaterial() {
    return interiorMaterial;
  }

  public void setInteriorMaterial(String interiorMaterial) {
    this.interiorMaterial = interiorMaterial;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Bitmap getImage() {
    return image;
  }

  public void setImage(Bitmap image) {
    this.image = image;
  }

}
