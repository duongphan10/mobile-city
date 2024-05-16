package com.vn.mobilecity.constant;

public class UrlConstant {

    public static class Auth {
        private static final String PRE_FIX = "/auth";
        public static final String LOGIN = PRE_FIX + "/login";
        public static final String LOGOUT = PRE_FIX + "/logout";
        public static final String SEND_VERIFY = PRE_FIX + "/send";
        public static final String VERIFY = PRE_FIX + "/verify";
    }

    public static class User {
        private static final String PRE_FIX = "/user";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_CURRENT_USER = PRE_FIX + "/my";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX;
        public static final String CHANGE_PASSWORD = PRE_FIX + "/change-password";
        public static final String CREATE_NEW_PASSWORD = PRE_FIX + "/new-password";
        public static final String DELETE = PRE_FIX;
        public static final String DELETE_BY_ID = PRE_FIX + "/{id}";
    }

    public static class Address {
        private static final String PRE_FIX = "/address";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String GET_DEFAULT = PRE_FIX + "/default";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE_BY_ID = PRE_FIX + "/{id}";
        public static final String CHANGE_DEFAULT = PRE_FIX + "/default/{id}";
        public static final String DELETE_BY_ID = PRE_FIX + "/{id}";
    }

    public static class Category {
        private static final String PRE_FIX = "/category";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class NewsType {
        private static final String PRE_FIX = "/news-type";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class News {
        private static final String PRE_FIX = "/news";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_BY_USER = PRE_FIX;
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class Promotion {
        private static final String PRE_FIX = "/promotion";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class Product {
        private static final String PRE_FIX = "/product";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String SEARCH = PRE_FIX + "/search";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class ProductOption {
        private static final String PRE_FIX = "/product-option";
        public static final String GET_OPTION_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL_OPTION = PRE_FIX + "/all";
        public static final String SEARCH = PRE_FIX + "/search";
        public static final String CREATE_OPTION = PRE_FIX;
        public static final String UPDATE_OPTION = PRE_FIX + "/{id}";
        public static final String DELETE_OPTION = PRE_FIX + "/{id}";
    }

    public static class Cart {
        private static final String PRE_FIX = "/cart";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String COUNT_ITEM = PRE_FIX + "/items";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class Slide {
        private static final String PRE_FIX = "/slide";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_BY_USER = PRE_FIX;
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class OrderStatus {
        private static final String PRE_FIX = "/order-status";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
    }

    public static class PaymentType {
        private static final String PRE_FIX = "/payment-type";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
    }

    public static class Order {
        private static final String PRE_FIX = "/order";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String GET_ALL_BY_USER = PRE_FIX + "/my";
        public static final String CREATE = PRE_FIX;

        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class OrderDetail {
        private static final String PRE_FIX = "/order-detail";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
    }

    public static class Review {
        private static final String PRE_FIX = "/review";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String GET_STAR = PRE_FIX + "/star";
        public static final String CREATE = PRE_FIX;

        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

    public static class Notification {
        private static final String PRE_FIX = "/notification";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;

        public static final String UPDATE_BY_ID = PRE_FIX + "/{id}";
        public static final String DELETE_BY_ID = PRE_FIX + "/{id}";
    }

    public static class File {
        private static final String PRE_FIX = "/file";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";
    }

}
