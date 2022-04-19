Ready state vs page load strategy:
www.selenium.dev/documentation/webdriver/capabilities/shared/#pageloadstrategy
https://developer.mozilla.org/en-US/docs/Web/API/Document/readyState

Potential use:

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

When navigating to a new page via URL, by default Selenium will wait until the Document’s Ready State is “complete.”
The document.readyState property of a document describes the loading state of the current document.
By default, WebDriver will hold off on completing a navigation method (e.g., driver.navigate().get()) until the document ready state is complete.


