package Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.fancode.basetest.BaseTest;
import com.fancode.constants.Constants;
import com.fancode.endpoints.Routes;
import com.fancode.methodimplementation.ValidationMethods;
import com.fancode.methodimplementation.getMethod;
import com.fancode.pojo.ToDos;
import com.fancode.pojo.Users;
import com.fancode.utilities.JasonResponseToArray;

import io.restassured.response.Response;

public class TestCompleteTaskPercentage extends BaseTest {
	
	@Test(priority = 1)
	public void isCompeledTaskMoreThan50Percent() {
		
		Response usersRes = getMethod.getMethod(Routes.USERS);
		//System.out.println(usersRes.getStatusCode());
		Assert.assertEquals(usersRes.getStatusCode(), 200, "Get request failed");
		logger.info("get request for user api is succesful");
		
		com.fancode.pojo.Users[] userArray = JasonResponseToArray.convertResponseToArray(usersRes, com.fancode.pojo.Users[].class);
		
		for (Users user : userArray) {
			double Lat = Double.parseDouble(user.getAddress().getGeo().getLat());
			double Lng = Double.parseDouble(user.getAddress().getGeo().getLng());
			
			if(ValidationMethods.IsFancodeCity(Lat,Lng)) {
				logger.info("user with user id: "+user.getId()+" is fancodeCity member");
				
				Response ToDoRes = given().queryParam("userId", user.getId()).when().get(Routes.TODOS);
				Assert.assertEquals(usersRes.getStatusCode(), 200, "Get request failed");
				logger.info("get request for ToDo api is succesful");
				
				com.fancode.pojo.ToDos[] todoArray = JasonResponseToArray.convertResponseToArray(ToDoRes, com.fancode.pojo.ToDos[].class);
				
				int Totaltask = todoArray.length;
				int completedTask =0;
				
				
				for (ToDos todo : todoArray) {
					if (todo.isCompleted() == true) {
						completedTask++;
					}
				}
				
				if(ValidationMethods.CalculatePercentage(completedTask, Totaltask)>Constants.expectedPercentageVal) {
					logger.log(Status.PASS, "the the fancodecity user with userid: "+user.getId()+" has more than 50% of task completed");
				}
				
				Assert.assertTrue(ValidationMethods.CalculatePercentage(completedTask, Totaltask)>Constants.expectedPercentageVal,
						"User " + user.getId() + "In the Fancode city has less than 50% Task Completed");
				
			}
		}
		
	}
	

}
