package com.example.hotelyuk.unittesting;

public interface UserView {
    String getEmail();
    void showEmailError(String message);

    String getPassword();
    void showPasswordError(String message);

    String getPhoneNumber();
    void showPhoneNumberError(String message);

    String getAccountName();
    void showAccountNameError(String message);

    void startMainRegister();

    void showRegisterError(String message);
    void showErrorResponse(String message);
}
