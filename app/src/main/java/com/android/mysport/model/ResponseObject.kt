package com.android.mysport.model

class ResponseObject {

    var events: SportEvents? = null
    var teams: Teams? = null
    var error: Throwable? = null

    constructor(events: SportEvents?) {
        this.events = events
    }

    constructor(teams: Teams?) {
        this.teams = teams
    }

    constructor(error: Throwable?) {
        this.error = error
    }

}