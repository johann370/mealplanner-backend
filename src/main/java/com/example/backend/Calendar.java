package com.example.backend;

import java.util.List;

public class Calendar {
    private Day monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    public Calendar(Day monday, Day tuesday, Day wednesday, Day thursday, Day friday, Day saturday, Day sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public Calendar(MealRepository repository) {
        this.monday = new Day(repository, "Monday");
        this.tuesday = new Day(repository, "Tuesday");
        this.wednesday = new Day(repository, "Wednesday");
        this.thursday= new Day(repository, "Thursday");
        this.friday = new Day(repository, "Friday");
        this.saturday = new Day(repository, "Saturday");
        this.sunday = new Day(repository, "Sunday");
    }

    public Day getMonday() {
        return monday;
    }

    public void setMonday(Day monday) {
        this.monday = monday;
    }

    public Day getTuesday() {
        return tuesday;
    }

    public void setTuesday(Day tuesday) {
        this.tuesday = tuesday;
    }

    public Day getWednesday() {
        return wednesday;
    }

    public void setWednesday(Day wednesday) {
        this.wednesday = wednesday;
    }

    public Day getThursday() {
        return thursday;
    }

    public void setThursday(Day thursday) {
        this.thursday = thursday;
    }

    public Day getFriday() {
        return friday;
    }

    public void setFriday(Day friday) {
        this.friday = friday;
    }

    public Day getSaturday() {
        return saturday;
    }

    public void setSaturday(Day saturday) {
        this.saturday = saturday;
    }

    public Day getSunday() {
        return sunday;
    }

    public void setSunday(Day sunday) {
        this.sunday = sunday;
    }
}
