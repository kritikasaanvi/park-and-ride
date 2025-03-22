document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        const res = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password })  // ✅ fixed
        });

        if (res.ok) {
            const data = await res.json();
            localStorage.setItem("token", data.token);   // ✅ Save token
            alert("Login successful!");
            window.location.href = "dashboard.html";     // ✅ Redirect
        } else {
            alert("Login failed. Please check credentials.");
        }
    } catch (err) {
        console.error("Login error:", err);
        alert("Something went wrong.");
    }
});
