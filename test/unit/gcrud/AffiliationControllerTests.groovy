package gcrud



import org.junit.*
import grails.test.mixin.*

@TestFor(AffiliationController)
@Mock(Affiliation)
class AffiliationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/affiliation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.affiliationInstanceList.size() == 0
        assert model.affiliationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.affiliationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.affiliationInstance != null
        assert view == '/affiliation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/affiliation/show/1'
        assert controller.flash.message != null
        assert Affiliation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/affiliation/list'

        populateValidParams(params)
        def affiliation = new Affiliation(params)

        assert affiliation.save() != null

        params.id = affiliation.id

        def model = controller.show()

        assert model.affiliationInstance == affiliation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/affiliation/list'

        populateValidParams(params)
        def affiliation = new Affiliation(params)

        assert affiliation.save() != null

        params.id = affiliation.id

        def model = controller.edit()

        assert model.affiliationInstance == affiliation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/affiliation/list'

        response.reset()

        populateValidParams(params)
        def affiliation = new Affiliation(params)

        assert affiliation.save() != null

        // test invalid parameters in update
        params.id = affiliation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/affiliation/edit"
        assert model.affiliationInstance != null

        affiliation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/affiliation/show/$affiliation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        affiliation.clearErrors()

        populateValidParams(params)
        params.id = affiliation.id
        params.version = -1
        controller.update()

        assert view == "/affiliation/edit"
        assert model.affiliationInstance != null
        assert model.affiliationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/affiliation/list'

        response.reset()

        populateValidParams(params)
        def affiliation = new Affiliation(params)

        assert affiliation.save() != null
        assert Affiliation.count() == 1

        params.id = affiliation.id

        controller.delete()

        assert Affiliation.count() == 0
        assert Affiliation.get(affiliation.id) == null
        assert response.redirectedUrl == '/affiliation/list'
    }
}
