package com.alexbonetskiy.restaurantvoting.model;



public enum Role  {
    USER,
    ADMIN;

    //    https://stackoverflow.com/a/19542316/548473
    public String getAuthority() {
        return "ROLE_" + name();
    }
}