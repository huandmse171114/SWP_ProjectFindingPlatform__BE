package com.findhub.finhubbackend.util;

public class Config {

    public static class EntityPath {
        public static final String TEAM = "/teams";
        public static final String SKILL = "/skills";
        public static final String MAJOR = "/majors";
        public static final String SEARCH = "/search";
        public static final String MEMBER = "/members";
        public static final String ACCOUNT = "/accounts";
        public static final String PAYMENT = "/payments";
        public static final String PROJECT = "/projects";
        public static final String CATEGORY = "/categories";
        public static final String PUBLISHER = "/publishers";
        public static final String APPLICATION = "/applications";
        public static final String DELIVERABLE_TYPE = "/deliverable-types";

    }

    public static class Var {
        public static final String ID = "id";
        public static final String IMG = "image";
        public static final String KEYWORD = "keyword";

    }

    public static class SubPath {
        public static final String ID       = "/{" + Var.ID + "}";
        public static final String IMG      = "/{" + Var.IMG + "}";
        public static final String KEYWORD  = "/{" + Var.KEYWORD + "}";

        public static final String ALL                  = "/all";
        public static final String ACTIVE               = "/active";
        public static final String INACTIVE             = "/inactive";

        public static final String STATUS_ID            = "/status" + ID;
        public static final String STATUS_KEYWORD       = "/status" + KEYWORD;
        public static final String STATUS_ALL           = "/status" + ALL;

        public static final String SEARCH_MEMBERS       = EntityPath.MEMBER + KEYWORD;
        public static final String SEARCH_ACCOUNTS      = EntityPath.ACCOUNT + KEYWORD;
        public static final String SEARCH_PUBLISHERS    = EntityPath.PUBLISHER + KEYWORD;
        public static final String SEARCH_PROJECTS      = EntityPath.PROJECT + KEYWORD;
        public static final String SEARCH_TEAMS         = EntityPath.TEAM + KEYWORD;
        public static final String SEARCH_SKILLS        = EntityPath.SKILL + KEYWORD;
        public static final String SEARCH_MAJORS        = EntityPath.MAJOR + KEYWORD;
        public static final String SEARCH_CATEGORIES    = EntityPath.CATEGORY + KEYWORD;
    }

    /**
     * Path
     */
    public static class ApiPath {
        private static final String API             = "/api";
        public static final String ACCOUNT          = API + EntityPath.ACCOUNT;
        public static final String PUBLISHER        = API + EntityPath.PUBLISHER;
        public static final String APPLICATION      = API + EntityPath.APPLICATION;
        public static final String CATEGORY         = API + EntityPath.CATEGORY;
        public static final String SKILL            = API + EntityPath.SKILL;
        public static final String MAJOR            = API + EntityPath.MAJOR;
        public static final String MEMBER           = API + EntityPath.MEMBER;
        public static final String PROJECT          = API + EntityPath.PROJECT;
        public static final String TEAM             = API + EntityPath.TEAM;
        public static final String PAYMENT          = API + EntityPath.PAYMENT;
        public static final String DELIVERABLE_TYPE = API + EntityPath.DELIVERABLE_TYPE;
        public static final String SEARCH           = API + EntityPath.SEARCH;

        private static final String FB_MEDIA = "/media";
        public static final String FB_PROJECT = FB_MEDIA + EntityPath.PROJECT + SubPath.IMG;

    }
}
