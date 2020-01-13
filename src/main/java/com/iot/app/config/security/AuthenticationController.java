package com.iot.app.config.security;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iot.app.Service.UserService;
import com.iot.app.dto.DataResponseDto;
import com.iot.app.dto.LoginDto;
import com.iot.app.dto.UserDetailsDto;
import com.iot.app.model.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	UserService userService;

	ModelMapper modelMapper = new ModelMapper();

	@ApiOperation(value = "api to login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "login successfull"),
			@ApiResponse(code = 201, message = "bad credentials-email password miss match"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(value = "/login")
	public Response<String> createAuthenticationToken(@RequestBody @Valid LoginDto authenticationRequest,
			HttpServletResponse httpResponse) throws Exception {

		authenticate(authenticationRequest.getEmailAddress(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmailAddress());

		UserDetailsDto userDetailsDto = userDetailsService.loadUserByEmail(userDetails.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetailsDto);

		if (userDetailsDto != null) {
			DataResponseDto loginResponse = new DataResponseDto();
			modelMapper.map(userDetailsDto, loginResponse);
			httpResponse.addHeader("authorization", token);
			Response<String> response = userService.updateMobileTokenOnLogin(
					authenticationRequest.getMobileDeviceToken(), authenticationRequest.getEmailAddress());
			if (response.getStatusCode() == 200) {
				return new Response(200, "login Successfull", loginResponse);
			} else {
				return new Response(201, "mobileDeviceToken not present");
			}
		}
		return new Response(201, "login failed");
	}

	@ApiOperation(value = "api for logout")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "logout successfull"),
			@ApiResponse(code = 201, message = "logout failed"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/logout")
	public Response<String> logOutFromBackend() throws Exception {
		if (!JwtRequestFilter.jwtToken.isEmpty())
			return userService.logOutFromBackend(JwtRequestFilter.jwtToken);
		else
			return new Response<>(400, "bad request token is not provided");
	}

	@ApiOperation(value = "api for reset passwords")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "verification successfull"),
			@ApiResponse(code = 200, message = "code sent to Emailid "),
			@ApiResponse(code = 201, message = "verification failed"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/forgot-password")
	public Response<String> forgotPassword(@RequestParam String emailId,
			@RequestParam(required = false) String verificationCode) throws Exception {

		if (emailId != null && verificationCode != null) {
			return userService.verifyCodeAndGenerateToken(emailId, verificationCode);
		} else {
			if (emailId != null)
				return userService.sendVerificationCodeToChangePassword(emailId);
			else
				return new Response<>(201, "provide emailId");
		}

	}

	@ApiOperation(value = "api for reset passwords")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "verification successfull"),
			@ApiResponse(code = 200, message = "code sent to Emailid "),
			@ApiResponse(code = 201, message = "verification failed"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/change-password")
	public Response<?> changePassword(@RequestHeader String emailId, @RequestParam(required = true) String newPassword)
			throws Exception {

		if (emailId != null && newPassword != null) {
			if (!JwtRequestFilter.jwtToken.isEmpty()) {
				return userService.changePassword(emailId, newPassword, JwtRequestFilter.jwtToken);
			} else {
				return new Response<>(400, "bad request token is not provided");
			}
		} else
			return Response.BAD_REQUEST;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}