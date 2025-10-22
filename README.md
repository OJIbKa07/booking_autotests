# Проект по автоматизации тестовых сценариев для сайта [Booking.com](https://www.booking.com/)
<a href="https://www.booking.com/" target="_blank">
    <img width="100%" title="Booking" src="images/logo/booking-logo.jpg">
</a>

<h2 id="содержание">📜 Содержание:</h2>

- [Используемый стек](#computer-используемый-стек)
- [Локальный запуск тестов](#локальный-запуск-тестов)
- [Удаленный запуск тестов](#удаленный-запуск-тестов)
- [Установленная конфигурация](#установленная-конфигурация)
- [Интеграция с Jenkins](#интеграция-с-jenkins)
- [Интеграция с Allure](#интеграция-с-allure)
- [Интеграция с Allure Testops](#интеграция-с-allure-testops)
- [Уведомления в Telegram](#уведомления-telegram)
- [Видео пример запуска тестов в Selenoid](#видео-пример)

## :computer: Используемый стек

<p align="center">
<a href="https://www.jetbrains.com/idea/" target="_blank">
    <img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
</a>
<a href="https://www.java.com/" target="_blank">
    <img width="6%" title="Java" src="images/logo/Java.svg">
</a>
<a href="https://selenide.org/" target="_blank">
    <img width="5%" title="Selenide" src="images/logo/Selenide.png">
</a>
<a href="https://selenoid.autotests.cloud/" target="_blank">
    <img width="6%" title="Selenoid" src="images/logo/Selenoid.png">
</a>
<a href="https://allurereport.org/" target="_blank">
    <img width="6%" title="Allure Report" src="images/logo/allure_report.png">
</a>
<a href="https://qameta.io/" target="_blank">
    <img width="6%" title="Allure Test Ops" src="images/logo/allure_testops.png">
</a>
<a href="https://gradle.org/" target="_blank">
    <img width="6%" title="Gradle" src="images/logo/Gradle.svg">
</a>
<a href="https://junit.org/junit5/" target="_blank">
    <img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
</a>
<a href="https://github.com/" target="_blank">
    <img width="6%" title="GitHub" src="images/logo/Github.svg">
</a>
<a href="https://www.jenkins.io/" target="_blank">
    <img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
</a>
<a href="https://telegram.org/" target="_blank">
    <img width="6%" title="Telegram" src="images/logo/Telegram.png">
</a>
</p>

Тесты в данном проекте написаны на языке <code>Java</code>, сборщик - <code>Gradle</code>. Так же были использованы фреймворки <code>JUnit 5</code> и [Selenide](https://selenide.org/).
При прогоне тестов браузер запускается не локально, а в [Selenoid](https://aerokube.com/selenoid/).
Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота.

Содержание Allure-отчета:
* Шаги теста;
* Скриншот страницы на последнем шаге;
* Page Source;
* Логи браузерной консоли;
* Видео выполнения автотеста.

[Вернуться к оглавлению⬆️](#содержание) 

## :arrow_forward: Запуск автотестов

### Локальный запуск тестов
```
./gradlew clean test -Denv=local
```
При выполнении данной команды в терминале IDE тесты запустятся локально в браузере Chrome.

<code>clean</code> — удаляет каталог build/ в проекте (все скомпилированные классы, отчёты, кэш тестов, временные файлы);

<code>-Denv</code> — переменная, которая принимает значения <code>local</code> (локальный запуск) и <code>remote</code> (удаленный запуск);

<code>test</code> — запускает задачу тестирования Gradle, которая:

- компилирует тесты и основной код;
- запускает тесты (JUnit, TestNG и т.д.);
- формирует отчёты (например, `build/reports/tests/test/index.html`).

[Вернуться к оглавлению⬆️](#содержание)

### Удаленный запуск тестов

Запуск с настройками из remote.properties:
```
./gradlew clean test clean test -Denv=remote
```
Команда для запуска из Jenkins (параметрами можно управлять):
```
./gradlew clean test clean test -Denv=remote -Dbrowser=${BROWSER} -DbrowserVersion=${BROWSER_VERSION} -DbrowserSize=${BROWSER_SIZE} -DremoteWebDriverUrl=${REMOTE} -DbaseUrl=${URL}
```
где
- <code>Dbrowser</code> - название браузера. Доступны опции chrome/opera/firefox;
- <code>DbrowserVersion</code> - версия браузера. Для chrome - 127.0 / 128.0; для firefox = 124.0 / 125.0;
- <code>DbrowserSize</code> - размер окна браузера. До дефолту 1920x1080;
- <code>DremoteWebDriverUrl</code> - адрес удаленного веб-драйвера;
- <code>DbaseUrl</code> - базовый урл, на котором будут запускаться автотесты.

[Вернуться к оглавлению⬆️](#содержание)

### Установленная конфигурация

* Браузер - <code>chrome</code>;
* Версия браузера - <code>128.0</code>;
* Расширение окна браузера - <code>1920x1080</code>;
* Базовый URL - <code>https://www.booking.com</code>.

[Вернуться к оглавлению⬆️](#содержание)

<a id="интеграция-с-jenkins"></a>
## <img width="4%" style="vertical-align:middle" title="Jenkins" src="images/logo/Jenkins.svg"> [Интеграция с Jenkins](https://jenkins.autotests.cloud/view/QA.GURU%20students/job/c36-oPalushina-qa_guru-booking/48/)
<p align="center">
    <img title="Jenkins Build" src="images/screenshot/jenkinsBuild.png">
</p>

[Вернуться к оглавлению⬆️](#содержание)

<a id="интеграция-с-allure"></a>
## <img width="4%" style="vertical-align:middle" title="Allure Report" src="images/logo/allure_report.png"> [Интеграция с Allure](https://jenkins.autotests.cloud/job/c36-oPalushina-qa_guru-booking/48/allure/#)
<p align="center">
    <img title="Allure Overview" src="images/screenshot/Allure_Report.png">
</p>

[Результат успешного прогона](https://jenkins.autotests.cloud/job/c36-oPalushina-qa_guru-booking/48/allure/#)
<p align="center">
<img title="Allure Overview" src="images/screenshot/ResultsTest.png">
</p>

[Вернуться к оглавлению⬆️](#содержание)

<a id="интеграция-с-allure-testops"></a>
## <img width="4%" style="vertical-align:middle" title="Allure Report" src="images/logo/allure_testops.png"> [Интеграция с Allure Test Ops](https://allure.autotests.cloud/project/4948/dashboards)

<p align="center">
<a href="https://allure.autotests.cloud/project/4948/dashboards" target="_blank">
    <img title="" src="images/screenshot/allure_testops_dashboard.png">
</a>
</p>

[Allure Test Ops тест-кейсы](https://allure.autotests.cloud/project/4948/dashboards)
<p align="center">
<a href="https://allure.autotests.cloud/project/4948/test-cases?treeId=0" target="_blank">
    <img title="" src="images/screenshot/allure_testops_cases.png">
</a>
</p>

[Вернуться к оглавлению⬆️](#-содержание)

<a id="уведомления-telegram"></a>
### <img width="4%" style="vertical-align:middle" title="Telegram" src="images/logo/Telegram.png"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.

<p align="center">
<img width="70%" title="Telegram Notifications" src="images/screenshot/TelegramNotifications.png">
</p>

[Вернуться к оглавлению⬆️](#содержание)

<a id="видео-пример"></a>
### <img width="4%" style="vertical-align:middle" title="Selenoid" src="images/logo/Selenoid.png"> Видео пример запуска тестов в Selenoid

К каждому тесту в отчете прилагается видео прогона.
<p align="center">
  <img title="Selenoid Video" src="images/screenshot/videoExample.gif">
</p>

[Вернуться к оглавлению⬆️](#содержание) 
