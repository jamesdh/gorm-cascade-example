package com.github.event

import com.github.User
import grails.testing.gorm.DomainUnitTest
import groovy.json.JsonSlurper
import spock.lang.Specification

class IssueCommentSpec extends Specification implements DomainUnitTest<IssueComment> {


    void "test binding"() {
        given: "An ingested JSON body"
        def slurper = new JsonSlurper()
        def createJson = slurper.parseText('''
{
    "action": "created",
    "sender": {
        "login": "mickymouse",
        "id": 12345678
    }
}
''')

        when: "Binding the JSON to the domain object"
        def issueComment = new IssueComment(createJson)

        then: "Associations should exist but not yet be saved"
        assert issueComment.sender
        assert !IssueComment.count()
        assert !User.count()

        when: "Saving the event"
        issueComment.save(flush: true)

        then: "Event and associations should be persisted"
        assert issueComment.id
        assert IssueComment.exists(issueComment.id)
        assert User.count() == 1
        assert User.exists(issueComment.sender.id)
    }


}

