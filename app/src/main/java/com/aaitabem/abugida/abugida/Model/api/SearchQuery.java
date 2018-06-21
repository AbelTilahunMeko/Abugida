package com.aaitabem.abugida.abugida.Model.api;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class SearchQuery {
   private String startingCity;
    private String destinationCity;

    public String getStartingCity() {
        return startingCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

   public SearchQuery(String startingCity, String destinationCity){
       this.destinationCity = destinationCity;
       this.startingCity = startingCity;
   }
}
