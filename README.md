# Anthar-Jala Watch 🌿💧

Smart Groundwater Monitoring & Sustainability Platform built using **Android (Kotlin + Jetpack Compose)**, **Firebase**, and **Google Maps**.

Anthar-Jala Watch helps monitor borewell data, visualize groundwater stress regions, and provide community-level sustainability insights using interactive GIS-style mapping and environmental analytics.

---

## 🤖App Link

```bash
https://drive.google.com/file/d/1vBrF716za9NCEB6RUu_YSLd16bF9eYqL/view?usp=sharing
```

---

## ❗ Instruction
Turn on your mobile location to add borewell data

---

## 📱 Features

### 🌍 Real-Time Groundwater Mapping
- Live borewell location visualization
- GIS-style eco heatmap rendering
- Community groundwater stress zones
- Google Maps integration

---

### 📊 Analytics Dashboard
- Total borewell count
- Average groundwater depth
- Water risk classification
- Groundwater trend analysis
- Sustainability recommendations
- Community environmental insights

---

### ➕ Borewell Data Collection
- Add borewell details
- Area/Village tagging
- Groundwater depth tracking
- Yield recording
- Year-wise data logging
- Live GPS location capture

---

### 🔥 Smart Environmental Visualization
- Green / Yellow / Red groundwater zones
- Heatmap-style groundwater intensity rendering
- Regional clustering
- Interactive live markers

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Android Development |
| Jetpack Compose | Modern UI |
| Firebase Firestore | Cloud Database |
| Firebase Authentication | Anonymous Login |
| Google Maps SDK | Map Visualization |
| Maps Compose | Compose Map Integration |
| Android Location Services | Live GPS |
| Material 3 | UI Design |

---

## 📂 Project Structure

```bash
AntharJalaWatch/
│
├── data/
│   └── model/
│       └── BorewellEntry.kt
│
├── ui/
│   └── screens/
│       ├── MapScreen.kt
│       ├── DashboardScreen.kt
│       ├── AddDataScreen.kt
│       └── MainNavigation.kt
│
├── utils/
│   ├── FirebaseAuthHelper.kt
│   └── LocationHelper.kt
│
└── MainActivity.kt

```

## 📍 Core Functionalities

### Groundwater Monitoring
Tracks:
- groundwater depth
- borewell distribution
- environmental stress

---

### Community Analytics
Provides:
- groundwater risk assessment
- depletion trends
- sustainability recommendations

---

### GIS Visualization
Displays:
- eco heat zones
- groundwater intensity
- area-based monitoring

---

### 🔗 Clone Repository

```bash
git clone https://github.com/OmBelgali/AntharJalaWatch.git
```

---

### Open in Android Studio
Open project folder in:
Android Studio Pandas 4 or newer

---

## 🔥 Add Firebase
- Create Firebase project
- Add Android app
- Download ```google-services.json```
- Place inside:
```bash
app/
```

---

## Add Google Maps API Key
Inside AndroidManifest.xml:
```bash
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY"/>
```

---

## ▶️ Run Project
```bash
Build → Run App
```
OR generate APK:
```bash
Build → Build APK(s)
```
---

### 🎯 Project Objective

Anthar-Jala Watch aims to support:
- groundwater sustainability
- environmental monitoring
- community water awareness
- eco-friendly resource management
- through smart digital analytics and visualization.
