package tests;

import helpers.allure.annotations.Manual;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class ManualTests {
    @Test
    @Manual
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск отеля по городу на booking.com")
    public void searchHotelByCityTest() {
        step("Открываем сайт booking.com");
        step("Вводим в строку поиска название города (например, 'Paris')");
        step("Выбираем даты заезда и выезда");
        step("Указываем количество гостей и номера");
        step("Нажимаем кнопку 'Search'");
        step("Проверяем, что отображаются результаты поиска по указанному городу");
    }

    @Test
    @Manual
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Фильтрация отелей по рейтингу")
    public void filterHotelsByRatingTest() {
        step("Открываем booking.com и выполняем поиск отелей по городу");
        step("В панели фильтров выбираем рейтинг 8+ или аналогичный");
        step("Применяем фильтр");
        step("Проверяем, что все отображаемые отели имеют рейтинг выше выбранного значения");
    }

    @Test
    @Manual
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Просмотр карточки конкретного отеля")
    public void openHotelDetailsTest() {
        step("Выполняем поиск отелей по городу");
        step("Кликаем на название любого отеля из списка");
        step("Проверяем, что открылась страница отеля с фотографиями, описанием и доступными номерами");
    }

    @Test
    @Manual
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Добавление отеля в избранное (Save to Wishlist)")
    public void addHotelToWishlistTest() {
        step("Авторизуемся на сайте booking.com");
        step("Выполняем поиск отелей");
        step("Нажимаем на иконку сердца у любого отеля");
        step("Проверяем появление уведомления о сохранении в избранное");
        step("Переходим в раздел 'Wishlist' и убеждаемся, что отель появился в списке сохраненных");
    }

    @Test
    @Manual
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Бронирование номера без оплаты (Free Cancellation)")
    public void bookingWithFreeCancellationTest() {
        step("Выполняем поиск отелей по городу");
        step("Выбираем фильтр 'Free cancellation'");
        step("Открываем карточку отеля с поддержкой бесплатной отмены");
        step("Нажимаем 'Reserve' или аналогичную кнопку");
        step("Заполняем данные гостя");
        step("Проверяем, что отображается отметка о бесплатной отмене бронирования");
        step("Подтверждаем бронь и проверяем, что появляется страница с деталями бронирования");
    }

    @Test
    @Manual
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Изменение языка интерфейса")
    public void changeLanguageTest() {
        step("Открываем booking.com");
        step("В шапке сайта нажимаем на переключатель языка");
        step("Выбираем другой язык (например, English → Deutsch)");
        step("Проверяем, что интерфейс сайта переключился на выбранный язык");
    }

    @Test
    @Manual
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Оставить отзыв на забронированный отель")
    public void leaveReviewTest() {
        step("Авторизуемся на сайте booking.com");
        step("Переходим в раздел 'My Trips' или 'Bookings'");
        step("Выбираем завершённое бронирование");
        step("Нажимаем кнопку 'Leave a review'");
        step("Заполняем форму оценки и комментарий");
        step("Отправляем отзыв");
        step("Проверяем отображение сообщения об успешной отправке отзыва");
    }
}
