CREATE TABLE employee (
  id UUID NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  workingHours INTEGER NOT NULL,
  sickHours INTEGER NOT NULL,
  vacationHours INTEGER NOT NULL,
  date DATE NOT NULL
);