package com.example.hotelyuk.unittesting;

import com.example.hotelyuk.entity.User;

public class UserPresenter {
    private UserView view;
    private UserService service;
    private UserCallback callback;
    private User user;

    public UserPresenter(UserView view, UserService service) {
        this.view = view;
        this.service = service;
    }

    public void onRegisterClicked() {
        String regexEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String regexPassword = "^[A-Za-z0-9]+$";
        String regexName = "[A-Za-z]+\\s+[A-Za-z]+";

        if (view.getEmail().isEmpty()) {
            view.showEmailError("Email tidak boleh kosong");
            return;
        } else if(!(view.getEmail().matches(regexEmail))){
            view.showEmailError("Format email salah");
            return;
        } else if (view.getPassword().isEmpty()) {
            view.showPasswordError("Password tidak boleh kosong");
            return;
        } else if(!(view.getPassword().matches(regexPassword))){
            view.showPasswordError("Format password salah");
            return;
        } else if (view.getPhoneNumber().isEmpty()) {
            view.showPhoneNumberError("Nomor telepon tidak boleh kosong");
            return;
        } else if(view.getPhoneNumber().length() > 13) {
            view.showPhoneNumberError("Nomor telepon tidak boleh lebih dari 13 digit");
            return;
        }  else if (view.getAccountName().isEmpty()) {
            view.showAccountNameError("Nama akun tidak boleh kosong");
            return;
        } else if(!(view.getAccountName().matches(regexName))){
            view.showAccountNameError("Format nama akun salah");
            return;
        } else {
            service.register(view, user, new UserCallback() {
                @Override
                public void onSuccess(boolean value, User user)
                {
                    view.startMainRegister();
                }

                @Override
                public void onError() {

                }
            });
            return;
        }
    }
}
