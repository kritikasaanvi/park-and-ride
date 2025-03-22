document.getElementById("rideForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);

    const token = localStorage.getItem("token");
    const userId = 1; // You can replace this with dynamic user ID if stored

    const res = await fetch("/api/ride/book", {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userId: userId,
            pickupLocation: formData.get("pickup"),
            dropLocation: formData.get("drop"),
            vehicleType: formData.get("type"),
            rideTime: formData.get("time")
        })
    });

    if (res.ok) {
        const data = await res.json();
        alert("✅ Ride booked successfully! Ride ID: " + data.rideId);
    } else {
        const error = await res.text();
        console.error("Ride Booking Error:", error);
        alert("❌ Failed to book ride. See console.");
    }
});
