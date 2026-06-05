import "./NewsFeedPage.css";

export default function NewsFeedPage() {
    const notifications = [
        {
            id: 1,
            title: "Obavijest o sastanku",
            message:
                "Sastanak svih zaposlenika održat će se u petak u 10:00 sati u konferencijskoj dvorani.",
            targetGroup: "employees",
            image:
                "https://images.unsplash.com/photo-1517048676732-d65bc937f952",
            createdAt: "17.06.2025. 09:15",
        },
        {
            id: 2,
            title: "Nova radionica",
            message:
                "Pozivamo sve posjetitelje i zaposlenike na radionicu koja će se održati sljedeći tjedan.",
            targetGroup: "participants",
            image: null,
            createdAt: "16.06.2025. 15:30",
        },
        {
            id: 3,
            title: "Promjena radnog vremena",
            message:
                "Od sljedećeg mjeseca uvodi se novo radno vrijeme ustanove.",
            targetGroup: "participants",
            image:
                "https://images.unsplash.com/photo-1506784365847-bbad939e9335",
            createdAt: "15.06.2025. 12:00",
        },
    ];

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
                                    {notification.createdAt}
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