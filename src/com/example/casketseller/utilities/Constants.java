package com.example.casketseller.utilities;

public class Constants {

  public enum Activities {
    
    searchScreen ("com.example.casketseller.activities.SearchScreen"),
    casketsDisplay ("com.example.casketseller.activities.CasketsDisplay");

    private String activity;

    Activities(String s) {
      activity = s;
    }
    
    public String toString(){
      return activity;
    }
    
  }

}
