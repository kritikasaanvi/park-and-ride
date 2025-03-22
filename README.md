# Park and Ride: Smart Parking & Last-Mile Connectivity Solution

A smart urban mobility platform that simplifies your commute!  
This system enables **real-time parking spot booking**, **last-mile shuttle/cab integration**, and **AI-powered dynamic pricing** — all while supporting **offline access** and **system fault-tolerance**.


---

## Tech Stack

| Layer         | Tech Used                  |
|--------------|-----------------------------|
| Frontend     | HTML, CSS, JavaScript       |
| Backend      | Spring Boot (Java)          |
| Database     | MySQL                       |
| Auth         | JWT (JSON Web Tokens)       |
| Hosting      | [To Deploy: Netlify / Vercel / Railway] |
| Deployment   | Localhost (Spring Boot)     |

---

## Features

### I. Seamless Parking Booking
- Search and pre-book parking slots near metro stations
- Real-time availability + dynamic spot assignment
- Contactless entry using **QR codes / License Plate Recognition (LPR)**

### II. Cab & Last-Mile Shuttle Integration
- Book cabs/shuttles/e-rickshaws from your phone
- On-demand or scheduled rides with AI-based route optimization
- Integration with metro cards & digital wallets

### III. Availability Conflict Resolution
- Smart IoT-based live tracking of slot availability
- No-show auto-cancellation
- Dynamic reallocation of free slots to prevent booking clashes

### IV. Dynamic Pricing Engine
- Peak-hour surge pricing and off-peak discounts
- AI-based demand forecasting and route adjustment
- Subscription plans and loyalty reward system

### V. Offline Mode Support
- View bookings & use QR passes even without internet
- Auto-sync when network returns
- Preloaded voice-guided parking directions

---

## Implementation Highlights

- JWT-Based Authentication for secure access
- OOP Principles in backend for modular structure
- AI-driven Pricing & Routing logic
- Caching with in-memory strategy
- Error handling with detailed logs
- System monitoring using Spring logs
- Tested with edge cases and real-time conditions

---

## How to Run the Project

1. **Clone the repo**
   ```bash
   git clone https://github.com/yourusername/park-and-ride.git
   cd park-and-ride
   ```

2. **Set up Backend**
   - Install Java 17+
   - Import the Spring Boot project into your IDE
   - Set up MySQL and import the provided schema
   - Update `application.properties` with your DB credentials
   - Run the app:
     ```bash
     ./mvnw spring-boot:run
     ```

3. **Set up Frontend**
   - Go to `src/main/resources/static`
   - Open `index.html` in your browser
   - Test parking booking, ride scheduling, etc.

4. **Test Credentials**
   - Username: `testuser`
   - Password: `password123`

---



---

## Project Structure (Spring Boot)

```
src/
├── controller/
├── service/
├── repository/
├── model/
├── config/       # JWT Auth configs
├── static/       # Frontend HTML/CSS/JS
└── resources/
    └── application.properties
```

---

## Future Improvements

- Use Firebase for real-time updates
- Add mobile app support (React Native / Flutter)
- Integrate real-world LPR system using OpenCV
- Enable Redis caching layer for better performance

---

## License

This project is under the MIT License.

---


