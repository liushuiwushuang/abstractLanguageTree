package bs.nina.monitor;

import bs.nina.entities.Clazz;
import bs.nina.entities.CloneClass;
import bs.nina.entities.CloneInstance;
import bs.nina.entities.Commit;
import bs.nina.entities.Developer;
import bs.nina.entities.Method;
import bs.nina.entities.Repository;





public class CloneEvent {

	private Commit commit;
	private Developer developer;
	private Repository repository; 
	private Method method;
	private Clazz clazz;
	private CloneClass cloneClass;
	private CloneInstance cloneInstance;
	public Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public Repository getRepository() {
		return repository;
	}
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public CloneClass getCloneClass() {
		return cloneClass;
	}
	public void setCloneClass(CloneClass cloneClass) {
		this.cloneClass = cloneClass;
	}
	public CloneInstance getCloneInstance() {
		return cloneInstance;
	}
	public void setCloneInstance(CloneInstance cloneInstance) {
		this.cloneInstance = cloneInstance;
	}
	
	
}
