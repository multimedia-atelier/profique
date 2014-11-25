package ma.demo.devfest.profique

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.Work
import ma.profique.domain.DummyEntity
import ma.profique.service.impl.DummyEntityServiceImpl
import ma.profique.service.impl.DummyEntityServiceImpl
import ma.profique.service.impl.OfyProvider
import spock.lang.Specification

/**
 * Created by stepan on 18.2.14.
 */
class MessageServiceTest extends Specification {



    def "insertOrUpdate should run in transaction" () {
        setup:
            def providerMock = Mock(OfyProvider)
            def ofyMock = Mock(Objectify)
            providerMock.get() >> ofyMock
            def service = new DummyEntityServiceImpl(providerMock)
            def message = new DummyEntity()
            message.key = "key"
        when:
            service.createOrUpdateMessage(message)
        then:
            1 * ofyMock.transact(_ as Work)
    }
}
