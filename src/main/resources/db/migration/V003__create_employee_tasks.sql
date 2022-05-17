CREATE TABLE IF NOT EXISTS employee_tasks
(
    employee_id SERIAL NOT NULL,
    task_uuid VARCHAR(36) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (task_uuid) REFERENCES tasks (uuid)
);