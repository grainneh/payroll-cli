# UL Payroll System

A command-line payroll system built for CS4013 (Object-Oriented Design) at the University of Limerick. It manages payslip generation, employee records, and pay claims for three user types: Employee, Admin, and HR.

**Group project — 4 contributors.** See [Contributions](#contributions) below for individual ownership.

## Features

- **Employee** — log in, view personal details, view a payslip by date, submit a pay claim (part-time staff)
- **Admin** — log in with a password, add/remove employees from the full-time and part-time CSV records
- **HR** — log in with a password, promote employees or apply a pay raise
- **Payroll engine** — calculates gross pay (salaried vs. hourly), then net pay after PRSI, USC, income tax, health insurance, and union deductions; applies automatic annual salary scale progression each October

## Project structure

```
src/        Java source files
resources/  Sample CSV data the CLI reads/writes (employee records, salary scales, payslips)
docs/       UML class diagram and CRC cards from the design phase
```

## Running it

The file paths in the code are relative to the working directory, so compile into a folder alongside the resource CSVs:

```bash
mkdir build
javac -d build src/*.java
cp resources/*.csv build/
cd build
java Run
```

You'll be prompted to log in as Employee (`E`), Admin (`A`), or HR (`H`).

| Role | ID | Password |
|------|-----|----------|
| Employee | `1111` | — |
| Admin | `1111` | `admin123` |
| HR | `1111` | `hr123` |

To view a sample payslip, log in as Employee with ID `1111` and enter date `25/11/2024`.

## Contributions

| Area | Author(s) |
|------|-----------|
| CLI / user interaction layer | Gráinne Hartigan, Liam Finn |
| Deductions (PRSI, USC, income tax, health insurance, union) | Gráinne Hartigan |
| Payslip generation, salary scale progression | Gráinne Hartigan |
| Admin functions | Michał Czekalski, Gráinne Hartigan |
| Employee record management | Philip Roche |
| HR functions | Michał Czekalski |
| Pay claim submission | Liam Finn |

GenAI was used to assist an early draft of a CSV reader/writer helper method, which was subsequently rewritten and extended by the authors.

## Authors

Michał Czekalski, Gráinne Hartigan, Liam Finn, Philip Roche
