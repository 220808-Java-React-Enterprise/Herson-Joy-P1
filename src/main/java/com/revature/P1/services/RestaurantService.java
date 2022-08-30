package com.revature.P1.services;

import com.revature.P1.daos.RestaurantDAO;
import com.revature.P1.models.Restaurant;

import java.util.List;

public class RestaurantService {
    private final RestaurantDAO restoDAO;

    public RestaurantService(RestaurantDAO restoDAO) {
        this.restoDAO = restoDAO;
    }

    public List<Restaurant> getAllRestaurants() {
        return restoDAO.getAll();
    }
}
