const API_URL = "http://localhost:8080"; // URL de l'API

class APIService {
    static async request(method: string, endpoint: string, data: any = null) {
        const options: RequestInit = {
            method,
            headers: {
                "Content-Type": "application/json",
            },
        };

        if (data && (method === "POST" || method === "PUT")) {
            options.body = JSON.stringify(data);
        }

        try {
            const response = await fetch(`${API_URL}${endpoint}`, options);

            // Vérifie si la réponse est correcte (status 2xx)
            if (!response.ok) {
                const errorResponse = await response.json();
                throw new Error(errorResponse.message || "Une erreur est survenue.");
            }

            // Parse la réponse JSON
            const jsonResponse = await response.json();
            if (jsonResponse.status !== "success") {
                throw new Error(jsonResponse.message || "Une erreur est survenue.");
            }

            return jsonResponse;
        } catch (error: any) {
            console.error("Erreur lors de la requête:", error.message);
            throw error;
        }
    }
}

export default APIService;
