CREATE TABLE employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

CREATE TABLE department (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

INSERT INTO department (name) VALUES ('Engineering');
INSERT INTO department (name) VALUES ('Sales');

-- employee will have a foreign key to department
ALTER TABLE employee ADD COLUMN department_id BIGINT;
ALTER TABLE employee ADD CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES department (id);


INSERT INTO employee (email, salary, department_id) VALUES ('john@example.com', 10000, 1);
INSERT INTO employee (email, salary, department_id) VALUES ('casey@example.com', 12000, 1);
INSERT INTO employee (email, salary, department_id) VALUES ('jane@example.com', 8000, 2);