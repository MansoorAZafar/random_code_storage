# URL Shortener API
## Purpose
The purpose of this API is to take any URL, HTTP or HTTPS and shorten them into a user chosen alias or the first 8 characters of a generated hash.

## Technologies
- Unit Testing
- Spring Boot
- Docker Compose
- Redis
- PostgreSQL

## Setup

```bash
# 1. Clone the repository
git clone https://github.com/MansoorAZafar/url-shortener-api.git

# 2. CD into it
cd url-shortener-api/

# 3. Setup the .env
#  - based on the .env.example, make a .env file and
#    add keys and put the values
#  - the values in .env.example are for local testing

# 3.5 Run Unit Tests
./mvnw test

# 4. Run the Application
# - Docker must be open
./mvnw spring-boot:run
```

## API
### POST /url
Canonicalizes the URL by removing the trailing slashes and https:// or http://. Then adds the url to the database and optionally maps it to the alias. 
If no alias is provided, then maps it to a server side generated 8 character hash.
- Duplicate aliases and urls cannot exist, and the url is madatory. If a duplicate exists then success is false.
#### Body
```js
{
  "url": String, // The url as a string
  "alias": String // the custom name of the url 
}
```
#### Response
```js
{
  "status": boolean,
  "data": String, // This the path to the url provided
  "message": String // null if successful else a error string message
}
```


### GET /url/{id}
Returns a link redirect to the mapped url to the id from the POST request, otherwise it will return nothing other than a HttpStatus of Not Found.

#### Path Parameter
```js
`/url/${id}`
```
#### Response
```js
// it auto maps the response to the url
<!DOCTYPE HTML>
<html>
 ...
```


### DELETE /url/{id}
Deletes the mapped resource for the id given from POST. If it fails, success will be false.

#### Path Parameter
```js
`/url/${id}`
```
#### Response
```js
{
  "status": boolean,
  "data": null, // This the path to the url provided
  "message": String // null if successful else a error string message
}
```
