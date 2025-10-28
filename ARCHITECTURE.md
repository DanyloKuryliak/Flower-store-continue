# Architecture Documentation

## Web Application Layers

### Understanding the Three-Tier Architecture

Most web applications follow a three-tier architecture pattern:

#### 1. API Layer (Presentation Layer / Controller Layer)
- **Purpose**: Handles HTTP requests and responses
- **Responsibilities**:
  - Receives client requests
  - Validates input data
  - Delegates business logic to service layer
  - Formats and returns HTTP responses
- **In this project**: Located in `src/main/java/com/example/demo/flower/store/api/`

#### 2. Service Layer (Business Logic Layer)
- **Purpose**: Contains business logic and coordinates operations
- **Responsibilities**:
  - Implements business rules
  - Coordinates between different components
  - Acts as a bridge between API and data layers
  - Transaction management
- **In this project**: Located in `src/main/java/com/example/demo/flower/store/service/`

#### 3. Data Access Layer (Persistence Layer)
- **Purpose**: Manages data storage and retrieval
- **Responsibilities**:
  - Repository operations
  - Data persistence
  - Database interactions
- **Note**: In this project, we use in-memory storage. In production, this would typically connect to a database.

## What is Spring Boot?

Spring Boot is a framework built on top of the Spring Framework that simplifies Java application development:

### Key Features:
1. **Auto-configuration**: Automatically configures Spring based on dependencies
2. **Starter dependencies**: Pre-configured dependencies for common use cases
3. **Embedded server**: Runs as a standalone application (no need for external server)
4. **Production-ready**: Built-in health checks, metrics, and monitoring

### Spring Boot Initializr
The project was created using [Spring Initializr](https://start.spring.io/):
- Selected Java 21
- Added Spring Web starter
- Generated Maven project structure

## Implementation Summary

### ✅ Completed Requirements:

1. **Layered Architecture**
   - API Layer with REST controllers
   - Service Layer with business logic
   - Domain Layer with models

2. **RESTful API**
   - `@RestController` annotations
   - `@RequestMapping` for base paths
   - `@GetMapping` and `@PostMapping` for endpoints

3. **Flower Endpoint**
   - `/api/flowers` returns list of flowers
   - Moved to proper package structure

4. **Order Implementation**
   - `Order` class manages client orders
   - Contains list of items
   - Can calculate total price
   - Processes orders with payment and delivery

5. **Item Implementation**
   - Abstract base class for order elements
   - Flower extends Item
   - Supports polymorphism

6. **Payment Strategies (Strategy Pattern)**
   - `Payment` interface
   - `CreditCardPaymentStrategy` - Credit card implementation
   - `PayPalPaymentStrategy` - PayPal implementation
   - Flexible payment method switching

7. **Delivery Strategies (Strategy Pattern)**
   - `Delivery` interface
   - `PostDeliveryStrategy` - Standard postal delivery
   - `DHLDeliveryStrategy` - DHL express delivery
   - Flexible delivery method switching

8. **Comprehensive Testing**
   - Payment strategy tests (4 tests)
   - Delivery strategy tests (5 tests)
   - Order tests (7 tests)
   - Application context tests (1 test)
   - **Total: 17 tests, all passing**

9. **API Endpoints for Payment and Delivery**
   - `/api/payment/creditcard`
   - `/api/payment/paypal`
   - `/api/delivery/post`
   - `/api/delivery/dhl`

10. **GitHub Actions CI/CD**
    - Automated test runner
    - Runs on push and pull requests
    - Java 21 with Maven
    - Config file: `.github/workflows/ci.yml`

## Project Structure

```
src/main/java/com/example/demo/flower/store/
├── api/                           # API Layer
│   ├── FlowerController.java     # Flower endpoints
│   ├── OrderController.java       # Order management
│   ├── PaymentController.java    # Payment processing
│   └── DeliveryController.java   # Delivery processing
├── service/                       # Service Layer
│   └── OrderService.java          # Order business logic
├── payment/                       # Payment Strategy Pattern
│   ├── Payment.java               # Interface
│   ├── CreditCardPaymentStrategy.java
│   └── PayPalPaymentStrategy.java
├── delivery/                      # Delivery Strategy Pattern
│   ├── Delivery.java              # Interface
│   ├── PostDeliveryStrategy.java
│   └── DHLDeliveryStrategy.java
└── [domain models]                # Domain Layer
    ├── Flower.java
    ├── Order.java
    ├── Item.java
    ├── FlowerBucket.java
    └── FlowerPack.java
```

## Design Patterns Used

### Strategy Pattern
Allows defining a family of algorithms, encapsulating each one, and making them interchangeable:

**Payment Strategy:**
- Interface: `Payment`
- Implementations: `CreditCardPaymentStrategy`, `PayPalPaymentStrategy`
- Allows runtime switching of payment methods

**Delivery Strategy:**
- Interface: `Delivery`
- Implementations: `PostDeliveryStrategy`, `DHLDeliveryStrategy`
- Allows runtime switching of delivery methods

### Benefits:
- Open/Closed Principle: Easy to add new strategies
- Single Responsibility: Each strategy has one job
- Dependency Inversion: Depends on abstractions (interfaces)

## Testing Strategy

### Unit Tests
- Payment strategies tested independently
- Delivery strategies tested independently
- Order operations tested with mocked dependencies

### Integration Tests
- Spring Boot context loads correctly
- All components work together

### Test Coverage
All critical paths are covered:
- Payment processing with different methods
- Delivery processing with different methods
- Order creation and processing
- Error handling (missing payment/delivery strategies)

## API Usage Examples

### Get all flowers
```bash
curl http://localhost:8080/api/flowers
```

### Create an order with demo data
```bash
curl -X POST http://localhost:8080/api/orders/demo \
  -H "Content-Type: application/json" \
  -d '{
    "paymentType": "paypal",
    "deliveryType": "dhl",
    "address": "123 Main St",
    "email": "user@example.com"
  }'
```

### Process credit card payment
```bash
curl -X POST http://localhost:8080/api/payment/creditcard \
  -H "Content-Type: application/json" \
  -d '{
    "cardNumber": "1234567890123456",
    "amount": "100.00"
  }'
```

## Running the Application

```bash
# Start the application
./mvnw spring-boot:run

# Run tests
./mvnw test

# Run with tests and check coverage
./mvnw clean test
```

The application will be available at `http://localhost:8080`

## Conclusion

This project demonstrates:
- ✅ Proper layered architecture (API, Service, Domain)
- ✅ RESTful API design
- ✅ Strategy pattern implementation
- ✅ Comprehensive test coverage
- ✅ CI/CD with GitHub Actions
- ✅ Spring Boot best practices
- ✅ SOLID principles (especially Strategy pattern)

All requirements have been successfully implemented.
