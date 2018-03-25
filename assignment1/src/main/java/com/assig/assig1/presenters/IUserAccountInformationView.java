package com.assig.assig1.presenters;

import com.assig.assig1.userinterface.user.IUserAccountInformationPresenter;

public interface IUserAccountInformationView {

    void show();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setICN(String icn);

    void setAddress(String country, String city, String address, String number, String buildingNumber, String floor,
                    String doorNumber);

    void setPresenter(IUserAccountInformationPresenter p);

    void dontDisplay();
}
