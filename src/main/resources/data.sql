INSERT INTO category(id, name, description)
VALUES (1, 'Elektronika', 'Bardzo fajne przedmioty'),
       (2, 'Moto', 'Samochody, skutery i ciężarówki'),
       (3, 'Meble',  'Meble drewniane')
;

INSERT INTO offer(title, description, img_url, price, category_id)
VALUES
    ('Telewizor', 'Super telewizor o przekątnej 55 cali', 'http://blabla2.jpg', 1299, 1),
    ('Kino domowe', 'Wypasione kino domowe firmy Sony, gra tak, że można robić festyn', 'http://blabla3.jpg', 699, 1),
    ('Stół drewniany', 'Idealny stół drewniany dla rodziny, 6 krzeseł gratis', 'http://blabla.jpg3', 2699, 3)
;