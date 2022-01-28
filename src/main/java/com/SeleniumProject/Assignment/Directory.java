package com.SeleniumProject.Assignment;


public class Directory 
{
    String FirstName,LastName,District,Party,OfficeRoom,Phone,CommitteeAssignment,Country,url;
    Directory(String FName,String LName,String District,String Party,String OfficeRoom,String Phone,String CommitteeAssignment,String Country,String url){
    	this.FirstName=FName;
    	this.LastName=LName;
    	this.District=District;
    	this.Party=Party;
    	this.OfficeRoom=OfficeRoom;
    	this.Phone=Phone;
    	this.CommitteeAssignment=CommitteeAssignment;
    	this.Country=Country;
    	this.url=url;
    }
   
	public String toString() {
		return FirstName+" "+LastName+" "+District+" "+Party+" "+OfficeRoom+" "+Phone+" "+CommitteeAssignment+" "+Country;
	}
}
