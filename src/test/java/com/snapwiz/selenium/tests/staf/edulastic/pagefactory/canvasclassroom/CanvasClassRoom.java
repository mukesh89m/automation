package com.snapwiz.selenium.tests.staf.edulastic.pagefactory.canvasclassroom;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Priyanka on 06-10-2016.
 */
public class CanvasClassRoom {

 @FindBy(xpath = "//div[@class='menu-item-icon-container']")
 public List<WebElement> sideNavigator;

 @FindBy(xpath = "//a[text()='Profile']")
 public WebElement profile_Link;

 @FindBy(css = "img[alt='Canvas by Instructure']")
 public WebElement canvasLogo;

 @FindBy(css = ".ic-DashboardCard__link")
 public List<WebElement> classes;

 @FindBy(className = "assignments")
 public WebElement assignments_Menu;

 @FindBy(className = "ig-title")
 public List<WebElement> assignmentName;

 @FindBy(className = "ig-details")
 public List<WebElement> dueDateDetails;

 @FindBy(xpath = "//button[@class='Button Button--small']")
 public WebElement logout;

 @FindBy(xpath = "//button[@class='btn']")
 public WebElement loadRegressionInNewWindow;

 @FindBy(className = "grades")
 public WebElement grades;

 @FindBy(className = "gradebook_filter")
 public WebElement studentFilterTextBox;

 @FindBy(xpath = "//div[@class='header-right-side']")
 public WebElement showMore;

 @FindBy(css = ".content_summary")
 public List<WebElement> assignmentList;

 @FindBy(css = ".score-display")
 public List<WebElement> score;

 @FindBy(css = ".people")
 public WebElement people;

 @FindBy(css = ".right")
 public  List<WebElement> dropdownArrow;

 @FindBy(xpath = "//a[@data-event='removeFromCourse']")
 public  List<WebElement> removeFromCourse;

 @FindBy(xpath = "//div[@class='ic-notification__message']")
 public WebElement noCourseNotificationMessage;

 @FindBy(id = "addUsers")
 public WebElement addPeople;

 @FindBy(id = "user_list_textarea")
 public WebElement userListTextArea;

 @FindBy(id = "next-step")
 public WebElement nextButton;

 @FindBy(id = "createUsersAddButton")
 public WebElement addUser;

 @FindBy(xpath = "//button[text()='Done']")
 public WebElement doneButton;

 @FindBy(xpath = "//button[text()='Accept']")
 public WebElement accept;

 @FindBy(xpath = "//a[@data-event='deactivateUser']")
 public List<WebElement> deactivateUser;

 @FindBy(xpath = "//span[text()='inactive']")
 public WebElement inactive;

 @FindBy(xpath = "//a[@data-event='reactivateUser']")
 public List<WebElement> reactivateUser;

 @FindBy(className = "more_user_information_link")
 public WebElement moreUserDetails;

 @FindBy(className = "conclude_enrollment_link")
 public WebElement concludeEnrollment;

 @FindBy(className = "unconclude_enrollment_link")
 public WebElement restoreEnrollment;













}
