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

Here's how I would **answer this in an interview**, **word by word**, assuming I'm the interviewee. I’ll walk the interviewer through my thought process like a **well-prepared, systematic student**, ensuring everything is clear, justified, and connected to real-world examples. The tone is **confident, structured, and humble**.

---

### 👨‍💼 Interview Question:

**"Design a system like Hotstar or Netflix for video streaming. Think about both on-demand and live streaming use cases."**

---

### 🧑‍💻 Answer as an Interviewee:

---

> “Sure, thank you for the question. I’d like to approach this step by step — starting with understanding the requirements, then defining system components, and finally addressing scalability, edge cases, and design decisions.

---

## 🧾 Step 1: Understanding the Requirements

> “Let’s first clarify the problem. We’re designing a system like Hotstar, which supports both **on-demand video streaming** (like movies and shows) and **live streaming** (like IPL or news).

> I’ll categorize the requirements as **functional** and **non-functional**:

### ✅ Functional Requirements:

* User should be able to **sign up, log in, browse** content.
* Should be able to **watch videos**, both live and recorded.
* Users can **resume playback** where they left off.
* Support **subscriptions**, **multi-device playback**, and maybe **offline downloads**.

### ⚙️ Non-Functional Requirements:

* System must be **highly available** (especially during high-traffic events).
* Video playback should have **low latency** and **high quality**.
* The system should **scale globally** for millions of users.
* Secure video access with **DRM and encryption**.

> Is this direction okay so far, or should I narrow the scope further?”

🧑‍🏫 *(Waits for interviewer to confirm or suggest scope limits — always good practice in real interviews.)*

---

## 🧱 Step 2: High-Level Architecture

> “At a high level, I’d split the system into two major domains:

1. **Control Plane**: User management, subscriptions, search, watch history, recommendations.
2. **Data Plane**: Video upload, encoding, storage, CDN distribution, playback.

> Let me begin by listing all the components and then I’ll deep-dive into each.”

```
Client Apps → API Gateway → Backend Services → Storage + CDN
                                   |
                              Video Pipeline
```

---

## 📲 Step 3: Client Side (Mobile, Web, TV)

> “Our client app will stream video using protocols like **HLS or MPEG-DASH** to support adaptive bitrate streaming. That means the player can switch video quality based on real-time network speed.

> For example, if a user is on 4G, they may get 1080p, and if on 3G, maybe 480p.

> This improves **user experience** and avoids buffering.”

---

## 🛡 Step 4: API Gateway

> “Next, all API calls from the client go through an **API Gateway**, which handles:

* Authentication using **JWT tokens**
* **Rate limiting** to avoid abuse (e.g., 100 requests per minute)
* Routing to the correct backend service

> For example, `/api/videos/search` would route to the **Search Service**, while `/api/user/history` routes to the **History Service**.”

---

## ⚙️ Step 5: Backend Microservices

> “Now let’s break down the core backend services.”

### 🧑 User Service

* Handles user registration and login
* Stores credentials securely using bcrypt
* Auth tokens issued as **JWT**

### 🎬 Video Metadata Service

* Stores all video-related data like title, genre, language, actors
* Ideal candidate for **NoSQL DB** like MongoDB — flexible and fast

### 💳 Subscription & Payment Service

* Integrates with Razorpay or Stripe
* Webhook system to handle payment status updates
* Stores billing info in **SQL database** for integrity

### 🕵️ Watch History Service

* Tracks what users have watched, at what time, and playback point
* Stored in a **NoSQL database** like DynamoDB for low-latency access

---

## 📽 Step 6: Video Upload & Processing Pipeline

> “This is the most critical part — the **video ingestion and processing pipeline**.”

### Workflow:

1. Creator uploads raw MP4 video to **Amazon S3**
2. Upload triggers a **Lambda function** or **Kafka event**
3. Encoding service uses **FFmpeg or AWS MediaConvert** to:

   * Convert video to multiple bitrates: 240p, 480p, 720p, 1080p
   * Split into **chunks (.ts/.mp4)** and generate **HLS playlist (.m3u8)**
4. Apply **DRM encryption** (e.g., Widevine for Android, FairPlay for Apple)
5. Push the chunks and playlist to **CloudFront CDN**

> “This pipeline ensures the same video can be streamed in different qualities on different devices, securely.”

---

## 🌍 Step 7: Content Delivery Network (CDN)

> “All encoded content is delivered through a **CDN like CloudFront or Akamai**.

> Let me explain with an example: if a user from Mumbai wants to watch a movie, we don’t want the video coming all the way from the US. The CDN caches video chunks at edge locations, so they load instantly.

> CDN also handles **geo-restrictions, signed URLs**, and supports **time-limited access** to prevent piracy.”

---

## 🔴 Step 8: Live Streaming

> “Live streaming works slightly differently.

* Ingest stream via **RTMP** protocol
* Transcode in real-time using **live media servers** (Wowza / AWS IVS)
* Convert into HLS chunks and push to CDN

> There’s typically a 10–30 second latency. For lower latency (e.g., live quiz shows), we can explore **WebRTC**.”

---

## 📦 Step 9: Data Storage Summary

| Type             | Storage         |
| ---------------- | --------------- |
| User Data        | PostgreSQL      |
| Video Metadata   | MongoDB         |
| Video Files      | S3 + CloudFront |
| History          | DynamoDB        |
| Payments         | RDBMS           |
| Logs & Analytics | S3, BigQuery    |

---

## ⚠️ Step 10: Handling Edge Cases

> “Let me walk through some edge cases and how I’d handle them.”

| Problem                           | Cause                     | Solution                                                       |
| --------------------------------- | ------------------------- | -------------------------------------------------------------- |
| 🔥 Traffic spike during IPL final | Millions join in 2 min    | Pre-scale infra, preload edge CDN, use event-based autoscaling |
| ❌ Buffering for rural users       | Poor network              | Use ABR, start with 240p chunks                                |
| 👀 Piracy                         | Users download streams    | Use **DRM + signed URLs**, watermark user ID                   |
| 💳 Payment fraud                  | Fake cards                | 3D Secure + Webhook verification                               |
| ⏱ Cold start                      | Infrequent user opens app | Use prefetch + caching                                         |

---

## 📊 Step 11: Analytics & Monitoring

> “To maintain system health, I’d integrate:

* **Prometheus + Grafana** for infrastructure metrics
* **ELK Stack** for logs
* **Kafka + Spark** for real-time analytics (e.g., what people are watching now)

> This helps detect abnormal patterns, like drops in playback success or sudden logins from suspicious IPs.”

---

## ⛓️ Step 12: Scalability and Deployment

> “To make the system scalable:

* Use **Kubernetes** to auto-scale services
* Keep services **stateless** to allow horizontal scaling
* Use **message queues like Kafka** for decoupling heavy tasks like encoding

> Deploy using CI/CD pipelines (GitHub Actions or Jenkins), with **canary rollouts** to test features safely.”

---

## 🧠 Final Thoughts

> “To summarize:

* I’ve broken the system into control and data planes
* Each service is modular, independently scalable
* Live and on-demand videos handled via specialized pipelines
* CDN and ABR optimize playback
* DRM + Signed URLs ensure security

> If you’d like, I can also sketch the architecture diagram or deep dive into any component like **recommendation engine or DRM implementation**.”

---

**architecture diagram**.

![video_streaming_architecture](https://github.com/user-attachments/assets/cf0cae7f-2dbf-4815-80be-7040306bfe5e)


-------------------------------------



    
