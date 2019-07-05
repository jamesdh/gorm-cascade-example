package com.github.event

import com.github.Organization
import com.github.Repository
import com.github.User

class IssueComment {

    /*
        Three scenarios:
        - user: assigned ID, bindable=true, save-update cascading
        - repository: default ID, save-update cascading
        - organization: defaults

     */
    String action
    User user
    Repository repository
    Organization organization

    static belongsTo = [user: User, repository: Repository, organization: Organization]

    static mapping = {
        user cascade: 'save-update'
        repository cascade: 'save-update'
    }

    static constraints = {
        action nullable: false
        user nullable: false
    }
}
