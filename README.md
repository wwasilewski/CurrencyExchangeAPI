
# Exchange rates REST API

This API supports latest and historical currency exchange rates and gold prices.


## API Reference examples

### Get the latest rate for base and target currency:

```http
  GET /api/currency/latest/{base}/{target}
```

| Parameter | Type     | Description                                 |
|:----------|:---------|:--------------------------------------------|
| `base`    | `string` | **Required**. symbol of the base currency   |
| `target`  | `string` | **Required**. symbol of the target currency |

#### Sample output:
```json
{
  "rate": 0.927562,
  "date": "2022-04-18"
}
```


### Get rate for base and target currency for specific date:

```http
  GET /api/currency/history/{base}/{target}/{date}
```

| Parameter | Type     | Description                                       |
|:----------|:---------|:--------------------------------------------------|
| `base`    | `string` | **Required**. symbol of the base currency         |
| `target`  | `string` | **Required**. symbol of the target currency       |
| `date`    | `string` | **Required**. requested date in format YYYY-MM-DD |

#### Sample output:
```json
{
  "rate": 0.926012,
  "date": "2016-01-25"
}
```


### Get the latest price of gold in polish zloty PLN:

```http
  GET /api/gold/latest
```

#### Sample output:
```json
{
  "rate": 2639.124429,
  "date": "2022-04-18"
}
```


### Get price of gold in polish zloty PLN for specific day:

```http
  GET /api/gold/history/{date}
```

| Parameter | Type     | Description                                       |
|:----------|:---------|:--------------------------------------------------|
| `date`    | `string` | **Required**. requested date in format YYYY-MM-DD |

#### Sample output:
```json
{
  "rate": 3329.499305,
  "date": "2022-03-12"
}
```


## STATISTICS

### Get rates for base and target currency for previous period:

```http
  GET /api/stats/{base}/{target}/{dateFrom}/{dateTo}
```

| Parameter  | Type     | Description                                       |
|:-----------|:---------|:--------------------------------------------------|
| `base`     | `string` | **Required**. symbol of the base currency         |
| `target`   | `string` | **Required**. symbol of the target currency       |
| `dateFrom` | `string` | **Required**. requested date in format YYYY-MM-DD |
| `dateTo`   | `string` | **Required**. requested date in format YYYY-MM-DD |

#### Sample output:
```json
{
  "base": "USD",
  "rates": {
    "2022-03-14": {
      "PLN": 4.323084
    },
    "2022-03-15": {
      "PLN": 4.296354
    },
    "2022-03-16": {
      "PLN": 4.232135
    }
  }
}
```

### Get specific currency records count from the database: 

```http
  GET /api/stats/currency/{base}
```

| Parameter | Type     | Description                               |
|:----------|:---------|:------------------------------------------|
| `base`    | `string` | **Required**. symbol of the base currency |

#### Sample output:
```json
5
```

### Get all records count from the database:

```http
  GET /api/stats/db-all
```

#### Sample output:
```json
34
```

### Get gold records count from the database:

```http
  GET /api/stats/db-gold
```

#### Sample output:
```json
3
```


## Tech Stack

**Technologies:** Java, Spring Boot, H2




## Authors

- [@Wojciech Wasilewski](https://github.com/wwasilewski)
- [@Katarzyna Sawicka](https://github.com/muskLisek)

