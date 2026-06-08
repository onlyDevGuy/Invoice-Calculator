# 💰 Invoice Calculator (Java Financial Calculation Project)

## Overview

The Invoice Calculator is a Java-based financial calculation component designed for retail billing and invoice processing. The system performs accurate monetary calculations using Java's `BigDecimal` class to eliminate floating-point precision errors commonly encountered in financial applications.

The calculator supports discount calculations, VAT application, final invoice generation, and input validation, making it suitable for retail, e-commerce, and billing systems.

---

## Features

### Financial Calculations
- Calculate discounted amounts
- Apply VAT (15%)
- Generate final invoice totals
- Automatic currency rounding

### Data Validation
- Prevent negative amounts
- Validate discount percentages
- Exception handling for invalid inputs

### Precision Handling
- Uses BigDecimal for accurate calculations
- Eliminates floating-point rounding errors
- Financial-grade arithmetic operations

---

## Technologies Used

- Java
- BigDecimal API
- Object-Oriented Programming (OOP)
- Exception Handling
- Financial Calculations

---

## Project Structure

```text
Invoice Calculator Project
│
├── CalculatorInvoice.java
│   └── Application Entry Point
│
└── InvoiceCalculator.java
    ├── Discount Calculations
    ├── VAT Calculations
    ├── Final Invoice Calculations
    ├── Data Validation
    └── Financial Logic
```

---

## Business Rules

### VAT Rate

```text
VAT = 15%
```

### Discount Rules

| Condition | Rule |
|------------|--------|
| Minimum Discount | 0% |
| Maximum Discount | 100% |
| Negative Discount | Not Allowed |

### Amount Rules

| Condition | Result |
|------------|----------|
| Positive Amount | Accepted |
| Zero Amount | Accepted |
| Negative Amount | Rejected |

---

## Core Functions

### 1. Calculate Discounted Amount

Calculates the price after applying a percentage discount.

#### Example

```text
Original Amount: R1,000.00
Discount: 10%

Discount Value = R100.00
Final Amount = R900.00
```

---

### 2. Apply VAT

Adds 15% VAT to the supplied amount.

#### Example

```text
Amount: R900.00

VAT = R135.00

Total = R1,035.00
```

---

### 3. Calculate Final Amount

Processes a complete invoice by:

```text
Original Amount
        ↓
Apply Discount
        ↓
Apply VAT
        ↓
Final Invoice Total
```

#### Example

```text
Original Amount: R1,000.00
Discount: 10%

After Discount:
R900.00

After VAT:
R1,035.00
```

---

## Validation Features

The system automatically validates:

### Amount Validation

```text
Amount cannot be negative.
```

### Discount Validation

```text
Discount cannot be less than 0%.
Discount cannot exceed 100%.
```

Invalid values trigger an `IllegalArgumentException`.

---

## Object-Oriented Concepts Demonstrated

### Encapsulation

Business rules and validation logic are contained within the InvoiceCalculator class.

### Constants

```java
private static final BigDecimal VAT_RATE
```

is used to store the VAT rate as an immutable constant.

### Method Decomposition

The application separates responsibilities into:

- Discount Processing
- VAT Processing
- Validation
- Final Invoice Calculation

---

## Skills Demonstrated

- Java Development
- Financial Programming
- BigDecimal Calculations
- Data Validation
- Business Logic Implementation
- Object-Oriented Design
- Exception Handling
- Software Engineering Best Practices

---

## Example Test Cases

### Test Case 1

```text
Amount: R500.00
Discount: 10%

Expected Result:
R517.50
```

### Test Case 2

```text
Amount: R1000.00
Discount: 20%

Expected Result:
R920.00
```

### Test Case 3

```text
Amount: R2500.00
Discount: 15%

Expected Result:
R2443.75
```

---

## Learning Outcomes

This project demonstrates:

- Financial application development
- Accurate monetary calculations
- Java BigDecimal usage
- Business rule implementation
- Input validation techniques
- Clean code principles
- Modular software design

---

## Real-World Applications

The Invoice Calculator can be adapted for:

- Retail Point-of-Sale Systems
- E-commerce Platforms
- Billing Systems
- Accounting Software
- Inventory Management Systems
- Payment Processing Applications

---

## Future Improvements

- Graphical User Interface (Swing/JavaFX)
- PDF Invoice Generation
- Database Integration
- Multiple Tax Rates
- Customer Management Module
- Product Catalog Integration
- Receipt Printing Functionality

---

## Author

**Sizwe Ramokhali**

Software Developer | Java Developer

### Skills Demonstrated
- Java Programming
- Financial Software Development
- OOP Design
- Business Logic Development
- Exception Handling
- Precision Arithmetic
