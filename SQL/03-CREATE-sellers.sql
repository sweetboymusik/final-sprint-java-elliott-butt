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