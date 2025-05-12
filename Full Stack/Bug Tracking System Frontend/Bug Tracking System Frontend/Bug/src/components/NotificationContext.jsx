import React, { createContext, useState } from "react";

export const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [notifications, setNotifications] = useState([]);

  const addNotification = (message, username) => {
    try {
      setNotifications((prev) => [...prev, `${message} (User: ${username})`]);
    } catch (error) {
      console.error("Error adding notification:", error);
    }

    // Auto-clear notifications after 2 seconds
    setTimeout(() => {
      setNotifications([]); // Reset notifications array
    }, 4000);
  };

  const clearNotifications = () => {
    setTimeout(() => {
      setNotifications([]);
    }, 2000); // Clears after 2 seconds (2000ms)
  };

  return (
    <NotificationContext.Provider
      value={{ notifications, addNotification, clearNotifications }}
    >
      {children}
    </NotificationContext.Provider>
  );
};
