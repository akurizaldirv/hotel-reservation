package com.enigma.hotelreservation.constant;

public class ResponseMessage {
    public static final String GET_DATA_SUCCESS = "Get Data Successful";
    public static final String CANCEL_SUCCESS = "Cancel Reservation Success";
    public static final String CREATE_DATA_SUCCESS = "Create Data Successful";
    public static final String UPDATE_DATA_SUCCESS = "Update Data Successful";
    public static final String DELETE_DATA_SUCCESS = "Delete Data Successful";


    public static final String APPROVE_SUCCESS = "Approve Payment Success";
    public static final String REJECT_SUCCESS = "Reject Payment Success";
    public static final String PAYMENT_SUCCESS = "Payment Success, Waiting for Admin Approval";

    public static final String GET_DATA_FAILED = "Get Data Failed";
    public static final String DATA_NOT_FOUND = "Data Not Found";
    public static final String CREATE_DATA_FAILED = "Create Data Failed";
    public static final String UPDATE_DATA_FAILED = "Update Data Failed";
    public static final String DELETE_DATA_FAILED = "Delete Data Failed";
    public static final String REGISTER_FAILED = "Register Failed";
    public static final String REGISTER_SUCCESS = "Register Success";
    public static final String LOGIN_FAILED = "Login Failed";
    public static final String LOGIN_SUCCESS = "Login Success";

    public static final String USERNAME_TAKEN = "Username has been Taken";
    public static final String DUPLICATE_ROOM_ID_AND_NUMBER = "Duplicate Room ID and Room Number";
    public static final String INVALID_RSVP_DATE = "Invalid Reservation Date";
    public static final String EMPTY_FILE = "Cannot Upload Empty File";
    public static final String INVALID_PAY_RSVP = "Cannot Pay this Reservation ID";
    public static final String INVALID_APPROVE_PAYMENT = "Cannot Approve this Payment ID";
    public static final String INVALID_REJECT_PAYMENT = "Cannot Reject this Payment ID";
    public static final String OVERDUE_PAYMENT = "Overdue Payment, Reservation Cancelled";

    public static final String CUSTOMER_NOT_FOUND = "Customer Not Found";
    public static final String ADMIN_NOT_FOUND = "Admin Not Found";
    public static final String FACILITY_NOT_FOUND = "Facility Not Found";
    public static final String PAYMENT_NOT_FOUND = "PaymentNot Found";
    public static final String RESERVATION_NOT_FOUND = "Reservation Not Found";
    public static final String ROLE_NOT_FOUND = "Role Not Found";
    public static final String ROOM_NOT_FOUND = "Room Not Found";
    public static final String ROOM_TYPE_NOT_FOUND = "Room Type Not Found";
    public static final String USER_NOT_FOUND = "User Not Found";
}
