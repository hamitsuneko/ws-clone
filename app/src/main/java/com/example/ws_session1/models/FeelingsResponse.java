package com.example.ws_session1.models;

import java.util.ArrayList;

public class FeelingsResponse {
    private boolean success;
    private ArrayList<FeelingModel> data;

    public static class FeelingModel {
        private int id;
        private String title;
        private String image;
        private String description;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getDescription() {
            return description;
        }
    }

    public boolean getSuccess() {
        return success;
    }

    public ArrayList<FeelingModel> getData() {
        return data;
    }
}
