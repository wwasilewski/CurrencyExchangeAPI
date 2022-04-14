
# Exchange rates REST API

This API supports latest and historical currency exchange rates and gold prices.


## API Reference examples

#### Get latest rate for base and target currency

```http
  GET /api/currency/latest/{base}/{target}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `base`    | `string` | **Required**. symbol of the base currency |
| `target`  | `string` | **Required**. symbol of the target currency |


#### Get rate for base and target currency for specific date

```http
  GET /api/currency/history/{base}/{target}/{date}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `base`    | `string` | **Required**. symbol of the base currency |
| `target`  | `string` | **Required**. symbol of the target currency |
| `date`    | `string` | **Required**. requested date in format YYYY-MM-DD |


#### Get latest price of gold in polish zloty PLN

```http
  GET /api/gold/latest
```


#### Get price of gold in polish zloty PLN for specific day

```http
  GET /api/gold/history/{date}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `date`    | `string` | **Required**. requested date in format YYYY-MM-DD |




## Tech Stack

**Technologies:** Java, Spring Boot, H2




## Authors

- [@Wojciech Wasilewski](https://github.com/wwasilewski)
- [@Katarzyna Sawicka](https://github.com/muskLisek)

