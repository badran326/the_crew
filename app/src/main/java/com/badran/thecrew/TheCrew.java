package com.badran.thecrew;

public class TheCrew {
    public String otherText;
    public boolean coffeeMate;
    String name;
    boolean nescafe;
    boolean coffee;
    boolean canderel;
    boolean sugar;
    boolean nestle;
    boolean other;
    boolean black;

    public TheCrew(String name,String otherText) {
        this.name = name;
        this.otherText = otherText;
    }

    public TheCrew(String name, boolean nescafe, boolean coffee, boolean canderel, boolean sugar, boolean nestle, boolean other, boolean black, boolean coffeeMate) {
        this.name = name;
        this.nescafe = nescafe;
        this.coffee = coffee;
        this.canderel = canderel;
        this.sugar = sugar;
        this.nestle = nestle;
        this.other = other;
        this.black = black;
        this.coffeeMate = coffeeMate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNescafe() {
        return nescafe;
    }

    public void setNescafe(boolean nescafe) {
        this.nescafe = nescafe;
    }

    public boolean isCoffee() {
        return coffee;
    }

    public void setCoffee(boolean coffee) {
        this.coffee = coffee;
    }

    public boolean isCanderel() {
        return canderel;
    }

    public void setCanderel(boolean canderel) {
        this.canderel = canderel;
    }

    public boolean isSugar() {
        return sugar;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    public boolean isNestle() {
        return nestle;
    }

    public void setNestle(boolean nestle) {
        this.nestle = nestle;
    }

    public String getOtherText() {
        return otherText;
    }

    public void setOtherText(String otherText) {
        this.otherText = otherText;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public boolean isCoffeeMate() {
        return coffeeMate;
    }

    public void setCoffeeMate(boolean coffeeMate) {
        this.coffeeMate = coffeeMate;
    }
}
