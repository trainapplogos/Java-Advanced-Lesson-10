package ua.lviv.trainapplogos.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.trainapplogos.dao.UserAccountDao;
import ua.lviv.trainapplogos.dao.impl.UserAccountDaoImpl;
import ua.lviv.trainapplogos.domain.UserAccount;
import ua.lviv.trainapplogos.service.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {
	private static Logger LOGGER = Logger.getLogger(UserAccountServiceImpl.class);
//	private static UserServiceImpl userServiceImpl;
	private static UserAccountService userServiceImpl;
	private UserAccountDao userDao;
	
	private UserAccountServiceImpl() {	
		try {
			userDao = new UserAccountDaoImpl();
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}
	
	public static UserAccountService getUserService() {
		if (userServiceImpl == null) {
			userServiceImpl = new UserAccountServiceImpl();
		}
		return userServiceImpl;
	}

	@Override
	public UserAccount create(UserAccount user) {
		return userDao.create(user);
	}

	@Override
	public UserAccount read(Integer id) {
		return userDao.read(id);
	}

	@Override
	public UserAccount update(UserAccount user) {
		return userDao.update(user);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public List<UserAccount> readAll() {
		return userDao.readAll();
	}

	@Override
	public UserAccount getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
}
