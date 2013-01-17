package gcrud



import org.junit.*
import grails.test.mixin.*

@TestFor(StudyController)
@Mock(Study)
class StudyControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/study/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.studyInstanceList.size() == 0
        assert model.studyInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.studyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.studyInstance != null
        assert view == '/study/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/study/show/1'
        assert controller.flash.message != null
        assert Study.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/study/list'

        populateValidParams(params)
        def study = new Study(params)

        assert study.save() != null

        params.id = study.id

        def model = controller.show()

        assert model.studyInstance == study
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/study/list'

        populateValidParams(params)
        def study = new Study(params)

        assert study.save() != null

        params.id = study.id

        def model = controller.edit()

        assert model.studyInstance == study
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/study/list'

        response.reset()

        populateValidParams(params)
        def study = new Study(params)

        assert study.save() != null

        // test invalid parameters in update
        params.id = study.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/study/edit"
        assert model.studyInstance != null

        study.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/study/show/$study.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        study.clearErrors()

        populateValidParams(params)
        params.id = study.id
        params.version = -1
        controller.update()

        assert view == "/study/edit"
        assert model.studyInstance != null
        assert model.studyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/study/list'

        response.reset()

        populateValidParams(params)
        def study = new Study(params)

        assert study.save() != null
        assert Study.count() == 1

        params.id = study.id

        controller.delete()

        assert Study.count() == 0
        assert Study.get(study.id) == null
        assert response.redirectedUrl == '/study/list'
    }
}
