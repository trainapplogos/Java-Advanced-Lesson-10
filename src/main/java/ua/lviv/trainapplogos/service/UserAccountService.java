package ua.lviv.trainapplogos.service;

import ua.lviv.trainapplogos.domain.UserAccount;
import ua.lviv.trainapplogos.shared.AbstractCRUD;

public interface UserAccountService extends AbstractCRUD<UserAccount> {
	UserAccount getUserByEmail(String email);
}
