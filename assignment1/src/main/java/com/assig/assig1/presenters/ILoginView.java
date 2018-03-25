package com.assig.assig1.presenters;

import com.assig.assig1.userinterface.user.ILoginPresenter;

public interface ILoginView {
    void showInvalidPassword();

    void showInvalidUsername();

    void display();

    void setPresenter(ILoginPresenter loginPresenter);

    void showInvalidCredentials();

    void dontDisplay();
}
