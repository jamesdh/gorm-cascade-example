package com.github

import com.github.event.IssueComment

class Repository {

    Long id
    String name

    static hasMany = [issueComments: IssueComment]

    static constraints = {
        id blank: false, unique: true
    }
}
