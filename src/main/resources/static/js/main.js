// 📌 Utility function
function showMessage(text, color = "red") {
    const msg = document.getElementById("message");
    msg.innerText = text;
    msg.style.color = color;
}

// 🛡️ Handle Login
document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;

    const response = await fetch("/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    if (response.ok) {
        const data = await response.json();
        localStorage.setItem("jwt", data.token);
        showMessage("Login successful! Redirecting...", "green");
        setTimeout(() => window.location.href = "booking.html", 1000);
    } else {
        showMessage("Login failed. Please check your credentials.");
    }
});

// ✍️ Handle Register
document.getElementById("registerForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("registerEmail").value;
    const password = document.getElementById("registerPassword").value;

    const response = await fetch("/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    if (response.ok) {
        showMessage("Registration successful! You can now login.", "green");
    } else {
        showMessage("Registration failed. Try a different email.");
    }
});
