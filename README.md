# Online Bookstore
## The application has the same business logic as the project https://github.com/TeodoraNencheva/online-bookstore-backend but instead of exposing REST API, it uses Thymeleaf for Server Side Rendering.
## Both applications have identical database, maintenance interceptor, schedular, Cloudinary integration and business logic.

## Differences
### 1. Authentication
#### 1.1. as regular user(customer): username: user@example.com or user2@example.com and password: 1234
#### 1.2. as admin: username: admin@example.com and password: 1234

### 2. OAuth2 Login - the application support login via Facebook and Google account

### 3. Exception handling
* via @ControllerAdvice, @ExceptionHandler and error.html, 403.html and 500.html error templates
