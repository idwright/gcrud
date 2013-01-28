package gcrud

class Person {

	//expose a json-rest-api
	static expose = 'person'
	
    static constraints = {
		name(blank:false, maxSize:100)
		email(email:true)
		description(maxSize:1500)
		filename(blank:true, nullable:true)
    }
	
	static transients = ['tooShort' , 'tooLong', 'noEmail', 'hasAffiliations']
	String name
	String email
	String description
	String filename
	boolean tooShort
	boolean tooLong
	boolean noEmail
	boolean hasAffiliations
	
	static hasMany = [affiliation:Affiliation]
	
	String toString() {
		return name
	}
}
