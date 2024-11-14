// APIService.ts
const API_URL = "http://localhost:8080"; // URL de l'API

class APIService {
    static async request(method: string, endpoint: string, data: any = null) {
        const options: RequestInit = {
            method,
            headers: {
                "Content-Type": "application/json",
            },
        };

        // Ajoute les données dans le corps de la requête pour POST et PUT
        if (data && (method === "POST" || method === "PUT")) {
            options.body = JSON.stringify(data);
        }

        try {
            const response = await fetch(`${API_URL}${endpoint}`, options);

            if (!response.ok) {
                throw new Error(`Erreur ${method}: ${response.status}`);
            }

            // Si la méthode est DELETE, on peut ne pas attendre de réponse en JSON
            return method === "DELETE" ? null : await response.json();
        } catch (error) {
            console.error(`Erreur lors de la requête ${method}:`, error);
            throw error;
        }
    }
}

export default APIService;
