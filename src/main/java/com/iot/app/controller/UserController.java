package com.iot.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iot.app.Service.UserService;
import com.iot.app.dto.SignUpDto;
import com.iot.app.dto.SocialsignUpDto;
import com.iot.app.model.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")

public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "api to register the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "registration successfull"),
			@ApiResponse(code = 201, message = "email id already registered"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/signup")
	public Response<String> registerUser(@RequestBody @Valid SignUpDto signUpDto) throws Exception {
		return userService.registerUser(signUpDto);
	}
	
	@ApiOperation(value = "api to print via spring secured url")
	@GetMapping("/protected-url")
	public String hello() {
		return "hello ujjwal";
	}
	
	@ApiOperation(value = "api to verify the email")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "verification successfull"),
			@ApiResponse(code = 200, message = "email id sent"),
			@ApiResponse(code = 201, message = "verification failed"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/verifiy-emailId")
	public Response<String> verifyEmail(@RequestParam @Email(message = "provided valid email address")String emailId,
			@RequestParam(required = true) String verificationCode,HttpServletResponse response) throws Exception {

		if (emailId != null && verificationCode != null) {
			
			Response<Map<String, Object>> verifyCode = userService.verifyCode(emailId, verificationCode);
			if(verifyCode.getStatusCode()==200)
			{
			response.addHeader("authorization",verifyCode.getData().get("token").toString());
			return new Response(verifyCode.getStatusCode(), verifyCode.getMessage(), verifyCode.getData().get("dataResponseDto"));
			}
			{
				return new Response<>(verifyCode.getStatusCode(), verifyCode.getMessage());
			}
			
		} else {
			return new Response<>(403, "bad request");
		}
	}
	
	/* /auth/logout controller written in jwt auth controller */
	/* /auth/forget password controller written in jwt auth controller */
	/* /auth/change password controller written in jwt auth controller */
	
	@ApiOperation(value = "api to register the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "registration successfull"),
			@ApiResponse(code = 201, message = "email id already registered"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/social-signup")
	public Response<String> socialSignup(@RequestBody @Valid SocialsignUpDto socialsignUpDto) throws Exception {
		return userService.socialSignup(socialsignUpDto);

	}
}
