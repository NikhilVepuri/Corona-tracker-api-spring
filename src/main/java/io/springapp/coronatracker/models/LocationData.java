package io.springapp.coronatracker.models;

public class LocationData {
    private String state;
    private String country;
    private int totalActiveCases;

    public int getDiffBwLastDay() {
        return diffBwLastDay;
    }

    public void setDiffBwLastDay(int diffBwLastDay) {
        this.diffBwLastDay = diffBwLastDay;
    }

    private int diffBwLastDay;
    public int getTotalActiveCases() {
        return totalActiveCases;
    }

    public void setTotalActiveCases(int totalActiveCases) {
        this.totalActiveCases = totalActiveCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalActiveCases=" + totalActiveCases +
                '}';
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
