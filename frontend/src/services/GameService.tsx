const API_URL = "http://localhost:8080/";

export const startNewGame = async () => {
    const response = await fetch(`${API_URL}/new-game`);
    return response.json()
};