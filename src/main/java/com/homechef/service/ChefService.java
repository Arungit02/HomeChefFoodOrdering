package com.homechef.service;

import com.homechef.dao.ChefDAO;
import com.homechef.entity.Chef;

public class ChefService {

    private ChefDAO chefDAO;

    public ChefService() {
        chefDAO = new ChefDAO();
    }

    // ==============================
    // ADD CHEF
    // ==============================

    public void addChef(Chef chef) {

        chefDAO.saveChef(chef);
    }

    // ==============================
    // GET CHEF BY ID
    // ==============================

    public Chef getChef(int id) {

        return chefDAO.getChef(id);
    }

    // ==============================
    // UPDATE CHEF
    // ==============================

    public void updateChef(Chef chef) {

        chefDAO.updateChef(chef);
    }

    // ==============================
    // DELETE CHEF
    // ==============================

    public void deleteChef(int id) {

        chefDAO.deleteChef(id);
    }
}
