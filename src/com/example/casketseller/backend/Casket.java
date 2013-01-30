package com.example.casketseller.backend;

import android.graphics.Bitmap;

public class Casket {

  private String exteriorMaterial;

  private String color;

  private String name;

  private double price;

  private String url;

  private String sdCardPath;

  private Bitmap image;

  public Casket() {
  }

  public Casket(String exteriorMaterial, String color, String name, double price, String url, String sdCardPath) {
    super();
    this.exteriorMaterial = exteriorMaterial;
    this.color = color;
    this.name = name;
    this.price = price;
    this.url = url;
    this.setSdCardPath(sdCardPath);
  }

  public Casket(String exteriorMaterial, String color, String name, double price, String url, String sdCardPath,
          Bitmap image) {
    super();
    this.exteriorMaterial = exteriorMaterial;
    this.color = color;
    this.name = name;
    this.price = price;
    this.url = url;
    this.setSdCardPath(sdCardPath);
    this.image = image;
  }

  public String getExteriorMaterial() {
    return exteriorMaterial;
  }

  public void setExteriorMaterial(String exteriorMaterial) {
    this.exteriorMaterial = exteriorMaterial;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
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

  public String getSdCardPath() {
    return sdCardPath;
  }

  public void setSdCardPath(String sdCardPath) {
    this.sdCardPath = sdCardPath;
  }

  public boolean equals(Object o) {

    boolean casketMatch = false;

    Casket c = (Casket) o;

    if (c != null) {

      if (c.getExteriorMaterial() != null && c.getExteriorMaterial().length() != 0)
        if (this.exteriorMaterial.equals(c.getExteriorMaterial()))
          casketMatch = true;
        else
          return false;
      if (c.getColor() != null && c.getColor().length() != 0)
        if (this.color.equals(c.getColor()))
          casketMatch = true;
        else
          return false;
      if (c.getName() != null && c.getName().length() != 0)
        if (this.name.equals(c.getName()))
          casketMatch = true;
        else
          return false;

    }

    return casketMatch;

  }

  public boolean isEmpty() {

    if ((exteriorMaterial == null || exteriorMaterial.length() == 0)
            && (color == null || color.length() == 0) && (name == null || name.length() == 0))
      return true;
    return false;
  }

}
