console.log("📦 booking.js loaded");

async function fetchAvailableSpots() {
    console.log("🟢 fetchAvailableSpots CALLED");

    const stationName = document.getElementById("stationSelect").value;

    const res = await fetch(`/api/parking/available?station=${encodeURIComponent(stationName)}`, {
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
    });

    if (res.ok) {
        const data = await res.json();
        console.log("✅ Data loaded:", data);

        const list = document.getElementById("availableSpots");
        list.innerHTML = "";

        if (data.length === 0) {
            list.innerHTML = "<li>No available slots at this station.</li>";
            return;
        }

        data.forEach(spot => {
            const li = document.createElement("li");
            li.innerHTML = `
                ${spot.slotNumber} - ${spot.status}
                <button onclick="bookSlot(${spot.id})">Book</button>
            `;
            list.appendChild(li);
        });
    } else {
        alert("Failed to load spots. Make sure you're logged in.");
        const errorText = await res.text();
        console.error("❌ Error fetching spots:", errorText);
    }
}

async function bookSlot(slotId) {
    const token = localStorage.getItem("token");

    const licensePlate = prompt("Enter License Plate:");
    const start = prompt("Enter Start Time (YYYY-MM-DDTHH:MM:SS)", "2025-03-23T10:00:00");
    const end = prompt("Enter End Time (YYYY-MM-DDTHH:MM:SS)", "2025-03-23T12:00:00");

    if (!licensePlate || !start || !end) {
        alert("All fields required.");
        return;
    }

    const body = {
        slotId: slotId,
        userId: 123, // 🔁 Use dynamic later if needed
        licensePlate: licensePlate,
        startTime: start,
        endTime: end
    };

    const res = await fetch("/api/parking/book", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(body)
    });

    if (res.ok) {
        const data = await res.json();
        alert("✅ Slot booked! Reservation ID: " + (data.id || "N/A"));
    } else {
        alert("❌ Booking failed.");
        const errText = await res.text();
        console.error("Booking error:", errText);
    }
}
