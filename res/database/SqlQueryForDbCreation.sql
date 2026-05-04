-- Tabella Utenti (Senza il campo balance)
CREATE TABLE IF NOT EXISTS Users (
    userID INTEGER PRIMARY KEY AUTOINCREMENT,
    nickname TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    is_publisher INTEGER DEFAULT 0 -- 0 per Player normale, 1 per Publisher
);

-- Tabella Giochi (Invariata)
CREATE TABLE IF NOT EXISTS Games (
    gameID INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    genre TEXT NOT NULL,
    price REAL NOT NULL
);

-- Tabella Libreria (Relazione Molti-a-Molti tra Utenti e Giochi)
CREATE TABLE IF NOT EXISTS Library (
    userID INTEGER,
    gameID INTEGER,
    PRIMARY KEY (userID, gameID),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (gameID) REFERENCES Games(gameID) ON DELETE CASCADE
);

-- Tabella Recensioni (Invariata)
CREATE TABLE IF NOT EXISTS Reviews (
    userID INTEGER,
    gameID INTEGER,
    rating INTEGER CHECK(rating >= 1 AND rating <= 5),
    PRIMARY KEY (userID, gameID),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (gameID) REFERENCES Games(gameID) ON DELETE CASCADE
);