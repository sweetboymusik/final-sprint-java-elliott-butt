CREATE TABLE public.categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE public.users
(
    id serial NOT NULL,
    username character varying(128) NOT NULL,
    password character varying(128) NOT NULL,
    email character varying(128) NOT NULL,
    role character varying(16) NOT NULL,
    last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE public.seller_information (
    id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    store_name VARCHAR(255) NOT NULL,
    store_description TEXT,
    contact_number VARCHAR(20),
    email VARCHAR(255),
    website_url VARCHAR(255),
    address TEXT,
    FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE
);

CREATE TABLE public.products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category_id INT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    seller_id INT NOT NULL,
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT products_seller_id_fkey FOREIGN KEY (seller_id)
	REFERENCES public.seller_information (id) ON DELETE CASCADE
);

INSERT INTO public.categories (id, name) VALUES
(1, 'Electronics'),
(2, 'Furniture'),
(3, 'Office Supplies'),
(4, 'Fitness'),
(5, 'Kitchen'),
(6, 'Books'),
(7, 'Clothing');

INSERT INTO public.users (username, password, email, role) VALUES 
('adminAlice', '$2a$10$DXa1IsEYck0nAw9rZBSDVeJkzUo3L56txhgDZa6qmP.hhSLowEZ.m', 'alice.admin@example.com', 'admin'),
('adminBob', '$2a$10$ZW6Vn608mPmX7ndLlUp4oumOEXMwVRRWO8EYq5PZPtVd8pf2mnxgC', 'bob.admin@example.com', 'admin'),
('buyerCharlie', '$2a$10$LYwvIOpQcNA4fWd2cbYPQOzH4IOaheKbHPn4MJNYxNcXA1Fv0VgXe', 'charlie.buyer@example.com', 'buyer'),
('buyerDana', '$2a$10$H8apY9Lc2IRJrctKwzQuheaDkVThHMIl9j7IXDTc9oRL9vfridQkS', 'dana.buyer@example.com', 'buyer'),
('sellerEve', '$2a$10$lxMXAiCCTHZ5SzQnQ2RHq.FunvcWvOwBh3kecO1MD0kkwv9lXkdYW', 'eve.seller@example.com', 'seller'),
('sellerFrank', '$2a$10$IBwBEArVzmjjxHlKd71Z5u.irl5adM0U3j.ItLo3u1JhOcaxKEc4y', 'frank.seller@example.com', 'seller'),
('sellerGrace', '$2a$10$q12XDQ8RIgKCqOFjcWrVxegc2JLyFQtXnSquw3ZhPPWPKbaIcp.fu', 'grace.seller@example.com', 'seller'),
('sellerHank', '$2a$10$NDRaaB2APhLhfSeF3fNcSe0JaE/jT2o8x8NvoV965im/zET/614MS', 'hank.seller@example.com', 'seller'),
('sellerIvy', '$2a$10$xkC7fIhtoX6es.It/p2kpONLtvz5Clo89MafNWcNaOnSgDEIZpLk.', 'ivy.seller@example.com', 'seller'),
('sellerJack', '$2a$10$.3EEIRZ6hvOKELHe2UKE5.KVFFyJmSC3ENFEJKm1nJmOOvGIcPKQG', 'jack.seller@example.com', 'seller');

INSERT INTO public.seller_information (
    user_id, store_name, store_description, contact_number, email, website_url, address
) VALUES
(5, 'TechGadget Store', 'Your one-stop shop for the latest in tech gadgets and accessories.', '+1-555-123-4567', 'contact@techgadgetstore.com', 'https://www.techgadgetstore.com', '123 Tech Lane, Silicon Valley, CA, 94043'),
(6, 'HomeOffice Essentials', 'Premium products for your home office setup, from monitors to desk accessories.', '+1-555-234-5678', 'info@homeofficessentials.com', 'https://www.homeofficessentials.com', '456 Office Road, Tech City, CA, 94044'),
(7, 'FitLife Hub', 'Fitness gear and equipment to help you achieve your health goals.', '+1-555-345-6789', 'support@fitlifehub.com', 'https://www.fitlifehub.com', '789 Fitness Blvd, Health Town, CA, 94045'),
(8, 'Cookery Corner', 'Top-notch cookware and kitchen gadgets for every cooking enthusiast.', '+1-555-456-7890', 'service@cookerycorner.com', 'https://www.cookerycorner.com', '101 Culinary Street, Gourmet City, CA, 94046'),
(9, 'Book Haven', 'A wide selection of books across various genres for avid readers.', '+1-555-567-8901', 'hello@bookhaven.com', 'https://www.bookhaven.com', '202 Literary Lane, Reading City, CA, 94047'),
(10, 'Fashion Forward', 'Trendy clothing and accessories for the modern style enthusiast.', '+1-555-678-9012', 'contact@fashionforward.com', 'https://www.fashionforward.com', '303 Style Ave, Trendy Town, CA, 94048');


INSERT INTO public.products 
	(name, description, category_id, price, quantity, seller_id, date_added) 
VALUES
	('Wireless Mouse', 'A high-precision wireless mouse with ergonomic design.', 1, 25.99, 50, 1, '2022-11-15'),
	('Gaming Keyboard', 'Mechanical gaming keyboard with customizable RGB lighting.', 1, 75.99, 30, 1, '2023-05-23'),
	('USB-C Hub', 'Multi-port USB-C hub with HDMI, USB 3.0, and Ethernet ports.', 1, 29.99, 60, 1, '2024-03-01'),
	('Office Chair', 'Ergonomic office chair with lumbar support and adjustable height.', 2, 199.99, 20, 1, '2023-09-10'),
	('Standing Desk', 'Height-adjustable standing desk with memory presets.', 2, 299.99, 15, 1, '2023-07-22'),
	('HD Monitor', '24-inch full HD monitor with vibrant colors and sharp details.', 1, 159.99, 25, 2, '2023-01-08'),
	('Monitor Stand', 'Adjustable monitor stand with cable management.', 1, 39.99, 50, 2, '2023-12-02'),
	('Desk Lamp', 'LED desk lamp with adjustable brightness and color temperature.', 2, 49.99, 40, 2, '2022-10-20'),
	('Ergonomic Mouse Pad', 'Mouse pad with wrist support for comfortable use.', 3, 15.99, 70, 2, '2024-05-15'),
	('Cable Management Kit', 'Complete cable management kit for a tidy workspace.', 3, 19.99, 80, 2, '2023-03-17'),
	('Yoga Mat', 'Non-slip yoga mat with extra cushioning for comfort.', 4, 29.99, 100, 3, '2024-02-27'),
	('Dumbbell Set', 'Adjustable dumbbell set for strength training.', 4, 89.99, 40, 3, '2023-06-30'),
	('Resistance Bands', 'Set of resistance bands with different levels of resistance.', 4, 24.99, 80, 3, '2023-04-10'),
	('Foam Roller', 'High-density foam roller for muscle recovery.', 4, 19.99, 50, 3, '2022-09-05'),
	('Exercise Ball', 'Anti-burst exercise ball for stability workouts.', 4, 22.99, 60, 3, '2023-08-15'),
	('Cookware Set', '10-piece non-stick cookware set for everyday cooking.', 5, 129.99, 25, 4, '2023-11-03'),
	('Chef Knife', 'Professional chef knife with a razor-sharp edge.', 5, 59.99, 50, 4, '2022-12-22'),
	('Cutting Board', 'Bamboo cutting board with juice groove.', 5, 19.99, 70, 4, '2023-02-14'),
	('Measuring Cups', 'Set of stainless steel measuring cups for precise measurements.', 5, 12.99, 100, 4, '2024-01-07'),
	('Mixing Bowls', 'Set of stainless steel mixing bowls with lids.', 5, 24.99, 80, 4, '2022-07-29'),
	('Fiction Book', 'A thrilling mystery novel that keeps you on the edge of your seat.', 6, 14.99, 150, 5, '2023-05-12'),
	('Non-Fiction Book', 'A comprehensive guide to personal finance and investing.', 6, 19.99, 120, 5, '2022-11-20'),
	('Children’s Book', 'A charming tale for young readers with beautiful illustrations.', 6, 9.99, 200, 5, '2023-03-08'),
	('Mystery Novel', 'An engaging mystery novel with unexpected twists and turns.', 6, 16.99, 110, 5, '2023-07-25'),
	('Science Fiction Book', 'A futuristic science fiction adventure with deep space exploration.', 6, 18.99, 130, 5, '2023-01-14'),
	('Men’s T-Shirt', 'Comfortable and stylish men’s t-shirt made from high-quality cotton.', 7, 14.99, 100, 6, '2023-02-17'),
	('Women’s T-Shirt', 'Trendy women’s t-shirt with a relaxed fit and soft fabric.', 7, 15.99, 120, 6, '2023-06-28'),
	('Men’s Jeans', 'Classic men’s jeans with a straight fit and durable material.', 7, 39.99, 80, 6, '2022-08-30'),
	('Women’s Jeans', 'Fashionable women’s jeans with a slim fit and stretchable fabric.', 7, 42.99, 90, 6, '2023-04-19'),
	('Baseball Cap', 'Adjustable baseball cap with a comfortable fit and embroidered logo.', 7, 12.99, 140, 6, '2023-09-11');
