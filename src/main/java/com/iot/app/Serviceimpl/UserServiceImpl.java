package com.iot.app.Serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iot.app.Service.UserService;
import com.iot.app.config.security.JwtTokenUtil;
import com.iot.app.config.security.JwtUserDetailsService;
import com.iot.app.dao.BlackListTokenDao;
import com.iot.app.dao.UserDao;
import com.iot.app.dto.DataResponseDto;
import com.iot.app.dto.SignUpDto;
import com.iot.app.dto.SocialsignUpDto;
import com.iot.app.dto.UserDetailsDto;
import com.iot.app.entity.BlackListedTokens;
import com.iot.app.entity.User;
import com.iot.app.enums.StatusEnum;
import com.iot.app.model.Response;
import com.iot.app.util.EmailService;
import com.iot.app.util.RandomCodeGenerator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	BlackListTokenDao blackListTokenDao;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	PasswordEncoder bcryptEncoder;

	@Autowired
	EmailService emailService;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public Response<String> updateMobileTokenOnLogin(String mobileDeviceToken, String emialId) {
		Optional<User> findByEmailAddress = userDao.findByEmailAddress(emialId);
		if (findByEmailAddress.isPresent()
				&& !findByEmailAddress.get().getMobileDeviceToken().equals(mobileDeviceToken)) {
			findByEmailAddress.get().setMobileDeviceToken(mobileDeviceToken);
			findByEmailAddress.get().setUpdatedAt(LocalDateTime.now());
			return new Response<>(200, "mobile device token updated");
		}
		return new Response<>(200, "mobile device token same");
	}

	@Override
	public Response<String> registerUser(@Valid SignUpDto signUpDto) throws Exception {
		User user = new User();
		user.setEmailAddress(signUpDto.getEmailAddress());
		user.setFirstName(signUpDto.getFirstName());
		user.setLastName(signUpDto.getLastName());
		user.setPassword(bcryptEncoder.encode(signUpDto.getPassword()));
		user.setMobileDeviceToken(signUpDto.getMobileDeviceToken());
		user.setCreatedAt(LocalDateTime.now());
		user.setCreatedBy(!signUpDto.getFirstName().isEmpty() ? signUpDto.getFirstName() : signUpDto.getEmailAddress());
		user.setUpdatedAt(LocalDateTime.now());
		user.setUpdatedBy(!signUpDto.getFirstName().isEmpty() ? signUpDto.getFirstName() : signUpDto.getEmailAddress());

		boolean userExist = userDao.existsByEmailAddress(user.getEmailAddress());
		if (!userExist) {
			User saved = userDao.save(user);
			Response<String> sendVerificationCodeToMail = sendVerificationCodeToMail(signUpDto.getEmailAddress());
			if (sendVerificationCodeToMail.getStatusCode() == 200)
			{
				DataResponseDto signedUpData=new DataResponseDto();
				modelMapper.map(saved, signedUpData);
				return new Response(200, "signup-Success and verification code sent to: " + saved.getEmailAddress(),signedUpData);
			}
			else if (sendVerificationCodeToMail.getStatusCode() == 201) {
				return new Response<>(201, "signup failed, email does not exist in mail box ");
			}
		}
		return new Response<>(201, "signup failed, email: " + user.getEmailAddress() + " already exist");

	}

	@Override
	public Response<String> sendVerificationCodeToMail(String emailId) throws Exception {
		final String verificationToken = RandomCodeGenerator.verificationCodeGenerator();

		User findByEmailAddress = userDao.findByEmailAddress(emailId).get();

		if (findByEmailAddress != null) {
			if (!findByEmailAddress.getStatus().equals(StatusEnum.EMAIL_VERIFIED)) {
				emailService.sendEmail(findByEmailAddress.getEmailAddress(), verificationToken);

				findByEmailAddress.setTempVerificationCode(verificationToken);
				userDao.save(findByEmailAddress);
				return new Response<>(200, "mail Sent successfully to: " + emailId);
			}
			return new Response<>(201, "mail not Sent to: " + emailId);
		} else
			return new Response<>(201, "emailId not exist: " + emailId);

	}

	@Override
	public Response<String> sendVerificationCodeToChangePassword(String emailId) {
		String verificationToken = null;
		try {
			verificationToken = RandomCodeGenerator.verificationCodeGenerator();
		} catch (Exception e) {
			e.printStackTrace();
		}
		User findByEmailAddress = userDao.findByEmailAddress(emailId).get();
		if (findByEmailAddress != null) {
			emailService.sendEmail(findByEmailAddress.getEmailAddress(), verificationToken);
			findByEmailAddress.setTempVerificationCode(verificationToken);
			userDao.save(findByEmailAddress);
			return new Response<>(200, "mail Sent successfully to: " + emailId);
		} else {
			return new Response<>(201, "emailId not exist: " + emailId);
		}
	}

	@Override
	@Transactional
	public Response<Map<String, Object>> verifyCode(String emailId, String verificationCode) {
		User findByEmailAddress = userDao.findByEmailAddress(emailId).get();
		if (findByEmailAddress != null) {
			if (!findByEmailAddress.getStatus().equals(StatusEnum.EMAIL_VERIFIED)) {
				if (findByEmailAddress.getTempVerificationCode().equals(verificationCode)) {
					findByEmailAddress.setStatus(StatusEnum.EMAIL_VERIFIED);
					findByEmailAddress.setUpdatedAt(LocalDateTime.now());
					findByEmailAddress.setTempVerificationCode(null);
					UserDetailsDto userDetailsDto=new UserDetailsDto();
					userDetailsDto.setEmailAddress(findByEmailAddress.getEmailAddress());
					userDetailsDto.setFirstName(findByEmailAddress.getFirstName());
					userDetailsDto.setId(findByEmailAddress.getId());
					DataResponseDto dataResponseDto=new DataResponseDto();
					modelMapper.map(findByEmailAddress, dataResponseDto);
					
					String jwtToken = jwtTokenUtil.generateToken(userDetailsDto);
					
					
					Map<String, Object> map=new HashMap<>();
					map.put("token", jwtToken);
					map.put("dataResponseDto", dataResponseDto);
					return new Response(200, "emailId: " + emailId + " verified successfully",map);
				} else {
					return new Response<>(201,
							"emailId: " + emailId + " verification failed >>> verification code mismatch");
				}
			} else
			{
				return new Response<>(201,
						" you have allready done email verification process your email: " + emailId + " is verified");
			}
		} else
		{
			return new Response<>(201, "emailId: " + emailId + " not exist in database");
		}
	}

	@Override
	public Response<String> changePassword(String emailId, String newPassword, String jwtToken) {

		try {

			User findByEmailAddress = userDao.findByEmailAddress(emailId).get();
			if (findByEmailAddress.getTempVerificationCode().equals(jwtToken)) {
				findByEmailAddress.setPassword(bcryptEncoder.encode(newPassword));
				findByEmailAddress.setUpdatedAt(LocalDateTime.now());
				findByEmailAddress.setTempVerificationCode(null);
				userDao.save(findByEmailAddress);
				blackListTokenDao.save(new BlackListedTokens(jwtToken, LocalDateTime.now(),
						jwtTokenUtil.getExpirationDateFromToken(jwtToken)));

				return new Response<>(200, "pasword changed successfully");
			} else {
				return new Response<>(401, "UnAuthorise");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(500, "something bad happen");
		}
	}

	/* /auth/logout controller written in jwt auth controller */

	@Override
	public Response<String> logOutFromBackend(String token) {
		if (!token.isEmpty()) {
			Date expirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(token);
			if (expirationDateFromToken != null) {

				BlackListedTokens blackListToken = new BlackListedTokens(token, LocalDateTime.now(),
						expirationDateFromToken);
				blackListTokenDao.save(blackListToken);
				return new Response<>(200, "logout successfull");
			} else
				return new Response<>(401, "wrong token ");
		} else
			return new Response<>(201, "logout failed ");
	}

	/* /auth/forget password controller written in jwt auth controller */

	@Override
	@Transactional
	public Response<String> verifyCodeAndGenerateToken(String emailId, String verificationCode) {
		User findByEmailAddress = userDao.findByEmailAddress(emailId).get();
		if (findByEmailAddress != null) {
			if (findByEmailAddress.getTempVerificationCode().equals(verificationCode)) {
				findByEmailAddress.setUpdatedAt(LocalDateTime.now());
				final UserDetailsDto userDetails = userDetailsService
						.loadUserByEmail(findByEmailAddress.getEmailAddress());
				final String jwtToken = jwtTokenUtil.generateToken(userDetails);
				if (findByEmailAddress.getStatus().equals(StatusEnum.EMAIL_UN_VERIFIED)) {
					findByEmailAddress.setStatus(StatusEnum.EMAIL_VERIFIED);
					findByEmailAddress.setUpdatedAt(LocalDateTime.now());
					findByEmailAddress.setTempVerificationCode(jwtToken);
					return new Response<>(200, "verification success and your email also verified ", jwtToken);
				}
				findByEmailAddress.setTempVerificationCode(jwtToken);
				return new Response<>(200, "verification success ", jwtToken);

			} else {
				return new Response<>(201,
						"emailId: " + emailId + " verification failed >>> verification code mismatch");
			}
		} else {
			return new Response<>(201, "emailId: " + emailId + " not exist in database");
		}
	}

	@Override
	public Response<String> socialSignup(@Valid SocialsignUpDto socialsignUpDto) {

		boolean userExist = userDao.existsByEmailAddress(socialsignUpDto.getEmailAddress());

		if (!userExist) {

			User user = new User();
			user.setEmailAddress(socialsignUpDto.getEmailAddress());
			user.setFirstName(socialsignUpDto.getFirstName());
			user.setLastName(socialsignUpDto.getLastName());
			user.setMobileDeviceToken(socialsignUpDto.getMobileDeviceToken());
			user.setSocialId(socialsignUpDto.getSocialId());
			user.setSocialType(socialsignUpDto.getSocialType());
			user.setCreatedAt(LocalDateTime.now());
			user.setCreatedBy(!socialsignUpDto.getFirstName().isEmpty() ? socialsignUpDto.getFirstName()
					: socialsignUpDto.getEmailAddress());
			user.setUpdatedAt(LocalDateTime.now());
			user.setUpdatedBy(!socialsignUpDto.getFirstName().isEmpty() ? socialsignUpDto.getFirstName()
					: socialsignUpDto.getEmailAddress());
			userDao.save(user);
			return new Response<>(200, "signup-Successfull");

		}
		return new Response<>(201, "signup failed, email: " + socialsignUpDto.getEmailAddress() + " already exist");

	}

}
