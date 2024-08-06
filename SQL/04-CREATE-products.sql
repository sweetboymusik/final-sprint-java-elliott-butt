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
	REFERENCES public.seller_information (id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	NOT VALID
);