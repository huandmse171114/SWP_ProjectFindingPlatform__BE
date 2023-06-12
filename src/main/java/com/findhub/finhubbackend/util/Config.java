package com.findhub.finhubbackend.util;

public class Config {
    /**
     * Path
     */
    public static class ApiPath {
        private static final String API = "/api";
        public static final String ACCOUNT = API + "/accounts";
        public static final String APPLICATION = API + "/applications";
        public static final String CATEGORY = API + "/categories";
        public static final String SKILL = API + "/skills";
        public static final String MAJOR = API + "/majors";
        public static final String MEMBER = API + "/members";
        public static final String PROJECT = API + "/projects";
        public static final String TEAM = API + "/teams";
        public static final String PAYMENT = API + "/payments";
        public static final String DELIVERABLE_TYPE = API + "/deliverable-type";

        public static final String ALL = "/all";
        public static final String ADD = "/add";
        public static final String DELETE = "/delete";
        public static final String ENABLE = "/enable";
        public static final String DISABLE = "/disable";
        public static final String UPDATE = "/update/" + Var.ID + "={" + Var.ID + "}";
        public static final String CHANGE_STATUS = "/change-status/" + Var.ID + "={" + Var.ID + "}";
        public static final String ID = "/{" + Var.ID + "}";

    }

    public static class Var {
        public static final String ID = "id";

    }
}