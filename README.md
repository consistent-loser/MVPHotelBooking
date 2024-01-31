<h1>MVP Hotel Booking</h1>

<h2>Scope</h2>
Creating a Booking service that can handle up to 3M booking requests per hour

<h2>Out of Scope</h2>
1. User Management
2. Hotel Listing
3. Searching Hotel etc

<h2>Feature Implementation - </h2>

<h3>1. Concurrency Control -</h3>
   Implemented Optimistic concurrency control to avoid duplicate bookings
   
<h3>2. Global Exception handling -</h3>
   Added Customized exception handling for clean error response

<h3>3. NoSQL -</h3>
   MongoDB integration for high-volume volume unorganized data

<h3>4.Caching </h3>
  Implemented Default Caching for spring boot to reduce DB hits

<h2>Basic Flow diagram</h2>

<img width="587" alt="image" src="https://github.com/consistent-loser/MVPHotelBooking/assets/50192697/bdcdca7e-6874-4ffe-a46d-be74d005a4cd">


