# Проект по автоматизации тестовых сценариев для сайта Booking.com
## :scroll: Содержание:

- [Используемый стек](#computer-используемый-стек)
- [Запуск автотестов](#arrow_forward-запуск-автотестов)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Интеграция Allure Testops](#-интеграция-allure-testops)
- [Интеграция Jira](#-интеграция-jira)
- [Результат успешного прогона](#-результат-успешного-прогона)
- [Уведомления в Telegram](#-уведомления-в-telegram-с-использованием-бота)
- [Видео примера запуска тестов в Selenoid](#-видео-пример-запуска-тестов-в-Selenoid)

## :computer: Используемый стек

<p align="center">
<a href="https://www.jetbrains.com/idea/" target="_blank">
    <img width="6%" title="IntelliJ IDEA" src="src/images/logo/Intelij_IDEA.svg">
</a>
<a href="https://www.java.com/" target="_blank">
    <img width="6%" title="Java" src="src/images/logo/Java.svg">
</a>
<a href="https://selenide.org/" target="_blank">
    <img width="5%" title="Selenide" src="src/images/logo/Selenide.png">
</a>
<a href="https://selenoid.autotests.cloud/" target="_blank">
    <img width="6%" title="Selenoid" src="src/images/logo/Selenoid.png">
</a>
<a href="https://allurereport.org/" target="_blank">
    <img width="6%" title="Allure Report" src="src/images/logo/allure_report.png">
</a>
<a href="https://qameta.io/" target="_blank">
    <img width="6%" title="Allure Test Ops" src="src/images/logo/allure_testops.png">
</a>
<a href="https://gradle.org/" target="_blank">
    <img width="6%" title="Gradle" src="src/images/logo/Gradle.svg">
</a>
<a href="https://junit.org/junit5/" target="_blank">
    <img width="6%" title="JUnit5" src="src/images/logo/JUnit5.svg">
</a>
<a href="https://github.com/" target="_blank">
    <img width="6%" title="GitHub" src="src/images/logo/Github.svg">
</a>
<a href="https://www.jenkins.io/" target="_blank">
    <img width="6%" title="Jenkins" src="src/images/logo/Jenkins.svg">
</a>
<a href="https://telegram.org/" target="_blank">
    <img width="6%" title="Telegram" src="src/images/logo/Telegram.png">
</a>
<a href="https://www.atlassian.com/software/jira"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jira/jira-original.svg" title="Jira" alt="Jira" width="40" height="40"/> 
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

## :arrow_forward: Запуск автотестов

### Запуск тестов из терминала
```
./gradlew clean test -Devn=remote
```
При выполнении данной команды в терминале IDE тесты запустятся удаленно в <code>Selenoid</code>.

<code>clean</code> — удаляет каталог build/ в проекте (все скомпилированные классы, отчёты, кэш тестов, временные файлы);

<code>-Devn</code> — переменная, которая принимает значения <code>local</code> (локальный запуск) и <code>remote</code> (удаленный запук);

<code>test</code> — запускает задачу тестирования Gradle, которая:

- компилирует тесты и основной код;
- запускает тесты (JUnit, TestNG и т.д.);
- формирует отчёты (например, `build/reports/tests/test/index.html`).

Также можно управлять переменными удаленного запуска:

```
./gradlew clean test clean test -Denv=remote -Dbrowser=${BROWSER} -DbrowserVersion=${BROWSER_VERSION} -DbrowserSize=${BROWSER_SIZE} -DremoteWebDriverUrl=${REMOTE} -DbaseUrl=${URL}
```
где
- <code>Dbrowser</code> - название браузера. Доступны опции chrome/opera/firefox;
- <code>DbrowserVersion</code> - версия браузера. Для chrome - 127.0 / 128.0; для opera - 108.0 / 109.0; для firefox = 124.0 / 125.0;
- <code>DbrowserSize</code> - размер окна браузера. До дефолту 1920x1080;
- <code>DremoteWebDriverUrl</code> - адрес удаленного веб-драйвера;
- <code>DbaseUrl</code> - базовый урл, на котором будут запускаться автотесты.


### Установленная конфигурация

* Браузер - <code>chrome</code>;
* Версия браузера - <code>128.0</code>;
* Расширение окна браузера - <code>1920x1080</code>;
* Базовый URL - <code>https://www.booking.com</code>.


## <img width="4%" style="vertical-align:middle" title="Jenkins" src="src/images/logo/Jenkins.svg"> Сборка в Jenkins
[Сборка в Jenkins](https://jenkins.autotests.cloud/view/QA.GURU%20students/job/c36-oPalushina-qa_guru-booking/1/)
<p align="center">
    <img title="Jenkins Build" src="src/images/screenshot/jenkinsBuild.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/images/logo/allure_report.png"> Пример Allure-отчета
[Allure  отчет](https://jenkins.autotests.cloud/view/QA.GURU%20students/job/c36-oPalushina-qa_guru-booking/1/allure/)
<p align="center">
    <img title="Allure Overview" src="src/images/screenshot/Allure_Report.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/images/logo/allure_report.png"> Результат успешного прогона

<p align="center">
<img title="Allure Overview" src="src/images/screenshot/ResultsTest.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/images/logo/allure_testops.png"> Интеграция с Allure Test Ops
[Allure Test Ops отчет](https://allure.autotests.cloud/project/4948/dashboards)

<p align="center">
<a href="https://allure.autotests.cloud/project/4948/dashboards" target="_blank">
    <img title="" src="src/images/screenshot/allure_testops_dashboard.png">
</a>

</p>

### <img width="4%" style="vertical-align:middle" title="Telegram" src="src/images/logo/Telegram.png"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.

<p align="center">
<img width="70%" title="Telegram Notifications" src="src/images/screenshot/TelegramNotifications.png">
</p>

### <img width="4%" style="vertical-align:middle" title="Selenoid" src="src/images/logo/Selenoid.png"> Видео пример запуска тестов в Selenoid

К каждому тесту в отчете прилагается видео прогона.
<p align="center">
  <img title="Selenoid Video" src="src/images/screenshot/videoExample.gif">
</p>
