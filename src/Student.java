package modell;

public class Student {
private String spnr;
private String sName;
private String sAdress;

public Student (String spnr, String sName, String sAdress){
	setSpnr(spnr);
	setsName(sName);
	setsAdress(sAdress);
}
	
public String getSpnr() {
	return spnr;
}
public void setSpnr(String spnr) {
	this.spnr = spnr;
}
public String getsName() {
	return sName;
}
public void setsName(String name) {
	this.sName = name;
}
public String getsAdress() {
	return sAdress;
}
public void setsAdress(String adress) {
	this.sAdress = adress;
}
}
