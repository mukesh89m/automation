package com.snapwiz.selenium.tests.staf.framework.utility;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by murthi on 07-01-2016.
 */
public class WebDriverFactory {

    public static Map<Long, String> driverExeMap = new HashMap<>();

    public static WebDriver startDriver(String browserName, boolean isLocal){

        Config.readconfiguration();

        WebDriver driver=null;
        Browser browser =Browser.getByName(browserName);
        DesiredCapabilities desCap=null;
        DesiredCapabilities capabilities=null;
        FirefoxProfile ffPro=null;
        //starting the browser
        if(driver==null){
            if(isLocal){
                switch (browser){

                    case CHROME:
                      /*  String zipName=getZipFileName("chromedriver");
                        ClassLoader classLoader = WebDriverFactory.class.getClassLoader();
                        File zipFile = new File(classLoader.getResource(zipName).getFile());
                        String chromeBinaryPath = unZipDriver(zipFile);
                        driverExeMap.put(Thread.currentThread().getId(),chromeBinaryPath);
                        File driverExecutable = new File(chromeBinaryPath);
                        driverExecutable.setExecutable(true);*/
                        System.setProperty("webdriver.chrome.driver", "/home/mukesh/Downloads/chromedriver");
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
                        ffPro=new FirefoxProfile();
                        ffPro.setEnableNativeEvents(true);
                        driver=new FirefoxDriver(ffPro);
                        break;
                    case GECKO:
                        String zipFileName=getZipFileName("geckodriver");
                        ClassLoader classLoaderGecko = WebDriverFactory.class.getClassLoader();
                        File zipFileGecko = new File(classLoaderGecko.getResource(zipFileName).getFile());
                        String geckoBinaryPath = unZipDriver(zipFileGecko);
                        File geckoDriverExecutable = new File(geckoBinaryPath);
                        geckoDriverExecutable.setExecutable(true);
                        System.setProperty("webdriver.gecko.driver", geckoBinaryPath);
                        driver = new FirefoxDriver();
                        break;
                    case UNIT:
//                        driver=new HtmlUnitDriver(true);
//                        ((HtmlUnitDriver)driver).setJavascriptEnabled(true);
                        break;
                    case PHANTOMJS:
                        File file=new File("/home/mukesh/Downloads/phantomjs/bin/phantomjs");
                        System.setProperty("phantomjs.binary.path",file.getAbsolutePath());
                        driver=new PhantomJSDriver();
                        break;
                    case IE:
                        String zipNameIE=getZipFileName("iedriver");
                        ClassLoader classLoaderIE = WebDriverFactory.class.getClassLoader();
                        File zipFileIE = new File(classLoaderIE.getResource(zipNameIE).getFile());
                        String ieBinaryPath = unZipDriver(zipFileIE);
                        driverExeMap.put(Thread.currentThread().getId(),ieBinaryPath);
                        File driverExecutableIE = new File(ieBinaryPath);
                        driverExecutableIE.setExecutable(true);
                        desCap = DesiredCapabilities.internetExplorer();
                        desCap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
                        System.setProperty("webdriver.ie.driver", ieBinaryPath);
                        driver = new InternetExplorerDriver(desCap);
                        break;
                    case SAFARI:
                        driver=new SafariDriver();
                        break;
                    case REMOTE:

                        if (Config.remoteBrowser.equalsIgnoreCase("FIREFOX")){
                            capabilities=DesiredCapabilities.firefox();
                        }

                        if (Config.remoteBrowser.equalsIgnoreCase("CHROME")){
                            capabilities=DesiredCapabilities.chrome();
                        }
                        String version = System.getProperty("version");
                        //version = version.substring(7);
                        capabilities.setCapability("version",version);

                        String gridURL = "http://127.0.0.1:4444/wd/hub";
                    try {

                        driver = new RemoteWebDriver(new URL(gridURL), capabilities);
                    }

                    catch(MalformedURLException e){

                    }
                        break;
                }

            }
            else{
                String gridHubIP=Properties.getPropertyValue("HubIP");
                String gridHubPort=Properties.getPropertyValue("HubPort");
                String gridHubURL=null;
                if(gridHubIP != null && gridHubPort != null)
                    gridHubURL="http://"+gridHubIP+":"+gridHubPort+"/wd/hub";
                else{
                    System.out.println("The selenium Grid hub or port cann't be null");
                }
                switch(browser){
                    case FIREFOX:
                        desCap=DesiredCapabilities.firefox();
                        try {
                            driver=new RemoteWebDriver(new URL(gridHubURL),desCap);
                        } catch (MalformedURLException e) {
                            System.out.println("The grid URL is not valid. please check it");
                            e.printStackTrace();
                        }
                        break;
                    case CHROME:
                        desCap=DesiredCapabilities.chrome();
                        try {
                            driver=new RemoteWebDriver(new URL(gridHubURL),desCap);
                        } catch (MalformedURLException e) {
                            System.out.println("The grid URL is not valid. please check it");
                            e.printStackTrace();
                        }
                        break;
                    case PHANTOMJS:
                        desCap=new DesiredCapabilities();
                        desCap.setBrowserName("phantomjs");
                        try {
                            driver=new RemoteWebDriver(new URL(gridHubURL),desCap);
                        } catch (MalformedURLException e) {
                            System.out.println("The grid URL is not valid. please check it");
                            e.printStackTrace();
                        }
                        break;
                    default:
                            System.out.println("The browser name is not matching with any case. please check your browser name");
                }
                getIPOfNode(driver);
            }
        }

        //Initial settings
        if(driver!=null){

            //timeout settings
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);

            //maximize window
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Get zip file name
     */
    public static String getZipFileName(String driver){
        String architecture = System.getProperty("os.arch");
        String os = System.getProperty("os.name").toLowerCase();
        String zipName = driver.trim().toLowerCase()+"_";
        if (os.contains("mac")) {
            zipName += "mac32.zip";
        }
        else if(driver.contains("gecko") && os.contains("window") ){
            if (architecture.contains("64")) {
                zipName+="win64.zip";
            }
            else{
                zipName+="win32.zip";
            }
        }
        else if (os.contains("window")) {
//            if (architecture.contains("_64")) {
//                zipName += "win64.zip";
//            }else{
                zipName += "win32.zip";
//            }

        } else {
//            if (architecture.contains("_64")) {
                zipName += "linux64.zip";
//            } else {     //32 bit architecture
//                zipName += "linux32.zip";
//            }
        }
        return zipName;
    }
    /**
     * Unzip it
     *
     * @param zipFile input zip file
     */
    public static String unZipDriver(File zipFile) {
        String absFilePath = null;
        byte[] buffer = new byte[1024];
        try {

            //get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            String fileName = ze.getName();
            File newFile = new File(System.currentTimeMillis() + fileName);

            absFilePath = newFile.getPath();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fos.close();

            zis.closeEntry();
            zis.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return absFilePath;
    }

    public static String getDriverExe(){
        return driverExeMap.get(Thread.currentThread().getId());
    }

    public static void deleteDriverExe() {
        String driverExe=getDriverExe();
        if(driverExe!=null){
            File driExeFile=new File(driverExe);
            driExeFile.deleteOnExit();
        }
    }

    public static String getIPOfNode(WebDriver remoteDriver1) {
        String hostFound = null;
        if(remoteDriver1!=null){
            RemoteWebDriver remoteDriver = (RemoteWebDriver) remoteDriver1;
            try {
                HttpCommandExecutor ce = (HttpCommandExecutor) remoteDriver.getCommandExecutor();
                String hostName = ce.getAddressOfRemoteServer().getHost();
                int port = ce.getAddressOfRemoteServer().getPort();
                HttpHost host = new HttpHost(hostName, port);
                HttpClient client = HttpClientBuilder.create().build();
                System.out.println("Session Id: "+ remoteDriver.getSessionId());
                URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + remoteDriver.getSessionId());
                BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest( "POST", sessionURL.toExternalForm());
                HttpResponse response = client.execute(host, r);
                InputStream contents = response.getEntity().getContent();
                StringWriter writer = new StringWriter();
                IOUtils.copy(contents, writer, "UTF8");
                JSONObject object = new JSONObject(writer.toString());
                URL myURL = new URL(object.getString("proxyId"));
                if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
                    hostFound = myURL.getHost();
                    System.out.println("THE TEST IS GETTING EXECUTED IN THE NODE WITH IPADDRESS = " + hostFound);
                }
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
        return hostFound;
    }
}
