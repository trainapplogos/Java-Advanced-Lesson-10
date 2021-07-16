package ua.lviv.trainapplogos.dao;

import ua.lviv.trainapplogos.domain.UserAccount;
import ua.lviv.trainapplogos.shared.AbstractCRUD;

public interface UserAccountDao extends AbstractCRUD<UserAccount> {
	UserAccount getUserByEmail(String email);
}
