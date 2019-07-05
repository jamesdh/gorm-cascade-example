package com.github.event

import com.github.Organization
import com.github.Repository
import com.github.User
import grails.testing.gorm.DomainUnitTest
import groovy.json.JsonSlurper
import spock.lang.Specification

class IssueCommentSpec extends Specification implements DomainUnitTest<IssueComment> {


    /*
        Three scenarios:
        - user: assigned ID, bindable=true, save-update cascading
            - expected: persisted
            - result: not persisted
        - repository: default ID, save-update cascading
            - expected: persisted
            - result: persisted
        - organization: defaults
            - expected: not persisted
            - result: not persisted

     */
    void "test binding and persistence"() {
        given: "An ingested JSON body"
        def slurper = new JsonSlurper()
        def createJson = slurper.parseText('''
        {
            "action": "created",
            "repository": {
                "name": "cheese"
            },
            "organization": {
                "login": "mice"
            },
            "user": {
                "id": 12345678,
                "login": "mickymouse"
            }
        }
        ''')

        when: "Binding the JSON to the domain objects"
        def issueComment = new IssueComment(createJson)

        then: "Objects should exist but not yet be saved"
        assert issueComment.user
        assert issueComment.repository
        assert issueComment.organization
        assert !IssueComment.count()
        assert !User.count()
        assert !Repository.count()
        assert !Organization.count()

        when: "Saving the event"
        issueComment.save(flush: true)

        then: "IssueComment should be persisted"
        assert issueComment.id
        assert IssueComment.exists(issueComment.id)

        then: "Organization should not be persisted"
        assert !Organization.count()

        then: "Repository should be cascade persisted"
        assert Repository.count() == 1
        assert Repository.exists(issueComment.repository.id)

        then: "User should be cascade persisted w/ assigned ID"
        assert User.count() == 1
        assert User.exists(issueComment.user.id)
        assert issueComment.user.id == createJson.user.id
    }


}

