import React, { createContext, useState, useContext, ReactNode } from 'react';

// Types des données du contexte
interface ModalContextProps {
    modalGameMode: boolean;
    modalGames: boolean;
    modalInfoGame: boolean;
    setGameMode: (value: boolean) => void;
    setGames: (value: boolean) => void;
    setInfoGame: (value: boolean) => void;
    closeAllModals: () => void;
}

// Créer le contexte
const ModalContext = createContext<ModalContextProps | undefined>(undefined);

// Provider
export const ModalProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [modalGameMode, setModalGameMode] = useState(false);
    const [modalGames, setModalGames] = useState(false);
    const [modalInfoGame, setModalInfoGame] = useState(false);

    // Fonctions pour gérer les modales
    const setGameMode = (value: boolean) => setModalGameMode(value);
    const setGames = (value: boolean) => setModalGames(value);
    const setInfoGame = (value: boolean) => setModalInfoGame(value);

    const closeAllModals = () => {
        setModalGameMode(false);
        setModalGames(false);
        setModalInfoGame(false);
    };

    return (
        <ModalContext.Provider
            value={{
                modalGameMode,
                modalGames,
                modalInfoGame,
                setGameMode,
                setGames,
                setInfoGame,
                closeAllModals,
            }}
        >
            {children}
        </ModalContext.Provider>
    );
};

// Hook pour accéder au contexte
export const useModals = (): ModalContextProps => {
    const context = useContext(ModalContext);
    if (!context) {
        throw new Error("useModals must be used within a ModalProvider");
    }
    return context;
};
