package com.epam.cinema.spring.controlers;

public class Pages {
    public static final String MAIN_PAGE = "/main";
    public static final String SCREENING_PAGE = "/screening";

    public static class User {
        public static final String USER_PAGE = "/user/user";
    }

    public static class Guest {
        public static final String LOGIN_PAGE = "/guest/sign-in";
        public static final String REGISTRATION_PAGE = "/guest/sign-up";
    }

    public static class Admin {
        public static final String ADMIN_PAGE = "/admin/admin";
        public static final String ADD_MOVIE_PAGE = "/admin/addMovie";
        public static final String AUDITORIUM_PAGE = "/admin/auditorium";
        public static final String MOVIES_PAGE = "/admin/movies";
        public static final String SCHEDULE_PAGE = "/admin/updateSchedule";
        public static final String UPDATE_MOVIE_PAGE = "/admin/updateMovie";
        public static final String STATISTIC = "/admin/statistic";
    }
}
