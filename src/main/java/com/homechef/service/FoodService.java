
package com.homechef.service;

import java.util.List;

import com.homechef.dao.FoodDAO;
import com.homechef.entity.Food;

public class FoodService {

    private FoodDAO foodDAO;

    public FoodService() {
        foodDAO = new FoodDAO();
    }

    // ==============================
    // ADD FOOD
    // ==============================

    public void addFood(Food food) {

        foodDAO.saveFood(food);
    }

    // ==============================
    // GET FOOD BY ID
    // ==============================

    public Food getFood(int id) {

        return foodDAO.getFood(id);
    }

    // ==============================
    // GET ALL FOOD
    // ==============================

    public List<Food> getAllFoods() {

        return foodDAO.getAllFoods();
    }

    // ==============================
    // UPDATE FOOD
    // ==============================

    public void updateFood(Food food) {

        foodDAO.updateFood(food);
    }

    // ==============================
    // DELETE FOOD
    // ==============================

    public void deleteFood(int id) {

        foodDAO.deleteFood(id);
    }
}
