# Streaming Application System Design
---------------------------------------

### Aim:
- Design a video streaming application that is able to deliver video streams on demand to a large user base across globe

### Requirements: 
1. Streaming video
2. Processing video
3. Broadcasting
4. Reactions / Comments etc.
5. Failproof
6. Advertisements
7. News/Flashes
8. Graceful degradation of lower video quality
9. Multiple Device Support etc

---------------------------------------------------

Designing a **video streaming platform like Hotstar (or Netflix, Prime Video)** is a **classic system design interview** question. It requires thinking across multiple dimensions: scalability, latency, reliability, cost, user experience, and fault tolerance.

---

## 🧠 Step-by-Step System Design: Video Streaming Platform (e.g., Hotstar)

---

### 🎯 **Step 1: Understand the Requirements**

#### ✅ Functional Requirements:

* User signup/login (authentication & authorization)
* Browsing/searching for shows/movies
* Video playback (Live + On-demand)
* Subscription & Payment handling
* Multi-device support
* Video resume, watch history, and recommendations

#### ⚠️ Non-Functional Requirements:

* Low latency video playback
* High availability and scalability
* Handle **millions of concurrent users** (especially during live events like IPL)
* Support for 4K streaming and multiple bitrates
* DRM (Digital Rights Management) to prevent piracy
* Global CDN delivery (multi-region support)

---

## 🧱 Step 2: High-Level Components

```
Client (Mobile/Web/TV)
        |
API Gateway (Authentication, Routing, Rate Limiting)
        |
+-------------------------------+
|    Backend Services           |
|                               |
| +--------------------------+  |
| | User Service             |  |
| | Video Metadata Service   |  |
| | Search & Recommendation  |  |
| | Subscription & Billing   |  |
| | Watch History Service    |  |
| +--------------------------+  |
|                               |
+---------------|---------------+
                |
      Video Streaming & CDN
                |
           Video Storage (S3 + Origin Servers)
                |
       Video Processing Pipeline (Encoding, DRM)
```

---

## 🧩 Step 3: Component-by-Component Design

---

### 1️⃣ **Client Application (Mobile / Web / Smart TVs)**

* Should support:

  * Adaptive Bitrate Streaming (ABR)
  * Local caching of video metadata
  * Offline downloads (via DRM-encrypted files)
* Uses **HLS (HTTP Live Streaming)** or **DASH** for video playback

📌 Why?

* Mobile users have varying network conditions, so ABR ensures smooth playback.

---

### 2️⃣ **API Gateway**

Handles:

* Authentication (JWT or OAuth)
* Rate Limiting (to prevent abuse)
* Request routing (based on service path)

📌 Example:
`GET /api/video/12345` → routes to Video Metadata Service

---

### 3️⃣ **User Service**

* Handles registration, login, profile info
* Stores user data in **RDBMS** (e.g., PostgreSQL)

🔐 Use **JWTs** for session management.
☁️ Store user data in **region-localized databases** to reduce latency.

---

### 4️⃣ **Video Metadata Service**

* Stores information like:

  * Title, Description, Genre, Language, Ratings, Duration
* Use **NoSQL (MongoDB / Cassandra)** for scalability

📌 Why?

* Metadata access is read-heavy, schema is flexible, ideal for NoSQL

---

### 5️⃣ **Search & Recommendation Service**

* Search:

  * Use **Elasticsearch** for indexing metadata
* Recommendation:

  * Based on watch history, trending videos, user demographics
  * Store user activity in **Kafka** → process with **Spark/Flink**

📌 Why?

* Real-time trends and batch-processing for recommendations

---

### 6️⃣ **Subscription & Payment**

* Integrate with Stripe / Razorpay
* Store billing history in **SQL**
* Use **Webhook-based design** for payment callbacks

---

### 7️⃣ **Watch History Service**

* Track:

  * What user watched
  * Resume points
  * Last watched timestamp
* Store in **NoSQL (DynamoDB/Firestore)**

---

### 8️⃣ **Video Processing Pipeline**

**Upload flow:**

1. Ingest raw video (MP4) into an S3 bucket
2. Trigger AWS Lambda or Kafka message
3. Use **FFmpeg** or **AWS MediaConvert** to:

   * Encode into multiple resolutions (240p to 4K)
   * Add watermarks, subtitles, chapters
   * Apply **DRM** (Widevine, FairPlay)
4. Push segments to **CDN origin**

---

### 9️⃣ **CDN + Video Delivery**

* Upload all encoded segments (.ts or .mp4 chunks) to CDN (CloudFront / Akamai)
* Serve via **HLS (m3u8 playlists)**
* Use **signed URLs** for security

📌 Live streaming uses **Segmenter + Packager** → m3u8 playlist generation

---

## 🔄 Step 4: Database Design

### 🔸 Users Table

```sql
User(user_id, name, email, password_hash, created_at, ...)
```

### 🔸 Videos Table

```sql
Video(video_id, title, description, genre, tags, duration, languages, upload_time, is_live, status)
```

### 🔸 Watch History

```sql
WatchHistory(user_id, video_id, last_watched_at, watched_percentage, resume_point)
```

---

## 📦 Step 5: Storage Strategy

| Type           | Storage                |
| -------------- | ---------------------- |
| Raw Videos     | AWS S3                 |
| Encoded Chunks | CloudFront Origin + S3 |
| Thumbnails     | S3 or CDN              |
| Metadata       | MongoDB / Cassandra    |
| User Data      | PostgreSQL / MySQL     |

---

## 🌐 Step 6: Handling **Live Streaming**

* Use protocols like **RTMP → HLS/DASH** conversion
* Live ingestion servers (origin + edge)
* Cloud tools: **AWS IVS**, **Wowza**, **nginx-rtmp-module**
* **10–30 sec latency**, not real-time

For very low latency: Use **WebRTC** (for interactive live shows)

---

## ⚠️ Step 7: Edge Cases & Mitigation

| Problem                   | Cause                                | Solution                                                     |
| ------------------------- | ------------------------------------ | ------------------------------------------------------------ |
| Latency during IPL finals | Spike in traffic                     | Pre-scale infra, CDN edge caching, overflow via multi-region |
| Video buffering           | Poor ABR or high bitrate             | Use ABR with network sniffers, start with lowest bitrate     |
| Piracy                    | Users download video illegally       | DRM (Widevine/FairPlay), watermarking                        |
| User skips payment        | Watch premium content without paying | Token-based access, server-side authorization per video      |
| Cold Start                | User opens app after long time       | Use cache warm-up + prefetching                              |
| Session hijacking         | JWT stolen                           | Token expiry + rotation + IP binding                         |

---

## 📊 Step 8: Monitoring and Analytics

* Use Prometheus + Grafana for infra monitoring
* Real-time logs: ELK Stack
* User activity: Kafka → Clickstream → BigQuery
* Business metrics:

  * Bounce Rate, Watch Time, Buffer Time, Conversion Funnel

---

## ⛓️ Step 9: Scalability Strategy

* Horizontally scale stateless services (Node/Java)
* CDN reduces 90% origin load
* Use **microservices + message queue (Kafka/SQS)** for async processing
* Auto-scale encoding workers with K8s or Fargate

---

## 🔐 Step 10: Security Measures

* Encrypted video chunks (AES-128)
* Signed CDN URLs (time-bound access)
* OAuth2 for 3rd-party auth (Google, Apple login)
* HTTPS-only communication
* WAF + API Gateway rate limiting

---

## 🧪 Final Touches: CI/CD, DevOps, Reliability

* Dockerize all services
* Use Kubernetes with Helm for deployment
* Use Canary + Blue/Green deploys
* Load Testing with Locust/JMeter
* Chaos engineering for resilience (Netflix’s Simian Army model)

---

## 🧠 Summary

| Layer               | Technology Stack           |
| ------------------- | -------------------------- |
| Frontend            | React, Flutter             |
| Backend Services    | Node.js, Java, Go          |
| DB (User, Billing)  | PostgreSQL                 |
| DB (Metadata)       | MongoDB / Cassandra        |
| Search              | Elasticsearch              |
| Video Processing    | FFmpeg, AWS MediaConvert   |
| Streaming Protocols | HLS / MPEG-DASH            |
| DRM                 | Widevine, FairPlay         |
| CDN                 | CloudFront, Akamai         |
| Queueing            | Kafka / SQS                |
| Monitoring          | Prometheus + Grafana       |
| Infra               | AWS/GCP/Azure + Kubernetes |

---

If you want, I can also:

* Draw architecture diagrams
* Deep dive into any one service (like Search, CDN optimization, ABR)
* Add capacity planning and load estimation

Let me know how deep you’d like to go, Sumegh.

    
