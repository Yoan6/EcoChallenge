import { useState } from 'react';
import colors from '../utils/colors';

type CityHealth = 'healthy' | 'degraded' | 'bad' | 'dying' | 'dead';

export function useCityHealth() {
  const [cityHealth, setCityHealth] = useState<CityHealth>('healthy');
  const [population, setPopulation] = useState(1000);
  const [pollutionLevel, setPollutionLevel] = useState(0);
  const [resources, setResources] = useState(100);

  // Calcule et met à jour la santé de la ville
  const updateCityHealth = () => {
    let newHealth: CityHealth;

    if (pollutionLevel < 20 && resources > 70) {
      newHealth = 'healthy';
    } else if (pollutionLevel >= 20 && pollutionLevel < 50 && resources > 50) {
      newHealth = 'degraded';
    } else if (pollutionLevel >= 50 && pollutionLevel < 70 && resources > 30) {
      newHealth = 'bad';
    } else if (pollutionLevel >= 70 && resources > 10) {
      newHealth = 'dying';
    } else {
      newHealth = 'dead';
    }

    setCityHealth(newHealth);
  };

  // Simule des événements qui affectent la ville (par exemple les actions du joueur)
  const simulateCityEvent = (eventType: string) => {
    switch (eventType) {
      case 'increase_pollution':
        setPollutionLevel(prev => Math.min(prev + 10, 100));
        break;
      case 'decrease_resources':
        setResources(prev => Math.max(prev - 20, 0));
        break;
      case 'increase_population':
        setPopulation(prev => prev + 100);
        break;
      default:
        break;
    }
    updateCityHealth();  // Met à jour la santé après l'événement
  };

  return { cityHealth, simulateCityEvent };
}
