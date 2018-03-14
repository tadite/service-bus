Для запуска проекта необходимо открыть cmd в корневой папке проекта (где содержится pom.xml) и выполнить команду
mvn clean install spring-boot:run.
Также можно выполнить эти команды через плагин maven в IDE.
Далее, запустите браузер и перейдите по адресу localhost:8090.

Описание эндпоинтов:

1) coast-of-living.
    Принимает в качестве параметров:
    - currency (валюта) - необязательный параметр;
    - city (город) - обязательный параметр.

    Пример: /coast-of-living?currency=RUB&city=Paris
            /coast-of-living?city=Paris

    Пример ответа:
    [
      {
        "category": "Restaurants",
        "fields": [
          {
            "title": "Meal, Inexpensive Restaurant",
            "price": "989.90",
            "price_range": {
              "from": "707.07",
              "to": "1,131.32"
            }
          },
          {
            "title": "Meal for 2 People, Mid-range Restaurant, Three-course",
            "price": "3,535.37",
            "price_range": {
              "from": "2,474.76",
              "to": "4,949.52"
            }
          },
          {
            "title": "McMeal at McDonalds (or Equivalent Combo Meal)",
            "price": "565.66",
            "price_range": {
              "from": "530.31",
              "to": "707.07"
            }
          },
          {
            "title": "Domestic Beer (0.5 liter draught)",
            "price": "424.24",
            "price_range": {
              "from": "353.54",
              "to": "494.95"
            }
          },
          {
            "title": "Imported Beer (0.33 liter bottle)",
            "price": "424.24",
            "price_range": {
              "from": "318.18",
              "to": "494.95"
            }
          },
          {
            "title": "Cappuccino (regular)",
            "price": "236.98",
            "price_range": {
              "from": "141.41",
              "to": "353.54"
            }
          },
          {
            "title": "Coke/Pepsi (0.33 liter bottle)",
            "price": "209.35",
            "price_range": {
              "from": "141.41",
              "to": "318.18"
            }
          },
          {
            "title": "Water (0.33 liter bottle)",
            "price": "135.08",
            "price_range": {
              "from": "70.71",
              "to": "212.12"
            }
          }
        ]
      },
      ...
    ]

2) tripster-excursion.
    Принимаетв качестве параметров:
    - city (город) - обязательный параметр;
    - type (тип экскурсии) - необязательный параметр. Можно получить в ответе при запросе tripster-excursion-types.

    Пример: /tripster-excursion?city=Tbilisi&type=284-obzorniye

    Пример ответа:
    [
      {
        "title": "Тбилиси – прогулка по Старому городу",
        "description": "Обзорная пешеходная экскурсия по улицам города",
        "price": "от € 18 за экскурсию",
        "time": "2.5 часа",
        "experience_id": "8695",
        "screen": "https://experience-ireland.s3.amazonaws.com/thumbs2/0ae5240e-77c3-11e7-8366-6e714efd800d.384x289.jpg"
      },
      {
        "title": "Как понять Тбилиси и получить от него удовольствие",
        "description": "Ежедневная экскурсия Трипстера для первого знакомства с городом",
        "price": "€ 18 за человека",
        "time": "3 часа",
        "experience_id": "8843",
        "screen": "https://experience-ireland.s3.amazonaws.com/thumbs2/29aa6afe-963f-11e6-8308-6e714efd800d.384x289.jpg"
      }
    ]

    tripster-excursion-types.
    Принимает в качестве параметров:
    - city (город) - обязательный параметр.

    Пример: /tripster-excursion-types?city=Paris

    Пример ответа:
    [
      {
        "title": "История и архитектура",
        "type": "25-istoriya-i-arhitektura"
      },
      {
        "title": "Необычные маршруты",
        "type": "193-neobyichnyie-marshrutyi"
      },
      {
        "title": "Обзорные экскурсии",
        "type": "310-obzornyie"
      },
      {
        "title": "Музеи и искусство",
        "type": "23-muzei-i-iskusstvo"
      },
      {
        "title": "Поездки за город",
        "type": "194-za-gorodom"
      },
      {
        "title": "Местная кухня",
        "type": "16-gastronomicheskie"
      }
    ]

3) tripster-excursion-details. Возвращает детальную информацию по выбранной экскурсии.
    Принимает в качестве параметров:
    - experience_id - обязательный параметр. Передается для каждой экскурсии в ответе на предыдущий запрос.

    Пример: /tripster-excursion-details?experience_id=8843

    Пример ответа:
    {
      "name": "Как понять Тбилиси и получить от него удовольствие",
      "description": "Ежедневная экскурсия Трипстера для первого знакомства с городом",
      "content": "Мы покажем вам Тбилиси, который мечтает увидеть любой путешественник – атмосферный город, в котором живут яркие и радостные люди. Вы узнаете, кто такие грузины, увидите настоящую городскую жизнь, познакомитесь с грузинскими традициям и побываете в самых колоритных местах старого города.",
      "guide": {
        "photo": "https://experience-ireland.s3.amazonaws.com/avatar/80272966-e213-11e5-a27d-f6ff22eb4f58.150x150.jpg",
        "name": "Tripster"
      },
      "info": {
        "description": "Групповая экскурсия",
        "time": "3 часа",
        "child": "До 12 человек",
        "rate": "4,9",
        "price": "€ 18 за человека",
        "to_order": "https://experience.tripster.ru/experience/booking/8843/"
      }
    }

4) weatlas-get-excursions.
    Принимает в качестве параметров:
    - country (страна) - обязательный параметр;
    - city (город) - обязательный параметр.

    Пример: /weatlas-get-excursions?country=Italy&city=Roma

    Пример ответа:
    [
      {
        "title": "Ежедневная обзорная экскурсия по Риму",
        "type": "Пешая",
        "time": "2 часа",
        "price": "20€ за человека",
        "excursion_id": "11203"
      },
      {
        "title": "Прогулка по Древнему Риму",
        "type": "Пешая",
        "time": "2,5 часа",
        "price": "25€ за человека",
        "excursion_id": "11188"
      },
      ...
    ]

5) weatlas-get-excursion-details. Возвращает детальную информацию по выбранной экскурсии.
    Принимает в качестве параметров:
    - excursion_id - обязательный параметр. Передается для каждой экскурсии в ответе на предыдущий запрос.

    Пример: /weatlas-get-excursion-details?excursion_id=11632

6) sea-cruises. Возвращает информацию о морских круизах.
    Принимает в качестве параметров:
    - data (желаемая дата круиза) - обязательный параметр;
    - min_price (минимальная стоимость билета) - обязательный параметр;
    - max_price (максимальная стоимость билета) - обязательный параметр;
    - region_id (id региона) - обязательный параметр. Содержится в файле sea-regions.json.

    Пример: /sea-cruises?date=15.04.2018&min_price=1000&max_price=200000&region_id=343

    Пример ответа:
    {
      "cruises": [
        {
            "company_img": "https://www.mcruises.ru/static/img/company/logo_svg/CS.svg",
            "title": "Круиз из порта Ираклион, о. Крит, Греция в порт Ираклион, о. Крит, Греция",
            "info": "Короткие круизы / Европа Южная, 4 ночи",
            "ship": "Celestial Majesty",
            "origin_date": "2018 год: апр 15, 22",
            "price_from": {
              "USD": "677",
              "EUR": "549",
              "RUB": "39 672"
            },
            "ports": [
              "Ираклион,  о. Крит",
              "о. Санторини,  Греция",
              "Афины (Пирей),  Греция",
              "о. Миконос,  Греция",
              "о. Самос,  Греция",
              "о.Патмос,  Греция",
              "о. Родос,  Греция",
              "Ираклион,  о. Крит"
            ]
        },
        ...
      ]
    }

7) river-cruises. Возвращает информацию о морских круизах.
       Принимает в качестве параметров:
       - data (желаемая дата круиза) - обязательный параметр;
       - min_price (минимальная стоимость билета) - обязательный параметр;
       - max_price (максимальная стоимость билета) - обязательный параметр;
       - region_id (id региона) - обязательный параметр. Содержится в файле river-regions.json.

       Пример: /river-cruises?date=20.04.2018&min_price=1000&max_price=100000&region_id=556

       Пример ответа:
       {
         "cruises": [
            {
              "company_img": "https://www.mcruises.ru/static/img/company/logo_svg/A1.svg",
              "title": "Круиз из порта Майнц, Германия в порт Вена, Австрия",
              "info": "Дунай  (Европа), 9 ночей",
              "ship": "Bellejour",
              "origin_date": "2018 год: май 09 сен 15",
              "price_from": {
                "USD": "1 660",
                "EUR": "1 347",
                "RUB": "97 336"
              },
              "ports": [
                "Майнц,  Германия",
                "Мильтенберг",
                "Вюрцбург",
                "Бамберг",
                "Нюрнберг",
                "Регенсбург",
                "Пассау,  Германия",
                "Линц",
                "Братислава,  Словакия",
                "Вена,  Австрия",
                "Вена,  Австрия"
              ]
            },
         ...
         ]
       }

8) rentalcars-find-cars. Прокат автомобилей.
    Принимает в качестве параметров:
    - country (страна) - обязательный параметр;
    - city (город) - обязательный параметр;
    - location_code (код места) - обязательный параметр. Будет получен в ответе на запрос rentalcars-city-meta;
    - location_name (название места) - обязательный параметр. Будет получен в ответе на запрос rentalcars-city-meta;
    - doYear (год конца проката) - обязательный параметр;
    - doMinute (время конца проката - до минуты) - обязательный параметр;
    - puYear (год начала проката) - обязательный параметр;
    - puMinute (время начала проката - с минуты) - обязательный параметр;
    - doDay (день конца проката) - обязательный параметр;
    - puMonth (месяц начала проката) - обязательный параметр;
    - doHour (время конца проката - до часа) - обязательный параметр;
    - puDay (день начала проката) - обязательный параметр;
    - puHour (время начала проката - с часа) - обязательный параметр;
    - doMonth (месяц конца проката) - обязательный параметр.

    Пример: /rentalcars-find-cars?country=Австрия&city=Вена&location_name=Вена+аэропорт&location_code=252129&doYear=2018&doMinute=00&puYear=2018&puMinute=00&doDay=10&puMonth=3&doHour=18&puDay=10&puHour=10&doMonth=3

    Пример ответа:
    {
      "cars": [
        {
          "image": "https://cdn.rcstatic.com/images/car_images/new_images/skoda/citigo_lrg.jpg",
          "name": "Skoda Citigo  или похожий",
          "price-period": "Цена за 1 день:",
          "price": "3310.12",
          "seats": "4 Сиденья",
          "doors": "2 Двери",
          "big-bag": "-",
          "small-bag": "2 Маленькие сумки",
          "climate": "С кондиционером",
          "transmission": "Механическая трансмиссия",
          "fuel-condition": "Возврат и получение с полным баком",
          "class": "Мини",
          "mileage": "4 000 km за аренду",
          "place": "Car Rental Centre",
          "rating": "8,4/ 10",
          "company": {
            "icon": "https://cdn.rcstatic.com/images/suppliers/flat/alamo_logo.gif",
            "name": "Alamo"
          }
        },
        ...
      ]
    }

    Для получения параметров location_code и location_name необходимо обратиться по ссылке на rentalcars-city-meta.
    Принимает в качестве параметров:
    - country (страна) - обязательный параметр;
    - city (город) - обязательный параметр.

    Пример: /rentalcars-city-meta?country=Австрия&city=Вена

    Пример ответа:
    [
      {
        "iata":"",
        "name":"Вена аэропорт",
        "id":"252129",
        "type":"airport"
      },
      {
        "iata":"",
        "name":"Вена - Erdbergstrasse",
        "id":"3237261",
        "type":"other"
      },
      ...
    ]

9) hotellook-find-hotels.
    Принимает в качестве параметров:
    - location — обязательный параметр. Имя локации (может использоваться IATA код локации);
    - checkIn — обязательный параметр. Дата заселения;
    - checkOut — обязательный параметр. Дата выселения;
    - adults — количество гостей (по умолчанию 2);
    - children — число детей (возраст от 2 до 18 лет);
    - infants — число младенцев (возраст от 0 до 2 лет);
    - limit — количество отелей. Если данный параметр используется в запросе без указания точного id или названия отеля, то действует следующее правило:
        - limit = 4 (значение по умолчанию) — вернется по одному отелю каждой категории (звездности);
        - limit = 5 — вернется два пятизвездочных отеля и по одному других категорий;
        - limit = 6 — по два 5-ти и 4-х звездочных отеля и остальные по одному;
        - limit = 7 — по два 5, 4 и 3-х звездочных отеля и один двухзвездочный;
        - limit = 8 — всех по два. И так далее, с ростом параметра по очереди увеличивается количество отелей каждой звездности. Если отелей указанной звездности больше нет, то в выдачу начнут попадать отели 1 и 0 звездности по такому же правилу.
    - customerIp — параметр используется для указания ip пользователя, если запрос отправляется не напрямую, а через какое-либо серверное проксирование.
    - currency — валюта ответа;
    - lang - язык вывода.

    Пример: /hotellook-find-hotels?lang=ru_RU&currency=RUB&location=MOW&checkIn=2018-04-10&checkOut=2018-04-16&adults=1&limit=10

    Пример ответа:
    {
        "location": {
            "country": "Russia",
            "geo": {
                "lon": 37.617508,
                "lat": 55.752041
            },
            "state": null,
            "name": "Moscow"
        },
        "priceAvg": 60897.74,
        "pricePercentile": {
            "3": 28863.56,
            "10": 28863.56,
            "35": 47805.27,
            "50": 59531.09,
            "75": 65435,
            "99": 120128.17
        },
        "hotelName": "Mercure Arbat Moscow",
        "stars": 4,
        "locationId": 12153,
        "hotelId": 333561,
        "priceFrom": 28863.56
    }

    Описание параметров:
    - stars — количество звезд;
    - locationId — id локации данного отеля;
    - priceFrom — минимальная цена за проживание в номере отеля за указанный период;
    - priceAvg — средняя цена за проживание в номере за указанный период;
    - pricePercentile — распределение цен по долям (например, запись вида «50»:59531.09 означает, что 50% цен находится в диапазоне до 59531.09 руб.)
    - hotelName — название отеля;
    - location — информация о локации отеля:
        - geo — координаты отеля;
        - name — название локации (города);
        - state — штат, в котором расположен город;
        - country — страна отеля.
    - hotelId — id отеля.

10) air-tickets-search-one-way. Поиск авиабилетов в одну сторону.
    Принимает в качестве параметров:
    - session_id - обязательный параметр. Возвращается в результате запроса по air-tickets-get-id-one-way.

    Пример: /air-tickets-search-one-way?session_id=2b1dee09ac9ab55892679ba9d173745e

    Пример ответа:
    {
      "result": {
        "flights": [
          {
            "flight": {
              "company": {
                "name": "Air Baltic",
                "icon": "https://static.tickets.ru/img/logos/BT.png?aa202f7c6136d6279e49760f6866cf3278d66833",
                "rating": "129 место в рейтинге авиакомпаний"
              },
              "flight_no": "BT-654",
              "aircraft": "Bombardier CS300",
              "class": "Эконом класс",
              "departure_time": "17:25",
              "departure_date": "20 апреля 2018, Пятница",
              "departure": {
                "place_code": "LGW",
                "place_name": "Гатвик (Лондон, Великобритания)"
              },
              "time_in_path": "13 ч 35 мин",
              "transfer": {
                "place_code": "RIX",
                "place_name": "Рига (Рига, Латвия)",
                "transfer_time": "Время пересадки: 9 ч 20 мин",
                "arrival_date": "Прилёт: 20 апреля 2018, 22:05",
                "departure_date": "Вылет: 21 апреля 2018, 07:25"
              },
              "arrival_time": "09:00",
              "arrival_date": "21 апреля 2018, Суббота",
              "arrival": {
                "place_code": "RIX",
                "place_name": "Рига (Рига, Латвия)"
              },
              "price": {
                "RUB": "16 812 RUR",
                "USD": "295 USD",
                "EUR": "238 EUR"
              }
            }
          },
          ...
        ]
      }
    }

    air-tickets-get-id-one-way.
    Принимает в качестве параметров:
    - origin_city (город отправления) - обязательный параметр;
    - origin_code (код города, например, LON) - обязательный параметр;
    - dest_city (город прибытия) - обязательный параметр;
    - dest_code (код города) - обязательный параметр;
    - origin_date (дата отправления) - обязательный параметр;
    - class - обязательный параметр.
        - E - эконом класс;
        - B - бизнес класс;
    - adults (количество взрослых людей) - обязательный параметр;
    - childrens (количество детей от 2 до 12 лет) - необязательный параметр;
    - infants (количество детей от 0 до 2 лет) - необязательный параметр.

    Пример: /air-tickets-get-id-one-way?origin_city=Москва&origin_code=MOW&dest_city=Лондон&dest_code=LON&origin_date=20.04.2018&class=E&adults=1&childrens=1&infants=1

    Пример ответа:
    {
      "params": {
        "session_id": "cddd43179f3ba2fb6ef44dcd72fd618d"
      },
      "code": false,
      "count": 77,
      "action": "results"
    }

11) air-tickets-search. Поиск авиабилетов с возвратом.
    Принимает в качестве параметров:
    - session_id - обязательный параметр. Возвращается в результате запроса по air-tickets-get-id.

    Пример: /air-tickets-search?session_id=ceadb65552e9671c21bfa3816f8b2e2f

    Пример ответа:
    {
      "result": {
          "flights": [
            {
              "flight_to": {
                "company": {
                  "name": "Lufthansa",
                  "icon": "https://static.tickets.ru/img/logos/LH.png?aa202f7c6136d6279e49760f6866cf3278d66833",
                  "rating": "17 место в рейтинге авиакомпаний"
                },
                "flight_no": "LH-909",
                "class": "Бизнес-класс",
                "aircraft": "Airbus Industrie A320 (Sharklets)",
                "departure_time": "15:30",
                "departure_date": "15 апреля 2018, Воскресенье",
                "departure": {
                  "place_code": "LHR",
                  "place_name": "Хитроу (Лондон, Великобритания)"
                },
                "time_in_path": "5 ч 40 мин",
                "transfer": {
                  "place_code": "FRA",
                  "place_name": "Франкфурт Интернешнл (Франкфурт-на-Майне, Германия)",
                  "transfer_time": "Время пересадки: 0 ч 55 мин",
                  "arrival_date": "Прилёт: 15 апреля 2018, 18:05",
                  "departure_date": "Вылет: 15 апреля 2018, 19:00"
                },
                "arrival_time": "23:10",
                "arrival_date": "15 апреля 2018, Воскресенье",
                "arrival": {
                  "place_code": "DME",
                  "place_name": "Домодедово (Москва, Россия)"
                }
              },
              "flight_from": {
                "company": {
                  "name": "Lufthansa",
                  "icon": "https://static.tickets.ru/img/logos/LH.png?aa202f7c6136d6279e49760f6866cf3278d66833",
                  "rating": "17 место в рейтинге авиакомпаний"
                },
                "flight_no": "LH-2529",
                "aircraft": "Airbus A320-100/200",
                "class": "Бизнес-класс",
                "departure_time": "16:10",
                "departure_date": "22 апреля 2018, Воскресенье",
                "departure": {
                  "place_code": "DME",
                  "place_name": "Домодедово (Москва, Россия)"
                },
                "time_in_path": "6 ч 15 мин",
                "transfer": {
                  "place_code": "MUC",
                  "place_name": "Международный аэропорт Мюнхена  (Мюнхен, Германия)",
                  "transfer_time": "Время пересадки: 1 ч 00 мин",
                  "arrival_date": "Прилёт: 22 апреля 2018, 18:20",
                  "departure_date": "Вылет: 22 апреля 2018, 19:20"
                },
                "arrival_time": "20:25",
                "arrival_date": "22 апреля 2018, Воскресенье",
                "arrival": {
                  "place_code": "LHR",
                  "place_name": "Хитроу (Лондон, Великобритания)"
                }
              },
              "price": {
                "RUB": "27 072 RUR",
                "USD": "475 USD",
                "EUR": "384 EUR"
              }
            },
            ...
          ]
      }
    }


    air-tickets-get-id.
        Принимает в качестве параметров:
        - origin_city (город отправления) - обязательный параметр;
        - origin_code (код города, например, LON) - обязательный параметр;
        - dest_city (город прибытия) - обязательный параметр;
        - dest_code (код города) - обязательный параметр;
        - origin_date (дата отправления) - обязательный параметр;
        - return_date (дата возвращения) - обязательный параметр;
        - class - обязательный параметр.
            - E - эконом класс;
            - B - бизнес класс;
        - adults (количество взрослых людей) - обязательный параметр;
        - childrens (количество детей от 2 до 12 лет) - необязательный параметр;
        - infants (количество детей от 0 до 2 лет) - необязательный параметр.

    Пример: /air-tickets-get-id?origin_city=Лондон&origin_code=LON&origin_date=15.04.2018&return_date=22.04.2018&class=B&adults=1&dest_city=Москва&dest_code=MOW

    Пример ответа:
    {
      "params": {
        "session_id": "ad77c7d40fcf68d6f6a64d9ce8fab1b6"
      },
      "code": false,
      "count": 155,
      "action": "results"
    }

Продолжение следует...