-- Sample categories
INSERT INTO category (id, name) VALUES (1, 'Work');
INSERT INTO category (id, name) VALUES (2, 'Personal');
INSERT INTO category (id, name) VALUES (3, 'Family');
INSERT INTO category (id, name) VALUES (4, 'Health');

-- Sample tasks
INSERT INTO task (title, description, dueDate, priority, completed, category_id)
VALUES
('Finish Spring project', 'Complete the test layer', '2025-11-20', 'HIGH', false, 1),
('Buy christmas presents', 'bike, computer, socks', '2025-12-25', 'LOW', false, 3),
('Clean room', 'Organize desk and wardrobe', '2025-11-21', 'MEDIUM', false, 2);
('Doctor appointment', 'Annual check-up at local clinic', '2025-11-22', 'MEDIUM', false, 4),
('Weekly meal prep', 'Cook meals for the week', '2025-11-17', 'MEDIUM', false, 4),
('Call family members', 'Catch up with parents and siblings over the phone', '2025-11-24', 'LOW', false, 2);