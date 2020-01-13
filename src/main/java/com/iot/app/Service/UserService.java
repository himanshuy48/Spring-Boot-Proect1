package com.iot.app.Service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.iot.app.dto.LoginDto;
import com.iot.app.dto.SignUpDto;
import com.iot.app.dto.SocialsignUpDto;
import com.iot.app.dto.UserDetailsDto;
import com.iot.app.entity.User;
import com.iot.app.model.Response;

public interface UserService {

	Response<String> registerUser(@Valid SignUpDto user) throws Exception;

	Response<String> sendVerificationCodeToMail(String emailId) throws Exception;

	Response<Map<String, Object>> verifyCode(String emailId, String verificationCode);

	Response<?> changePassword(String emailId, String newPassword, String jwtToken);

	Response<String> logOutFromBackend(String token);

	Response<String> verifyCodeAndGenerateToken(String emailId, String verificationCode);

	Response<String> sendVerificationCodeToChangePassword(String emailId);

	Response<String> socialSignup(@Valid SocialsignUpDto socialsignUpDto);

	Response<String> updateMobileTokenOnLogin(String mobileDeviceToken, String emailId);

	


}
