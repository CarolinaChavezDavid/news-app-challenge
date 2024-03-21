package com.example.newsAppChallenge.data

data class UsersData(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String,
    val birthDate: String,
    val address: UserAddress,
    val phone: String,
    val website: String,
    val company: UserCompany,
)

data class UserAddress(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: UserLocation,
)

data class UserLocation(
    val lat: String,
    val lng: String,
)

data class UserCompany(
    val name: String,
    val catchPhrase: String,
    val bs: String,
)

val userExample =
    UsersData(
        1,
        "John",
        "Doe",
        "johndoe@example.com",
        "1973-01-22",
        UserAddress(
            "123 Main Street",
            "Apt. 4",
            "Anytown",
            "12345-6789",
            UserLocation(
                "42.1234",
                "-71.2345",
            ),
        ),
        "(555) 555-1234",
        "www.johndoe.com",
        UserCompany(
            "ABC Company",
            "Innovative solutions for all your needs",
            "Marketing",
        ),
    )
