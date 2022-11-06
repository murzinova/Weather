import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MurzinovaTest {

    // TC_1_1 - Тест кейс
    // // 1. Открыть страницу https://openweathermap.org/
    // //2. Набрать в строке поиска город Paris
    // //3. Нажать пункт меню Search
    // //4. Из выпадающего списка выбрать Paris, FR
    // //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
               By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
              By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*
     * TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой страницы
     * OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testRedirectingToAPIGuidePage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement guideLink = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
        );
        guideLink.click();

        WebElement pageTitle = driver.findElement(
                By.xpath("//head//title[text()='OpenWeatherMap API guide - OpenWeatherMap']")
        );

        String actualResult = driver.getTitle();

        Assert.assertEquals(actualResult,expectedResult);

        expectedResult = "https://openweathermap.org/guide";
        actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /*
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test
    public void testTemperatureInF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String expectedResult = "F";
//        boolean expectedResult = true;

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement measureUnitLink = driver.findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );

        measureUnitLink.click();

        WebElement cityTemperatureInF = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(),'F')]")
        );
        Thread.sleep(2000);

//        CharSequence farengeit = "F";
//        boolean actualResult = cityTemperatureInF.getText().contains(farengeit);
        Assert.assertTrue(cityTemperatureInF.getText().endsWith(expectedResult));

        driver.quit();
    }

    /*
     * TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
     * We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
     * You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void testCookiesPanelAndButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential " +
                "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies " +
                "or manage them individually.";

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement cookiesTextPanel = driver.findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );
        cookiesTextPanel.click();

        String actualResult = cookiesTextPanel.getText();

        Assert.assertEquals(actualResult,expectedResult);

        expectedResult = "Allow all";

        WebElement cookiesAllowAllButton = driver.findElement(
                By.xpath("//button[@class='stick-footer-panel__link']"));

        actualResult = cookiesAllowAllButton.getText();

        Assert.assertEquals(actualResult,expectedResult);

        expectedResult = "Manage cookies";

        WebElement cookiesManageCookiesButton = driver.findElement(
                By.xpath("//a[@class='stick-footer-panel__link']"));
        actualResult = cookiesManageCookiesButton.getText();

        driver.quit();
    }

    /*
     * TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */

    @Test
    public void testSupportDropdownList() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String expectedResult = "FAQ";

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportLink = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportLink.click();

        WebElement FAQElement = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));

        String actualResult = FAQElement.getText();

        Assert.assertEquals(actualResult,expectedResult);

        expectedResult = "How to start";

        WebElement HowToStartElement = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='/appid']")
        );
        actualResult = HowToStartElement.getText();

        Assert.assertEquals(actualResult,expectedResult);

        expectedResult = "Ask a question";

        WebElement askAQuestionElement = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@target='_blank']")
        );
        actualResult = askAQuestionElement.getText();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /*
     * TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void testErrorMessageWithoutCaptchaVerification() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String email = "qetydos@mailinator.com";
        String message = "Some text";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportLink = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportLink.click();

        String originalWindow = driver.getWindowHandle();

        WebElement askAQuestionElement = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@target='_blank']")
        );

        askAQuestionElement.click();
        Thread.sleep(2000);

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement emailField = driver.findElement(
                By.xpath("//input[@class='form-control string email required']")
        );
        Thread.sleep(2000);
        emailField.click();
        emailField.sendKeys(email);

        WebElement subjectDropdownList = driver.findElement(
                By.xpath("//select[@class='form-control select required']")
        );
        subjectDropdownList.click();

        WebElement otherSubjectSelector = driver.findElement(
                By.xpath("//option[@value='Other']")
        );
        otherSubjectSelector.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']")
        );
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@class='btn btn-default']")
        );

        submitButton.click();

        WebElement captchaErrorMessage = driver.findElement(
                By.xpath("//div[@class='help-block']")
        );

        String actualResult = captchaErrorMessage.getText();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /*
     * TC_11_06
     * 1.  Открыть базовую ссылку
     * 2.  Нажать пункт меню Support → Ask a question
     * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     */

    @Test
    public void testErrorMessageIfEmailFieldIsBlank() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String message = "Some text";
        String expectedResult = "can't be blank";

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportLink = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportLink.click();

        String originalWindow = driver.getWindowHandle();

        WebElement askAQuestionElement = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@target='_blank']")
        );

        askAQuestionElement.click();
        Thread.sleep(2000);

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement subjectDropdownList = driver.findElement(
                By.xpath("//select[@class='form-control select required']")
        );
        subjectDropdownList.click();

        WebElement otherSubjectSelector = driver.findElement(
                By.xpath("//option[@value='Other']")
        );
        otherSubjectSelector.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']")
        );
        messageField.sendKeys(message);

        WebElement captchaField = driver.findElement(
                By.xpath("//iframe[@title='reCAPTCHA']")
        );

        captchaField.click();

        Thread.sleep(5000);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@class='btn btn-default']")
        );

        submitButton.click();

        WebElement blankEmailErrorMessage = driver.findElement(
                By.xpath("//span[@class='help-block']")
        );

        String actualResult = blankEmailErrorMessage.getText();
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /*
     * TC_11_07
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */

    @Test
    public void testTemperatureInC() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        boolean expectedResult = true;

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement imperialFLink = driver.findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );
        imperialFLink.click();

        WebElement metricCLink = driver.findElement(
                By.xpath("//div[@class='option'][text()='Metric: °C, m/s']")
        );
        metricCLink.click();

        WebElement cityTemperatureInC = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(),'C')]")
        );
        Thread.sleep(2000);

        CharSequence celsium = "C";
        boolean actualResult = cityTemperatureInC.getText().contains(celsium);

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /*
     * TC_11_08
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на лого компании
     * 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */

    @Test
    public void testUnchangeableLinkAfterClickingLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement companyLogo = driver.findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        companyLogo.click();
        Thread.sleep(6000);

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /*
     * TC_11_09
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */

    @Test
    public void testRedirectingAfterUsingSearchField() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        String cityName = "Rome";
        String findInURL = "find";

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchField = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
        );
        searchField.click();

        searchField.sendKeys(cityName);
        Thread.sleep(2000);

        searchField.sendKeys(Keys.ENTER);

        boolean actualResult = driver.getCurrentUrl().contains(findInURL) && driver.getCurrentUrl().contains(cityName);

        Assert.assertTrue(actualResult);

        String expectedResult1 = "Rome";

        WebElement searchResultField = driver.findElement(
                By.xpath("//input[@id='search_str']")
        );
        String actualResult1 = searchResultField.getAttribute("value");

        Assert.assertEquals(actualResult1,expectedResult1);

        driver.quit();
    }

    /*
     * TC_11_10
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню API
     * 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */

    @Test
    public void testOrangeButtonsQuantity() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String urlBasic = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(urlBasic);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement navBarAPILink = driver.findElement(
                By.xpath("//nav[@id='nav-website']//div[@id='desktop-menu']//a[@href='/api']"));

        navBarAPILink.click();

        int actualResult = driver.findElements(By.xpath("//a[contains(@class,'orange')]")).size();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }













}
