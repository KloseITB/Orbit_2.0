-- CREAZIONE DELLE TABELLE (dal tuo file)
CREATE TABLE IF NOT EXISTS Users (
    userID INTEGER PRIMARY KEY AUTOINCREMENT,
    nickname TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    is_publisher INTEGER DEFAULT 0 
);

CREATE TABLE IF NOT EXISTS Games (
    gameID INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    genre TEXT NOT NULL,
    price REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS Library (
    userID INTEGER,
    gameID INTEGER,
    PRIMARY KEY (userID, gameID),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (gameID) REFERENCES Games(gameID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Reviews (
    userID INTEGER,
    gameID INTEGER,
    rating INTEGER CHECK(rating >= 1 AND rating <= 5),
    PRIMARY KEY (userID, gameID),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (gameID) REFERENCES Games(gameID) ON DELETE CASCADE
);

-- POPOLAMENTO UTENTI DI TEST
-- Un utente "Player" normale e un "Publisher"
INSERT INTO Users (nickname, password, is_publisher) VALUES 
('Player1', 'password123', 0),
('Publisher1', 'admin123', 1);

-- POPOLAMENTO CATALOGO GIOCHI (20 Giochi)
INSERT INTO Games (title, genre, price) VALUES 
('Cyberpunk 2077', 'RPG', 59.99),
('The Witcher 3: Wild Hunt', 'RPG', 29.99),
('Red Dead Redemption 2', 'Action/Adventure', 49.99),
('Elden Ring', 'RPG', 59.99),
('Dark Souls III', 'RPG', 39.99),
('Hollow Knight', 'Metroidvania', 14.99),
('Stardew Valley', 'Simulation', 13.99),
('Hades', 'Roguelike', 24.99),
('Celeste', 'Platformer', 19.99),
('Portal 2', 'Puzzle', 9.99),
('DOOM Eternal', 'FPS', 39.99),
('Ori and the Blind Forest', 'Platformer', 19.99),
('Terraria', 'Sandbox', 9.99),
('Minecraft Java Edition', 'Sandbox', 26.95),
('GTA V', 'Action', 29.99),
('Sekiro: Shadows Die Twice', 'Action', 59.99),
('Cuphead', 'Run and Gun', 19.99),
('Dead Cells', 'Roguelike', 24.99),
('The Elder Scrolls V: Skyrim', 'RPG', 39.99),
('EA SPORTS FC 24', 'Sports', 69.99);
