<<<<<<< HEAD
import React, { createContext, useState } from "react";

export const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [notifications, setNotifications] = useState([]);

  const addNotification = (message, username) => {
    try {
      setNotifications((prev) => [...prev, `${message} (L3 Team: ${username})`]);
    } catch (error) {
      console.error("Error adding notification:", error);
    }

   
    setTimeout(() => {
      setNotifications([]); // Reseting notifications array
    }, 150000);
  };

  const clearNotifications = () => {
    setTimeout(() => {
      setNotifications([]);
    }, 20000); 
  };

  return (
    <NotificationContext.Provider
      value={{ notifications, addNotification, clearNotifications }}
    >
      {children}
    </NotificationContext.Provider>
  );
};
=======
import React, { createContext, useState } from "react";

export const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [notifications, setNotifications] = useState([]);

  const addNotification = (message, username) => {
    try {
      setNotifications((prev) => [...prev, `${message} (L3 Team: ${username})`]);
    } catch (error) {
      console.error("Error adding notification:", error);
    }

   
    setTimeout(() => {
      setNotifications([]); // Reseting notifications array
    }, 150000);
  };

  const clearNotifications = () => {
    setTimeout(() => {
      setNotifications([]);
    }, 20000); 
  };

  return (
    <NotificationContext.Provider
      value={{ notifications, addNotification, clearNotifications }}
    >
      {children}
    </NotificationContext.Provider>
  );
};
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
