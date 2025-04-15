
USE Library;

-- Livres
INSERT INTO Livre (titre, auteur, anneePublication, stock) VALUES
('Harry Pother', 'Rowling J. K.', 1997, 3),
('Le Seigneur des Anneaux', 'J. R. R. Tolkien', 1954, 5),
('Pirates des Caraibes', 'Ted Elliott & Terry Rossio', 2003, 2),
('50 Nuances de Gray', 'James E. L.', 2011, 10);

-- Membres
INSERT INTO Membre (nom, prenom, email, date_inscription) VALUES
('Chetsong', 'Cabrel', 'cabrel.chetsong@icloud.com', CURRENT_DATE),
('Dupont', 'Jean', 'jean.dupont@gmail.com', CURRENT_DATE),
('Martin', 'Claire', 'claire.martin@gmail.com', CURRENT_DATE),
('Nguyen', 'Bao', 'bao.nguyen@yahoo.com', CURRENT_DATE),
('Tremblay', 'Michel', 'Michel.tremblay@hotmail.com', CURRENT_DATE);

-- Emprunts
INSERT INTO Emprunt (idLivre, idMembre, dateEmprunt, dateRetourPrevue, dateRetourEffective) VALUES
(1, 4, CURRENT_DATE - INTERVAL 8 DAY, CURRENT_DATE - INTERVAL 3 DAY, NULL),
(2, 5, CURRENT_DATE - INTERVAL 10 DAY, CURRENT_DATE - INTERVAL 4 DAY, CURRENT_DATE - INTERVAL 2 DAY),
(3, 3, CURRENT_DATE - INTERVAL 10 DAY, CURRENT_DATE + INTERVAL 9 DAY, NULL),
(5, 2, CURRENT_DATE - INTERVAL 1 DAY, CURRENT_DATE + INTERVAL 10 DAY, CURRENT_DATE + INTERVAL 5 DAY);



