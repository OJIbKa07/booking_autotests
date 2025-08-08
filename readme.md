# Проект по автоматизации тестовых сценариев для сайта Booking.com
## :scroll: Содержание:

- [Используемый стек](#computer-используемый-стек)
- [Запуск автотестов](#arrow_forward-запуск-автотестов)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Результат успешного прогона](#-результат-успешного-прогона)
- [Уведомления в Telegram](#-уведомления-в-telegram-с-использованием-бота)
- [Видео примера запуска тестов в Selenoid](#-видео-примера-запуска-теста-в-selenoid)

## :computer: Используемый стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="src/images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="src/images/logo/Java.svg">
<img width="5%" title="Selenide" src="src/images/logo/Selenide.png">
<img width="6%" title="Selenoid" src="src/images/logo/Selenoid.png">
<img width="6%" title="Allure Report" src="src/images/logo/Allure.png">
<img width="6%" title="Gradle" src="src/images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="src/images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="src/images/logo/Github.svg">
<img width="6%" title="Jenkins" src="src/images/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="src/images/logo/Telegram.png">
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
./gradlew test
```
При выполнении данной команды в терминале IDE тесты запустятся удаленно в <code>Selenoid</code>.

### Установленная конфигурация

* Браузер - <code>chrome</code>;
* Версия браузера - <code>127.0</code>;
* Расширение окна браузера - <code>1920x1080</code>.


## <img width="4%" style="vertical-align:middle" title="Jenkins" src="src/images/logo/Jenkins.svg"> Сборка в Jenkins
<p align="center">
<img title="Jenkins Build" src="src/images/screenshot/jenkinsBuild.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/images/logo/Allure.png"> Пример Allure-отчета
### Overview

<p align="center">
<img title="Allure Overview" src="src/images/screenshot/Allure_Report.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/images/logo/ResultsTest.png"> Результат успешного прогона
### Overview

<p align="center">
<img title="Allure Overview" src="src/images/screenshot/ResultsTest.png">
</p>

### <img width="4%" style="vertical-align:middle" title="Telegram" src="src/images/logo/Telegram.png"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.

<p align="center">
<img width="70%" title="Telegram Notifications" src="src/images/screenshot/TelegramNotifications.png">
</p>

### <img width="4%" style="vertical-align:middle" title="Selenoid" src="src/images/logo/Selenoid.png"> Видео примера запуска тестов в Selenoid

К каждому тесту в отчете прилагается видео прогона.
<p align="center">
  <img title="Selenoid Video" src="src/images/screenshot/videoExample.gif">
</p>
