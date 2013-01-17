package gcrud

class Affiliation {

    static constraints = {
		name(blank:false, maxSize:300)
		url(maxSize:1500)
		description(maxSize:1500)
    }
	
	String name
	String description
	String url
	
	
	String toString() {
		return name
	}
}
