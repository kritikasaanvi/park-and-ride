// LOGIN HANDLER
const loginForm = document.getElementById("loginForm");
if (loginForm) {
    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const payload = {
            username: formData.get("username"),
            password: formData.get("password"),
        };

        const res = await fetch("/api/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload),
        });

        if (res.ok) {
            const data = await res.json();
            localStorage.setItem("token", data.token);
            window.location.href = "dashboard.html";
        } else {
            alert("Login failed.");
        }
    });
}

// REGISTER HANDLER
const registerForm = document.getElementById("registerForm");
if (registerForm) {
    registerForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const payload = {
            username: formData.get("username"),
            password: formData.get("password"),
            email: formData.get("email")
        };

        const res = await fetch("/api/auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload),
        });

        if (res.ok) {
            alert("Registered successfully!");
            window.location.href = "login.html";
        } else {
            const error = await res.text();
            alert("Registration failed: " + error);
        }
    });
}
