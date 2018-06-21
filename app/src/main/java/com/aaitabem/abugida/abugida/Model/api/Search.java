package com.aaitabem.abugida.abugida.Model.api;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class Search {
    private String bspName;
    private String bspId;
    private String routeId;
    private String busId;
    private String travelDate;
    private String departureTime;
    private String seatLeft;
    private String price;
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSeatLeft() {
        return seatLeft;
    }

    public void setSeatLeft(String seatLeft) {
        this.seatLeft = seatLeft;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBspID() {
        return bspId;
    }

    public void setBspID(String bspID) {
        this.bspId = bspID;
    }

    public String getRouteID() {
        return routeId;
    }

    public void setRouteID(String routeID) {
        this.routeId = routeID;
    }

    public String getBusID() {
        return busId;
    }

    public void setBusID(String busID) {
        this.busId = busID;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getBspName() {
        return bspName;
    }

    public void setBspName(String bspName) {
        this.bspName = bspName;
    }
}
