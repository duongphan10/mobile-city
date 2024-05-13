package com.vn.mobilecity.constant;

public class ErrorMessage {

    public static final String ERR_EXCEPTION_GENERAL = "exception.general";
    public static final String UNAUTHORIZED = "exception.unauthorized";
    public static final String FORBIDDEN = "exception.forbidden";
    public static final String FORBIDDEN_UPDATE_DELETE = "exception.forbidden.update-delete";
    public static final String ERR_EXCEPTION_MAX_UPLOAD_FILE_SIZE = "exception.max.upload.file.size";
    public static final String ERR_EXCEPTION_MULTIPART = "exception.multipart";
    public static final String ERR_EXCEPTION_INVALID_JSON_REQUEST = "exception.invalid.json.request";

    //error validation dto
    public static final String INVALID_SOME_THING_FIELD = "invalid.general";
    public static final String INVALID_FORMAT_SOME_THING_FIELD = "invalid.general.format";
    public static final String INVALID_SOME_THING_FIELD_IS_REQUIRED = "invalid.general.required";
    public static final String NOT_BLANK_FIELD = "invalid.general.not-blank";
    public static final String INVALID_NUMBER_POSITIVE = "invalid.number.positive";
    public static final String INVALID_FORMAT_USERNAME = "invalid.username-format";
    public static final String INVALID_FORMAT_PASSWORD = "invalid.password-format";
    public static final String INVALID_FORMAT_EMAIL = "invalid.email-format";
    public static final String INVALID_DATE = "invalid.date-format";
    public static final String INVALID_DATE_FEATURE = "invalid.date-future";
    public static final String INVALID_DATETIME = "invalid.datetime-format";

    public static class Auth {
        public static final String ERR_INCORRECT_USERNAME = "exception.auth.incorrect.username";
        public static final String ERR_INCORRECT_PASSWORD = "exception.auth.incorrect.password";
        public static final String ERR_ACCOUNT_NOT_ENABLED = "exception.auth.account.not.enabled";
        public static final String ERR_ACCOUNT_LOCKED = "exception.auth.account.locked";
        public static final String INVALID_REFRESH_TOKEN = "exception.auth.invalid.refresh.token";
        public static final String EXPIRED_REFRESH_TOKEN = "exception.auth.expired.refresh.token";
    }

    public static class User {
        public static final String ERR_NOT_FOUND_USERNAME = "exception.user.not.found.username";
        public static final String ERR_NOT_FOUND_ID = "exception.user.not.found.id";
        public static final String ERR_NOT_FOUND_WITH_EMAIL = "exception.user.not.found.with.email";
        public static final String ERR_ALREADY_EXIST_PHONE = "exception.user.already.exist.phone";
        public static final String ERR_ALREADY_EXIST_EMAIL = "exception.user.already.exist.email";
        public static final String ERR_ALREADY_EXIST_USERNAME = "exception.user.already.exist.username";
        public static final String ERR_CURRENT_PASSWORD_INCORRECT = "exception.user.current.password.incorrect";
        public static final String ERR_SAME_PASSWORD = "exception.user.same.password";
    }

    public static class Address {
        public static final String ERR_NOT_FOUND_ID = "exception.address.not.found.id";
        public static final String ERR_CANCEL_DEFAULT = "exception.address.cancel.default";
    }

    public static class Category {
        public static final String ERR_NOT_FOUND_ID = "exception.category.not.found.id";

    }

    public static class NewsType {
        public static final String ERR_NOT_FOUND_ID = "exception.news.type.not.found.id";
    }

    public static class News {
        public static final String ERR_NOT_FOUND_ID = "exception.news.not.found.id";
    }

    public static class Promotion {
        public static final String ERR_NOT_FOUND_ID = "exception.promotion.not.found.id";
    }

    public static class Product {
        public static final String ERR_NOT_FOUND_ID = "exception.product.not.found.id";
    }

    public static class ProductOption {
        public static final String ERR_NOT_FOUND_ID = "exception.product.option.not.found.id";
        public static final String ERR_OUT_OF_STOCK = "exception.product.option.out.of.stock";
    }

    public static class Cart {
        public static final String ERR_NOT_FOUND_ID = "exception.cart.not.found.id";
        public static final String ERR_QUANTITY_EXCEEDED = "exception.quantity.exceeded";
    }

    public static class Slide {
        public static final String ERR_NOT_FOUND_ID = "exception.slide.not.found.id";
        public static final String ERR_POSITION_ALREADY_EXIST = "exception.slide.position.already.exist";
    }

    public static class OrderStatus {
        public static final String ERR_NOT_FOUND_ID = "exception.order.status.not.found.id";
    }

    public static class PaymentType {
        public static final String ERR_NOT_FOUND_ID = "exception.payment.type.not.found.id";
    }

    public static class Order {
        public static final String ERR_NOT_FOUND_ID = "exception.order.not.found.id";
        public static final String ERR_INVALID_ADDRESS = "exception.order.invalid.address";
        public static final String ERR_INVALID_PRODUCT_OPTION = "exception.order.invalid.product.option";
        public static final String ERR_INVALID_STATUS_UPDATE = "exception.order.invalid.status";
    }

    public static class OrderDetail {
        public static final String ERR_NOT_FOUND_ID = "exception.order.detail.not.found.id";
    }

    public static class Review {
        public static final String ERR_NOT_FOUND_ID = "exception.review.not.found.id";
        public static final String ERR_ALREADY_EXIST_WITH_ORDER_DETAIL = "exception.review.already.exist.with.order.detail";
    }

    public static class Notification {
        public static final String ERR_NOT_FOUND_ID = "exception.notification.not.found.id";
    }

    public static class Message {
        public static final String ERR_NOT_FOUND_ID = "exception.message.not.found.id";
        public static final String ERR_NOT_FOUND_MESSAGE_OR_FILE = "exception.message.not.found.message.or.file";
    }

    public static class File {
        public static final String ERR_NOT_FOUND_ID = "exception.file.not.found.id";
    }

    public static class UserRoom {
        public static final String ERR_NOT_FOUND_ID = "exception.user.room.not.found.id";
        public static final String ERR_ALREADY_EXIST = "exception.user.room.already.exist";
    }

    public static class Room {
        public static final String ERR_NOT_FOUND_ID = "exception.room.not.found.id";
    }
}
