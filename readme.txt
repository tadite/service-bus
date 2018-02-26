Description will be here

Для запуска проекта необходимо открыть cmd в корневой папке проекта (где содержится pom.xml) и выполнить команду
mvn clean install spring-boot:run.
Также можно выполнить эти команды через плагин maven в IDE.
Далее, запустите браузер и перейдите по адресу localhost:8080.

Описание эндпоинтов:

1) coast-of-living.
    Принимает в качестве параметров:
    - currency (валюта) - необязательный параметр;
    - city (город) - обязательный параметр.

    Пример: /coast-of-living?currency=RUB&city=Paris
            /coast-of-living?city=Paris

2) tripster-excursion.
    Принимаетв качестве параметров:
    - city (город) - обязательный параметр;
    - type (тип экскурсии) - необязательный параметр.

    Пример: /tripster-excursion?city=Tbilisi&type=284-obzorniye

3) tripster-excursion-details. Возвращает детальную информацию по выбранной экскурсии.
    Принимает в качестве параметров:
    - experience_id - обязательный параметр. Передается для каждой экскурсии в ответе на предыдущий запрос.

    Пример: /tripster-excursion-details?experience_id=8843

4) weatlas-get-excursions.
    Принимает в качестве параметров:
    - country (страна) - обязательный параметр;
    - city (город) - обязательный параметр.

    Пример: /weatlas-get-excursions?country=Italy&city=Roma

5) weatlas-get-excursion-details. Возвращает детальную информацию по выбранной экскурсии.
    Принимает в качестве параметров:
    - excursion_id - обязательный параметр. Передается для каждой экскурсии в ответе на предыдущий запрос.

    Пример: /weatlas-get-excursion-details?excursion_id=11632

Продолжение следует...