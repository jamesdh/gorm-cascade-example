package com.github

import com.github.event.IssueComment

class User {

    Long id
    String login

    static hasMany = [issueComments: IssueComment]

    static mapping = {
        id generator: 'assigned'
    }

    static constraints = {
        id blank: false, unique: true, bindable: true
    }
}
