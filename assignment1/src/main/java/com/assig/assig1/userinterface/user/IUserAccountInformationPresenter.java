package com.assig.assig1.userinterface.user;

public interface IUserAccountInformationPresenter {

    void saveAccountInformation(String firstName, String lastName, String icn, String address);

    void setAddress(String country, String city, String address, String number, String buildingNumber, String floor,
                    String doorNumber);
}
