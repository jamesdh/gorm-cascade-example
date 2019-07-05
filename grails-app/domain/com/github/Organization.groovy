package com.github

import com.github.event.IssueComment

class Organization {

    Long id
    String login

    static hasMany = [issueComments: IssueComment]

    static constraints = {
        id blank: false, unique: true
    }
}
