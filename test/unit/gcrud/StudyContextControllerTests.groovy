package gcrud



import org.junit.*
import grails.test.mixin.*

@TestFor(StudyContextController)
@Mock(StudyContext)
class StudyContextControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/studyContext/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.studyContextInstanceList.size() == 0
        assert model.studyContextInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.studyContextInstance != null
    }

    void testSave() {
        controller.save()

        assert model.studyContextInstance != null
        assert view == '/studyContext/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/studyContext/show/1'
        assert controller.flash.message != null
        assert StudyContext.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/studyContext/list'

        populateValidParams(params)
        def studyContext = new StudyContext(params)

        assert studyContext.save() != null

        params.id = studyContext.id

        def model = controller.show()

        assert model.studyContextInstance == studyContext
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/studyContext/list'

        populateValidParams(params)
        def studyContext = new StudyContext(params)

        assert studyContext.save() != null

        params.id = studyContext.id

        def model = controller.edit()

        assert model.studyContextInstance == studyContext
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/studyContext/list'

        response.reset()

        populateValidParams(params)
        def studyContext = new StudyContext(params)

        assert studyContext.save() != null

        // test invalid parameters in update
        params.id = studyContext.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/studyContext/edit"
        assert model.studyContextInstance != null

        studyContext.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/studyContext/show/$studyContext.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        studyContext.clearErrors()

        populateValidParams(params)
        params.id = studyContext.id
        params.version = -1
        controller.update()

        assert view == "/studyContext/edit"
        assert model.studyContextInstance != null
        assert model.studyContextInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/studyContext/list'

        response.reset()

        populateValidParams(params)
        def studyContext = new StudyContext(params)

        assert studyContext.save() != null
        assert StudyContext.count() == 1

        params.id = studyContext.id

        controller.delete()

        assert StudyContext.count() == 0
        assert StudyContext.get(studyContext.id) == null
        assert response.redirectedUrl == '/studyContext/list'
    }
}
