package com.example.login_register_app

class Attendee {
    var name: String = ""
    var event: String = ""
    var phoneNumber: String = ""

    constructor(name: String, event: String, phoneNumber: String) {
        this.name = name
        this.event = event
        this.phoneNumber = phoneNumber
    }
}