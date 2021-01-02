# BitBucket-repo

API that allows users to send virtual currency to each other.
Functional Requirements:
● Users must be able to create an account. It must contain a username, email, and password
● Authentication is not needed. Simply mock authentication by allowing a user id to be passed in each request in a header
● Users must have a virtual currency property (decimal number; VC henceforth)
● All users must accrue 0.25 VC every 30 minutes
● A user must be able to send any amount of their current VC to up to 10 different users at a time
● A user must be able to retrieve a list of VC transactions where they can see who they’ve sent and received money from
