package com.aaitabem.abugida.abugida.Model.api;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class BookTicket {
    private String bspId;
    private String travelId;
    private String quantity;

    public String getBspId() {
        return bspId;
    }

    public String getTravelId() {
        return travelId;
    }

    public String getQuantity() {
        return quantity;
    }


    public BookTicket(String bspId, String _id, String numberOfSeats) {
        this.bspId = bspId;
        this.travelId = _id;
        this.quantity = numberOfSeats;
    }

}
