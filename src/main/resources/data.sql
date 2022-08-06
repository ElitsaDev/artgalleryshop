-- some test users
INSERT INTO users (id, username, email, first_name, last_name,  password)
VALUES (1, 'admin', 'admin@gmail.com', 'Admin', 'Adminov', 'ff7d5112585d4b68624f0afe939f8341d3066eec451d440c4dbb8425fa7f8b45a08f1b44e16c395d');
#         (2, 'elica', 'eli@gmail.com', 'Elica', 'Andreeva', '65843937954fbdd40fc9f463d3f8c5002ccb0ff6ec48a10e031f03334f036b9602cd637627ad04fe');

INSERT INTO authors (id, name)
VALUES (1, 'Leonardo da Vinci'),
       (2, 'Vincent van Gogh'),
       (3, 'Edvard Munch'),
       (4, 'Pablo Picasso'),
       (5, 'Salvador Dali'),
       (6, 'Georges Seurat'),
       (7, 'Johannes Vermeer'),
       (8, 'James McNeill Whistler'),
       (9, 'Rembrandt'),
       (10, 'Gustav Klimt'),
       (11, 'Claude Monet'),
       (12, 'Diego Rivera'),
       (13, 'Grant Wood'),
       (14, 'Rene Magrittees'),
       (15, 'Jackson Pollock'),
       (16, 'Pierre-Auguste Renoires'),
       (17, 'C.M. Coolidge');

INSERT INTO products (id, name, category, year, length, width, author_id, image_url)
VALUES  (1,'The Mona Lisa','PAINTINGS', 1503 , 740.0, 1150.0 , 1,'static/images/painting1.jpg'),
        (2,'Starry Night','PAINTINGS', 1889 , 1100.0, 840.0 , 2, 'static/images/painting2.jpg'),
        (3,'The Scream','PAINTINGS', 1893, 400, 400, 3,'static/images/painting3.jpg'),
        (4,'Guernica','PAINTINGS',  1937, 1040, 450, 4,'static/images/painting4.jpg'),
        (5,'The Persistence of Memory','PAINTINGS', 1931, 500, 360, 5,'static/images/painting5.jpg'),
        (6,'Three Musicians','PAINTINGS', 1921, 620, 550, 4,'static/images/painting6.jpg'),
        (7,'A Sunday Afternoon on the Island','PAINTINGS', 1921, 1550, 1550, 6, 'static/images/painting7.jpg'),
        (8,'Girl with a Pearl Earring','PHOTOGRAPHY', 1665, 1750, 2050, 7,'static/images/painting8.jpg'),
        (9,'Whistler''s Mother','DRAWINGS',1871 ,125, 150, 8,'static/images/painting9.jpg'),
        (10,'Self-Portrait without Beard','PAINTINGS', 1871, 715, 715, 2, 'static/images/painting10.jpg'),
        (11,'The Night Watch','PAINTINGS',1642, 625, 1250, 9, 'static/images/painting11.jpg'),
        (12,'The Kiss','PAINTINGS', 1908,555, 320, 10,'static/images/painting12.jpg'),
        (13,'Water Lilies','PAINTINGS',1840,555, 320, 11, 'static/images/painting13.jpg'),
        (14,'The Flower Carrier','PRINTS',1935, 555, 350, 12,'static/images/painting14.jpg'),
        (15,'American Gothic','PRINTS',1930, 555, 1250, 13, 'static/images/painting15.jpg'),
        (16,'Cafe Terrace at Night','PHOTOGRAPHY',1888, 955, 730, 2,'static/images/painting16.jpg'),
        (17,'The Son of Man','SCULPTURE',1964, 715, 450, 14, 'static/images/painting17.jpg'),
        (18,'No. 5, 1948','PAINTINGS', 1948,555, 373, 15, 'static/images/painting18.jpg'),
        (19,'Bal du moulin de la Galette','DRAWINGS',1948,781, 813, 16,'static/images/painting19.jpg'),
        (20,'Dogs Playing Poker','PRINTS',1903,391, 391, 17, 'static/images/painting20.jpg');



INSERT INTO offers (id, description, medium, image_url, price, weight, style, year, product_id, seller_id)
VALUES
    ('5ebdd23e-7bf3-4166-ab67-98242b871f6a', 'This infamous portrait of Lisa del Giocondo was completed some time between 1503-1519 and currently on display at the Museum du Louvre in Paris.', 'OIL', '/images/painting1.jpg', 15402000.00, 101, 'REALISM', 1503, 1, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f6b', 'The piece was done from memory and whimsically depicts the view from painter room at the sanitarium he resided in at the time', 'OIL', '/images/painting2.jpg', 17203100, 102, 'ABSTRACT', 1889, 2, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f6c', 'The backdrop of this expressionist painting is said to be Oslo, Norway', 'AIRBRUSH', '/images/painting3.jpg', 19166400.00, 103, 'EXPRESSIONISM', 1893, 3, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f6d', 'Inspired by the bombing of Guernica, Spain, during the Spanish Civil War', 'AIRBRUSH', '/images/painting4.jpg', 14481100.00, 104, 'CUBISM', 1937, 4, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f6e', 'It is thought that Albert Einstein''s Theory of Relativity inspired this bizarre piece', 'AIRBRUSH', '/images/painting5.jpg', 17145600.00, 105, 'SURREALISM', 1931, 5, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f6f', 'Three Musicians is actually an oil painting. Completed in 1921, they are two very similar paintings', 'DIGITAL', '/images/painting6.jpg', 15521700.00, 106, 'CUBISM', 1921, 6, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f60', 'Using the unique technique of pointillism, creating a complete image that is made up of only distinct individual dots', 'DIGITAL', '/images/painting7.jpg', 155217600.00, 107, 'MODERN', 1921, 7, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f61', 'This piece can now be found in the Mauritshuis Gallery in the Hague.', 'OIL', '/images/painting8.jpg', 155217900.00, 108, 'PHOTO_REALISM', 1665, 8, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f62', 'Very famous portrait originally known as Arrangement in Grey and Black: The Artist''s Mother', 'PENCIL', '/images/painting9.jpg', 123218900.00, 109, 'FINE_ART', 1871, 9, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f63', 'Additionally, having sold for $71.5 million in 1998, it is one of the most expensive paintings ever sold.', 'OIL', '//images/painting10.jpg', 71500000.00, 110, 'IMPRESSIONISM', 1871, 10, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f64', 'Using oil on canvas in an effort to show off for the French Queen that would be visiting.', 'OIL', '/images/painting11.jpg', 62500000.00, 111, 'FINE_ART', 1642, 11, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f65', 'What makes this piece different than the other oil paintings on the list is that it also incorporates gold leaf on canvas (in addition to oils).', 'OIL', '/images/painting12.jpg', 55500000.00, 112, 'ABSTRACT', 1908, 12, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f66', '250 paintings depicting a water lily pond from his backyard', 'AIRBRUSH', '/images/painting13.jpg', 55500000.00, 113, 'REALISM', 1840, 13, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f67', 'Rivera was known for his simple paintings dominated by their bright colors', 'AIRBRUSH', '/images/painting14.jpg', 55500000.00, 114, 'ILLUSTRATION', 1935, 14, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f68', 'It is a dry depiction of a farmer and his Plain-Jane daughter - The Great Depression personified.', 'AIRBRUSH', '/images/painting15.jpg', 320015, 115, 'MODERN', 1930, 15, 1),
    ('5ebdd23e-7bf3-4166-ab67-98242b871f69', 'It is one of the most individual depictions of such a mundane setting.', 'AIRBRUSH', '/images/painting16.jpg', 95500000.00, 116, 'STREET_ART', 1888, 16, 1);

INSERT INTO statistics(ip_address, local_date_time)
VALUES ('https://www.abv.bg', '2022-08-01 13:35:42'),
       ('https://carmarket.bg', '2022-07-31 13:35:42'),
       ('https://softuni.bg', '2022-08-30 13:35:42');

