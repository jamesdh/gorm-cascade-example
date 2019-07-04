package com.github.event

import com.github.User

class IssueComment {

    String action
    User sender

    static belongsTo = [sender: User]

    static constraints = {
        action nullable: false
        sender nullable: false
    }
}
