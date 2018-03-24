package com.assig.assig1;

import com.assig.assig1.userinterface.user.IUserAccountInformationPresenter;

public interface IUserAccountInformationView {

	void show();

	void setFirstName(String firstName);

	void setLastName(String lastName);

	void setICN(String icn);

	void setAddress(String address);
	
	void setPresenter(IUserAccountInformationPresenter p);
}
