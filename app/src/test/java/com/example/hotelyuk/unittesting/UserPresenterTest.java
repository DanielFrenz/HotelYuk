package com.example.hotelyuk.unittesting;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserPresenterTest {
    @Mock
    private UserView view;
    @Mock
    private UserService service;
    private UserPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new UserPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        System.out.println("Testing Pertama : Inputan Email Kosong");

        when(view.getEmail()).thenReturn("");
        System.out.println("Email : " + view.getEmail());

        presenter.onRegisterClicked();
        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailFormatInvalid() throws Exception {
        System.out.println("\n\n" + "Testing Kedua : Format Inputan Email Salah");

        when(view.getEmail()).thenReturn("testgmailcom");
        System.out.println("Email : " + view.getEmail());

        presenter.onRegisterClicked();
        verify(view).showEmailError("Format email salah");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Ketiga : Inputan Password Kosong");

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("");
        System.out.println("Password : " + view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordFormatInvalid() throws Exception {
        System.out.println("\n\n" + "Testing Keempat : Format Inputan Password Salah");

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("Tes_123");
        System.out.println("Password : " + view.getPassword());

        presenter.onRegisterClicked();
        verify(view).showPasswordError("Format password salah");
    }

    @Test
    public void shouldShowErrorMessageWhenPhoneNumberIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Kelima : Inputan Nomor Telepon Kosong");

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("Tes123");
        System.out.println("Password : " + view.getPassword());

        when(view.getPhoneNumber()).thenReturn("");
        System.out.println("Nomor Telepon : " + view.getPhoneNumber());

        presenter.onRegisterClicked();
        verify(view).showPhoneNumberError("Nomor telepon tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPhoneNumberLengthMore13() throws Exception {
        System.out.println("\n\n" + "Testing Keenam : Inputan Nomor Telepon lebih dari 13 digit");

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("Tes123");
        System.out.println("Password : " + view.getPassword());

        when(view.getPhoneNumber()).thenReturn("08121234567890");
        System.out.println("Nomor Telepon : " + view.getPhoneNumber());

        presenter.onRegisterClicked();
        verify(view).showPhoneNumberError("Nomor telepon tidak boleh lebih dari 13 digit");
    }

    @Test
    public void shouldShowErrorMessageWhenAccountNameIsEmpty() throws Exception {
        System.out.println("\n\n" + "Testing Ketujuh : Inputan Nama Akun Kosong");

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("Tes123");
        System.out.println("Password : " + view.getPassword());

        when(view.getPhoneNumber()).thenReturn("0812123456789");
        System.out.println("Nomor Telepon : " + view.getPhoneNumber());

        when(view.getAccountName()).thenReturn("");
        System.out.println("Nama Akun : " + view.getAccountName());

        presenter.onRegisterClicked();
        verify(view).showAccountNameError("Nama akun tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenAccountNameFormatInvalid() throws Exception {
        System.out.println("\n\n" + "Testing Kedelapan : Format Inputan Nama Akun Salah");

        when(view.getEmail()).thenReturn("test@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("Tes123");
        System.out.println("Password : " + view.getPassword());

        when(view.getPhoneNumber()).thenReturn("0812123456789");
        System.out.println("Nomor Telepon : " + view.getPhoneNumber());

        when(view.getAccountName()).thenReturn("Dani3l Fu");
        System.out.println("Nama Akun : " + view.getAccountName());

        presenter.onRegisterClicked();
        verify(view).showAccountNameError("Format nama akun salah");
    }
}