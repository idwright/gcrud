package gcrud

class StudyContext {

    static constraints = {
    }
	
	Float latitude;
	Float longitude;
	String country
	String subCont
	String contextID
	String studies
	String name
	int numSamples

	String toString() {
		return name
	}	
}
