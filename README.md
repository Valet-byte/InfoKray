# Info Kray backend server

Сервер для создания, поиска и участия вразличных мероприятиях / кружках / организациях.

## Методы
/api/registration для регистрации:

Отправляем:
```Json
{
	"name": "Romzes",
	"password":"111111",
	"email":"rsumakov20@gmail.com"
}
```
Возвращает:
```Json
{
	"id": 1,
	"name": "Romzes",
	"password": "NONE",
	"email": "rsumakov20@gmail.com"
}
```
/api/login для входа (нужен для фронта)

Метод авторизации: http basic 

в качестве имени пользователя исользуется email
Отправляем в headers: 
```text
Authorization : Basic cnN1bWFrb3YyMEBnbWFpbC5jb206MTExMTEx
```
Возвращает:
```json
{
	"id": 5,
	"name": "Romzes"
}
```
/api/addContent для добавления контента, требуется авторизация как написано выше.

Отправлем:
```json
{
	"name":"Test room",
	"body":"interest body",
	"images":[123, 321],
	"type":"EVENT"
}
```
Принимаем:
```json
{
	"id": 11,
	"name": "Test room",
	"body": "interest body",
	"images": [
		123,
		321
	],
	"type": "EVENT",
	"creatorId": 5
}
```
/api/getContent для получения контента. Требуется авторизация. 

Обязательный парпметр: search, строка, может быть пустой
Не обязательные параметры: page и size, числа

Отправляем:
http://my-host/api/getContent?search=image&page=0&size=5

Получаем:
```json
[
	{
		"id": 12,
		"name": "With image",
		"body": "interest body",
		"images": [
			"21745403737500gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg",
			"21745410702900gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg"
		],
		"type": "EVENT",
		"creatorId": 5
	},
	{
		"id": 13,
		"name": "With image",
		"body": "interest body",
		"images": [
			"23962854562800gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg",
			"23962859172700gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg"
		],
		"type": "EVENT",
		"creatorId": 5
	},
	{
		"id": 16,
		"name": "Content with image",
		"body": "interest body",
		"images": [
			"24016932356700gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg",
			"24016934117900gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg"
		],
		"type": "EVENT",
		"creatorId": 5
	},
	{
		"id": 17,
		"name": "very interesting images",
		"body": "interest body",
		"images": [
			"24055838187300gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg",
			"24055843673700gorod-franciya-parizh-ejfeleva-bashnya-arxitektura-pod-ejfelevoj.jpg"
		],
		"type": "EVENT",
		"creatorId": 5
	}
]
```

/api/getImage для получения кртинок. Требуется авторизация.

Обязательный парпметр: fileName, строка

Отправляем:
http://my-host/api/getImage?fileName=test.jpg

Получаем:
```text
Кратинку
```

/api/addContentWithFiles для добавления контента и загрузки картинок. Требуется авторизация.

Обязательный парпметр: search, строка, может быть пустой
Не обязательные параметры: page и size, числа

Отправляем:
Тело запроса Multipart

Файлы: files.

Json контента: content

Получаем:
```json
{
  "id": 1,
  "name": "very interesting images",
  "body": "interest body",
  "images": [
    "240558381873001.jpg",
    "240558436737002.jpg"
  ],
  "type": "EVENT",
  "creatorId": 5
}
```

/api/getPersonById для получения краткой информации о пользователе. Требуется авторизация.

Отправляем: в query параметр id

Получаем: 
```json
{
	"id": 1,
	"name": "Valet-byte"
}
```