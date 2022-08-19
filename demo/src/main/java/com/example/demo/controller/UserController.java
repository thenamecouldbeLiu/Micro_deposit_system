package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User new_user) throws SQLException {
		//System.out.println("ENTERLINE");
		//System.out.println(new_user.getEmail()); //成功建立user obj

		String[] result_array  = userService.addUser(new_user);
		
		if (result_array[0] == "200") {
		return ResponseEntity.ok(result_array[1]); // idx 1 是訊息回報
		}
		
		return new ResponseEntity<>(result_array[1], HttpStatus.BAD_REQUEST);
		}
	
	@PostMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestBody User query_user) {
		//System.out.println("into controller");
		
	 	//User user = userService.findUser(email, password, tw_id);
		//return user.getEmail();

			Object[] pair_array = userService.findUser(query_user);
			
			
			if ((boolean) pair_array[0]) {
				query_user = (User) pair_array[1];
				return new ResponseEntity<>(query_user, HttpStatus.OK);
				
				
			}else {
				query_user = new User("#","#","#","#");
				return new ResponseEntity<>(query_user, HttpStatus.BAD_REQUEST);
			}
		}
		
	@PostMapping("/login")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody User query_user, HttpSession session) {
		//System.out.println("into controller");
		
	 	//User user = userService.findUser(email, password, tw_id);
		//return user.getEmail();

		Object[] pair_array = userService.findUser(query_user);
		Map<String, Object> name_id = new HashMap<>();
		
		
		if ((boolean) pair_array[0]) {
			query_user = (User) pair_array[1];
			
	        session.setAttribute("user_id",query_user.getUser_id());
	        session.setAttribute("name",query_user.getName());
	        
	        //String id_str = String.valueOf(query_user.getUser_id());
	        
	        name_id.put("user_id", query_user.getUser_id());
	        name_id.put("name",query_user.getName());
	        				
			return new ResponseEntity<>(name_id, HttpStatus.OK);
			
			
		}else {
			name_id.put("user_id", "-1");
	        name_id.put("name","None");
			return new ResponseEntity<>(name_id, HttpStatus.BAD_REQUEST);
		}
	}
          
	//用來獲取session中的username，在前端某些需要的地方會用到。
    @GetMapping("/session-username")
    public  String getSessionUserName(HttpSession session){ //@Path用來取得url路徑的值
	        return (String)session.getAttribute("username");
	    }

	    //用戶登出
    @GetMapping("/sign_out")
    public String signOut(HttpSession session){
	      //銷毀session中的KV
	        session.removeAttribute("user_id");
	        session.removeAttribute("name");
	        return "登出成功";
	    }

}
