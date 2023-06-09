package com.findhub.finhubbackend.util;

public class Config {
    /**
     * Path
     */
    public static class Path {
        private static final String API = "/api";
        public static final String ACCOUNT = API + "/accounts";
        public static final String APPLICATION = API + "/applications";

        public static final String ALL = "/all";
        public static final String DELETE = "/delete";
        public static final String RESTORE = "/restore";
        public static final String UPDATE = "/update/" + Var.ID + "={" + Var.ID + "}";
        public static final String ID = "/" + Var.ID + "={" + Var.ID + "}";

    }

    public static class Var {
        public static final String ID = "id";

    }
}