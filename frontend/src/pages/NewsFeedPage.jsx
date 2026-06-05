import { useEffect, useState } from "react";
import axios from "axios";
import "./NewsFeedPage.css";

export default function NewsFeedPage() {

    const [notifications, setNotifications] = useState([]);


    useEffect(() => {

    const userType =
        localStorage.getItem("userType");

        loadNotifications(userType);

    }, []);

    const loadNotifications = async (userType) => {

        try {

            const response =
                await axios.get(
                    `http://localhost:8081/api/notifications/${userType}`
                );

            setNotifications(
                response.data
            );

        } catch (error) {

            console.error(error);

        }
    };

    return (
        <div className="newsfeed-page">
            <h1 className="prijava">Obavijesti</h1>

            <div className="feed">
                {notifications.map((notification) => (
                    <article
                        key={notification.id}
                        className="notification-card"
                    >
                        {notification.image && (
                            <img
                                src={notification.image}
                                alt={notification.title}
                                className="notification-image"
                            />
                        )}

                        <div className="notification-content">
                            <div className="notification-header">
                                <h2 className="prijava">{notification.title}</h2>

                                <span className="notification-date">
                                    {new Date(
                                            notification.createdAt
                                        ).toLocaleString()}
                                </span>
                            </div>

                            <p className="prijava">{notification.message}</p>

                            <div className="notification-footer">
                                <span className="prijava">
                                    {notification.targetGroup === "employees"
                                        ? "Samo zaposlenici"
                                        : "Posjetitelji i zaposlenici"}
                                </span>
                            </div>
                        </div>
                    </article>
                ))}
            </div>
        </div>
    );
}