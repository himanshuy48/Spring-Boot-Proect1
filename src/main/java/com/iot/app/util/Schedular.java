package com.iot.app.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iot.app.dao.BlackListTokenDao;
import com.iot.app.entity.BlackListedTokens;

@Component
public class Schedular {
	
	
	private static final Logger log = LoggerFactory.getLogger(Schedular.class);

	@Autowired
	BlackListTokenDao blackListTokenDao;

	@Scheduled(cron = "0 * */1 ? * *")
	void deleteBlackListedTokens() {
		List<BlackListedTokens> tokensList = blackListTokenDao.findAll();

		tokensList.parallelStream().forEachOrdered(token -> {
			if (token.getTokenExpireAt().before(new Date())) {
				blackListTokenDao.deleteById(token.getId());
				log.debug("deleted id: " + token.getId() +
				", expiration time: " + token.getTokenExpireAt());
			}
		});
	}

}
