# Дипломный проект по профессии «Инженер по тестированию»

<br>

## Тестовая документация

- [План](Plan.md) по проверке и автоматизации приложения

- [Чек-лист](https://docs.google.com/spreadsheets/d/1gwGnyZVh6rRqZoU8o95Mk4TtcPhtuAA845quOgZ5OBw/edit?usp=sharing)

- [Тест-кейсы](https://docs.google.com/spreadsheets/d/1jJ__0WjFbDWaJfLiHcV2ml41SObo5J7Wye-qMkFPSrE/edit?usp=sharing)

- [Баг-репорты](https://github.com/doremifamiredo/SomovDiplomQA/issues)

- [allure-отчет](allure-results.zip)

- [Отчет о тестировании](Result.md)

<br>

-----

<br>

## Запуск авто-тестов и создание Allure-отчёта

### Требования

Перед запуском убедитесь, что установлено:

- **JDK 11**
- **Android Studio**
- Настроены переменные окружения:
  - `JAVA_HOME`
  - `ANDROID_HOME`
- Установлен Android SDK
- Запущен эмулятор (например, Pixel 3)
- Установлен Allure CLI  
  https://allurereport.org/docs/install/

Проверить установку можно командами:

```bash
java -version
allure --version
adb devices
```

### Клонирование и настройка проекта
- Склонируйте репозиторий проекта: `git clone https://github.com/doremifamiredo/SomovDiplomQA`
- Откройте проект в Android Studio и дождитесь завершения Gradle Sync

### Запуск авто-тестов
В корневой директории проекта выполните команду
```bash
./gradlew connectedDebugAndroidTest
```

### Экспорт результатов тестов
- После выполнения тестов откройте Device Explorer в Android Studio
- Перейдите в директорию `/data/data/ru.iteco.fmhandroid/files/allure-results`
- Сохраните папку `allure-result` в корневую директорию проекта
    * Перед новым запуском рекомендуется удалять старую папку allure-results

### Генерация Allure-отчёта
Находясь в корне проекта, выполните
```bash
   # Для быстрого просмотра
    allure serve allure-results

   # Для генерации HTML-отчёта:
    allure generate allure-results -o allure-report --clean
    allure open allure-report
```

## Архитектура автотестов
Проект построен по принципам:
- Page Object Pattern
- Разделение логики теста и UI-взаимодействий
- Использование степов Allure
- Повторное использование вспомогательных методов

### stack 
Java | Android | Espresso | Allure
## Сомов Олег Анатольевич
QA Automation Engineer
