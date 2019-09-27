package edu.csupomona.cs480.controller;

import java.util.List;
import org.apache.commons.io.IOUtils;

import java.io.*;
import org.apache.commons.io.LineIterator;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;





/*imports for Taha's grab hyperlinks function */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/*end of imports for Taha's grab hyperlinks function */


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080 or http://localhost:8080/cs480/ping
		return "OK-";
	}
	
	@RequestMapping(value = "/cs480/willow", method = RequestMethod.GET)
	String willowFunction() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/cs480/willow
		return "OK-It's Willow";
	}
	
	@RequestMapping(value = "/cs480/taha", method = RequestMethod.GET)
	String tahasFunction() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/cs480/taha
		return "OK-It's Taha";
	}

	
	/* This is Jay Savla's edit of the file to make a simple HTTP API.
	*/
	@RequestMapping(value = "/cs480/jay", method = RequestMethod.GET)
	String jaysFunction() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK - It's Jay";
	}

	@RequestMapping(value = "/cs480/anirban", method = RequestMethod.GET)
	String anirbansFunction() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/cs480/taha
		return "OK-It's Anirban";
	}
	

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}
	
	/********** Test of Commons Math API*******/
	/**
	 * This method can inverse a matrix 
	 * @param matrix
     * @return
	 */
	double[][] array(double[][] matrix)
	{
     Array2DRowRealMatrix rMatrix=new Array2DRowRealMatrix(matrix);
	 RealMatrix inv=MatrixUtils.inverse(rMatrix);
	 double[][]invert=inv.getData();
	 return invert;
	}

	/******* Test of GSon Library by Jay Savla
	*/
	@RequestMapping(value = "/cs480/users/jaylist", method = RequestMethod.GET)
	String helloGson() {
		Gson gson = new Gson();
		int[] ints = {1, 2, 3, 4, 5};
		String[] strings = {"abc", "def", "ghi"};
		gson.toJson(ints);    
		gson.toJson(strings);
	}
	
	/********** Test of Commons-IO ******* by Willow *******/
	@RequestMapping(value = "/cs480/users/willowlist", method = RequestMethod.GET)
	void willow(String[] args)
	{
		out = new PrintWriter(System.out,true);
        //Creating InputStream from String.
        out.println("This is test by Willow");
	}
	
	
	@RequestMapping(value = "/cs480/grabhyperlinks", method = RequestMethod.GET)
		   public static void main(String[] args) {

	        Document doc;
	        try {

	            // need http protocol
	            doc = Jsoup.connect("http://google.com").get();

	            // get page title
	            String title = doc.title();
	            System.out.println("title : " + title);

	            // get all links
	            Elements links = doc.select("a[href]");
	            for (Element link : links) {

	                // get the value from href attribute
	                System.out.println("\nlink : " + link.attr("href"));
	                System.out.println("text : " + link.text());

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    
	
	
	}
	
	
	
}
