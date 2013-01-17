package gcrud

class Person {

    static constraints = {
		name(blank:false, maxSize:100)
		email(url:true)
		description(maxSize:1500)
    }
	
	String name
	String email
	String description
	
	static hasMany = [affiliation:Affiliation]
	
	String toString() {
		return name
	}
}
